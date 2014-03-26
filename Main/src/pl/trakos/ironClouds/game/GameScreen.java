package pl.trakos.ironClouds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import pl.trakos.lib.GameFboParticle;
import pl.trakos.lib.GameLayers;
import pl.trakos.lib.GameSettings;
import pl.trakos.lib.GameTouchType;

public class GameScreen implements Screen
{
    SpriteBatch batch;
    GameCoreEntity gameCoreEntity;

    public GameScreen()
    {
        GameFboParticle.createOrResumeAll();
        GameSettings.getCamera().setToOrtho(false, GameSettings.getResolutionWidth(), GameSettings.getResolutionHeight());
        batch = new SpriteBatch();
        cameraMarginX = GameSettings.getCameraWidth() / 4;

        gameCoreEntity = GameCoreEntity.instance;
        gameCoreEntity.start();
    }

    GameTouchType[] activeTouches = new GameTouchType[10];
    Integer[] activeTouchesIds = new Integer[10];
    int activeTouchNextId = 0;

    public void handleInput()
    {
        for (int k = 0; k < 10; k++)
        {
            if (Gdx.input.isTouched(k))
            {
                if (activeTouchesIds[k] == null)
                {
                    activeTouchesIds[k] = activeTouchNextId++;
                    if (activeTouchNextId == Integer.MAX_VALUE)
                    {
                        activeTouchNextId = 0;
                    }
                }

                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(k), Gdx.input.getY(k), 0);
                GameSettings.getCamera().unproject(touchPos);

                GameTouchType touchType = gameCoreEntity.handleTouch(touchPos.x, touchPos.y, activeTouches[k], activeTouchesIds[k]);
                activeTouches[k] =
                    touchType != GameTouchType.NotIntercepted
                    ? touchType
                    : (activeTouches[k] == null ? GameTouchType.NotIntercepted : activeTouches[k]);


            }
            else
            {
                activeTouches[k] = null;
                activeTouchesIds[k] = null;
            }
        }
    }

    private float fpsDrawDelay = 1;

    public void update(float delta)
    {
        gameCoreEntity.update(delta);
        updateCameraPosition(GameSettings.getCameraStartX(), gameCoreEntity.getPlayerCameraX(), GameSettings.getCamera());

        if (fpsDrawDelay <= 0)
        {
            Gdx.app.log("fps", String.valueOf(Math.round(Gdx.graphics.getFramesPerSecond())));
            fpsDrawDelay = 1;
        }
        else
        {
            fpsDrawDelay -= delta;
        }
    }

    final float cameraMarginX;

    public void updateCameraPosition(float cameraStartX, float tankPositionX, OrthographicCamera camera)
    {
        float cameraStartMin = Math.max(tankPositionX - cameraMarginX, 0);
        float cameraStartMax = Math.min(tankPositionX - GameSettings.getCameraWidth() + cameraMarginX,
                GameSettings.getMapWidth() - GameSettings.getCameraWidth());
        float newPositionX = Math.min(cameraStartMin, Math.max(cameraStartMax, cameraStartX))
                             + GameSettings.getCameraWidth() / 2;
        if (newPositionX != camera.position.x)
        {
            camera.position.x = newPositionX;
            camera.update();
        }
    }

    public void draw()
    {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        GameFboParticle.instance.beginPreparing();
        drawHelper(GameLayers.LayerPrepareParticles);
        GameFboParticle.instance.endPreparing();

        GameFboParticle.foregroundInstance.beginPreparing();
        drawHelper(GameLayers.LayerPrepareParticlesForeground);
        GameFboParticle.foregroundInstance.endPreparing();

        batch.setProjectionMatrix(GameSettings.getCamera().combined);
        batch.begin();
        for (GameLayers layer : GameLayers.values())
        {
            if (layer == GameLayers.LayerPrepareParticles || layer == GameLayers.LayerPrepareParticlesForeground)
            {
                continue;
            }
            drawHelper(layer);
        }
        batch.end();
    }

    protected void drawHelper(GameLayers layer)
    {
        gameCoreEntity.draw(layer, batch);
    }

    @Override
    public void render(float delta)
    {
        handleInput();
        update(delta);
        draw();
    }

    @Override
    public void resize(int width, int height)
    {
        GameSettings.setScreenWidth(width);
        GameSettings.setScreenHeight(height);
    }

    @Override
    public void show()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void pause()
    {
        if (Gdx.app.getType() == Application.ApplicationType.Android)
        {
            gameCoreEntity.changeGameState(
                    (gameCoreEntity.getGameState() == GameCoreEntity.GameState.GameActive
                            || gameCoreEntity.getGameState() == GameCoreEntity.GameState.GamePausedInMenu)
                            ? GameCoreEntity.GameState.GamePausedByOSGame
                            : GameCoreEntity.GameState.GamePausedByOSMainMenu
            );
            GameFboParticle.disposeAll();
        }
    }

    @Override
    public void resume()
    {
        if (Gdx.app.getType() == Application.ApplicationType.Android)
        {
            GameFboParticle.createOrResumeAll();
            gameCoreEntity.changeGameState(
                    gameCoreEntity.getGameState() == GameCoreEntity.GameState.GamePausedByOSMainMenu
                            ? GameCoreEntity.GameState.MainMenu
                            : GameCoreEntity.GameState.GamePausedInMenu
            );
        }
    }

    @Override
    public void dispose()
    {
        GameFboParticle.disposeAll();
    }
}
