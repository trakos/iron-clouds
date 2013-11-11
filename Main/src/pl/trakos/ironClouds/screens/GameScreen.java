package pl.trakos.ironClouds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector3;
import pl.trakos.ironClouds.screens.mainEntities.*;
import pl.trakos.ironClouds.screens.mainEntities.tank.Tank;
import pl.trakos.ironClouds.screens.mainEntities.tank.TankMissileContainer;
import pl.trakos.lib.*;

public class GameScreen implements Screen
{
    OrthographicCamera camera;
    SpriteBatch batch;

    Background background;
    TankAndMissiles tankAndMissiles;

    public GameScreen()
    {
        background = new Background();
        tankAndMissiles = new TankAndMissiles();

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

    public void update(float delta)
    {
        tankAndMissiles.update(delta);
    }

    public void draw()
    {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        {
            background.draw(camera, batch);
            tankAndMissiles.draw(camera, batch);
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
