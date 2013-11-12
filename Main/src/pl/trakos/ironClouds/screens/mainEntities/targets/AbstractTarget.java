package pl.trakos.ironClouds.screens.mainEntities.targets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameSettings;

/**
 * User: trakos
 * Date: 11.11.13
 * Time: 03:30
 */
public abstract class AbstractTarget extends GameEntity
{
    float speed = 300f;

    float positionX = 0;
    float positionY = 300f;
    int direction = 1;

    Polygon targetPolygon;
    Texture texture;

    public void initPosition()
    {
        positionX -= texture.getWidth();
        positionY = MathUtils.random(200f, 400f);
    }

    @Override
    public void update(float delta)
    {
        if (direction == 1 && positionX > GameSettings.getWidth())
        {
            direction = -1;
        }
        else if (direction == -1 && positionX < -texture.getWidth())
        {
            direction = 1;
        }
        positionX += direction * speed * delta;

        targetPolygon.setPosition(positionX, positionY);
    }

    @Override
    public void draw(Camera camera, SpriteBatch batch)
    {
        batch.draw(
                texture,
                positionX,
                positionY,
                positionX,
                positionY,
                texture.getWidth(),
                texture.getHeight(),
                1,
                1,
                0,
                0,
                0,
                texture.getWidth(),
                texture.getHeight(),
                direction == -1,
                false);
    }

    @Override
    public Polygon getHitBox()
    {
        return targetPolygon;
    }

    @Override
    public void dispose()
    {
        // @todo
    }
}
