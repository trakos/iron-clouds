package pl.trakos.ironClouds.screens.mainEntities.targets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameLayers;
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
    TextureRegion texture;

    public void initPosition()
    {
        positionX -= texture.getRegionWidth();
        positionY = MathUtils.random(200f, 400f);

        targetPolygon = new Polygon(new float[] {
                0, 0,
                0, texture.getRegionHeight(),
                texture.getRegionWidth(), texture.getRegionHeight(),
                texture.getRegionWidth(), 0
        });
    }

    public int getWidth()
    {
        return texture.getRegionWidth();
    }

    public int getHeight()
    {
        return texture.getRegionHeight();
    }

    @Override
    public void update(float delta)
    {
        if (direction == 1 && positionX > GameSettings.getMapWidth())
        {
            direction = -1;
        }
        else if (direction == -1 && positionX < -texture.getRegionWidth())
        {
            direction = 1;
        }
        positionX += direction * speed * delta;

        targetPolygon.setPosition(positionX, positionY);
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerMain)
        {
            batch.draw(
                    texture,
                    positionX,
                    positionY,
                    texture.getRegionWidth() * direction,
                    texture.getRegionHeight());
        }
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
