package pl.trakos.ironClouds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector3;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.screens.mainEntities.*;
import pl.trakos.ironClouds.screens.mainEntities.Background;
import pl.trakos.ironClouds.screens.mainEntities.tank.TankMissile;
import pl.trakos.lib.*;

import java.util.Hashtable;
import java.util.Map;

public class GameScreen implements Screen
{
    SpriteBatch batch;

    Background background;
    TankAndMissiles tankAndMissiles;
    TargetsContainer targets;

    public GameScreen()
    {
        background = new Background();
        tankAndMissiles = new TankAndMissiles();
        targets = new TargetsContainer();

        GameSettings.getCamera().setToOrtho(false, GameSettings.getResolutionWidth(), GameSettings.getResolutionHeight());
        batch = new SpriteBatch();
        cameraMarginX = GameSettings.getCameraWidth() / 4;
    }

    public void handleInput()
    {
        if (Gdx.input.isTouched())
        {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameSettings.getCamera().unproject(touchPos);

            tankAndMissiles.handleTouch(touchPos.x, touchPos.y);
        }
    }

    static private Hashtable<GameEntity, GameEntity[]> entitiesHitByAnyOf;
    private float fpsDrawDelay = 1;
    public void update(float delta)
    {
        background.update(delta);
        GameFboParticle.instance.update(delta);
        tankAndMissiles.update(delta);
        targets.update(delta);

        entitiesHitByAnyOf = GameEntitiesContainer.getEntitiesHitByAnyOf(tankAndMissiles.missiles, targets);
        for (Map.Entry<GameEntity, GameEntity[]> entry : entitiesHitByAnyOf.entrySet())
        {
            entry.getKey().alive = false;
            entry.getValue()[0].alive = false;
            GameFboParticle.instance.playParticleEffect(IronCloudsAssets.particleEffectExplosion,
                    entry.getValue()[0].getX() + entry.getValue()[0].getWidth() / 2,
                    entry.getValue()[0].getY() + entry.getValue()[0].getHeight() / 2);
            IronCloudsAssets.soundSimpleExplosion.play(0.7f);
        }

        updateCameraPosition(GameSettings.getCameraStartX(), tankAndMissiles.getTankPositionX(), GameSettings.getCamera());

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
        float newPositionX = Math.min(cameraStartMin, Math.max(cameraStartMax, cameraStartX)) + GameSettings.getCameraWidth() / 2;
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

        batch.setProjectionMatrix(GameSettings.getCamera().combined);
        batch.begin();
        for (GameLayers layer : GameLayers.values())
        {
            if (layer == GameLayers.LayerPrepareParticles)
            {
                continue;
            }
            drawHelper(layer);
        }
        batch.end();
    }

    protected void drawHelper(GameLayers layer)
    {
        background.draw(layer, batch);
        GameFboParticle.instance.draw(layer, batch);
        tankAndMissiles.draw(layer, batch);
        targets.draw(layer, batch);
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

    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
    }
}
