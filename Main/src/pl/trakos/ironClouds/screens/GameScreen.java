package pl.trakos.ironClouds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector3;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.screens.mainEntities.*;
import pl.trakos.ironClouds.screens.mainEntities.tank.Tank;
import pl.trakos.ironClouds.screens.mainEntities.tank.TankMissile;
import pl.trakos.ironClouds.screens.mainEntities.tank.TankMissileContainer;
import pl.trakos.ironClouds.screens.mainEntities.targets.Plane;
import pl.trakos.lib.*;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GameScreen implements Screen
{
    OrthographicCamera camera;
    SpriteBatch batch;

    Background background;
    TankAndMissiles tankAndMissiles;
    TargetsContainer targets;

    public GameScreen()
    {
        background = new Background();
        tankAndMissiles = new TankAndMissiles();
        targets = new TargetsContainer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
    }

    public void handleInput()
    {
        if (Gdx.input.isTouched())
        {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            tankAndMissiles.handleTouch(touchPos.x, touchPos.y);
        }
    }

    static private Hashtable<GameEntity, GameEntity[]> entitiesHitByAnyOf;
    public void update(float delta)
    {
        GameFboParticle.instance.update(delta);
        tankAndMissiles.update(delta);
        targets.update(delta);

        entitiesHitByAnyOf = GameEntitiesContainer.getEntitiesHitByAnyOf(tankAndMissiles.missiles, targets);
        for (Map.Entry<GameEntity, GameEntity[]> entry : entitiesHitByAnyOf.entrySet())
        {
            entry.getKey().alive = false;
            entry.getValue()[0].alive = false;
            GameFboParticle.instance.playParticleEffect(IronCloudsAssets.particleEffectExplosion, ((TankMissile)entry.getKey()).getPositionX(),
                    ((TankMissile)entry.getKey()).getPositionY());
            IronCloudsAssets.soundSimpleExplosion.play(0.7f);
        }
    }

    public void draw()
    {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        {
            background.draw(camera, batch);
            GameFboParticle.instance.draw(camera, batch);
            tankAndMissiles.draw(camera, batch);
            targets.draw(camera, batch);
        }
        batch.end();
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
