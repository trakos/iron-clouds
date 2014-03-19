package pl.trakos.ironClouds.screens.mainEntities.enemies.targets;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameFboParticle;
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
    int initialHp = 2;
    int hp;

    Polygon targetPolygon;
    TextureRegion texture;
    private ParticleEffectPool.PooledEffect smokeEffect;

    public AbstractTarget()
    {
        hp = initialHp;
    }

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
        smokeEffect = IronCloudsAssets.particleEffectGrayExhaust.obtain();
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
    public float getX()
    {
        return positionX - (direction == 1 ? 0 : texture.getRegionWidth());
    }

    @Override
    public float getY()
    {
        return positionY;
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

        targetPolygon.setPosition(positionX - (direction == 1 ? 0 : texture.getRegionWidth()), positionY);
        smokeEffect.setPosition(positionX, getY() + getHeight() / 2);
        smokeEffect.update(delta);
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerPrepareParticles)
        {
            if (initialHp > hp && hp > 0)
            {
                GameFboParticle.instance.renderParticle(smokeEffect);
            }
        }
        if (layer == GameLayers.LayerMain)
        {
            batch.draw(
                    texture,
                    positionX,
                    positionY,
                    texture.getRegionWidth() * direction,
                    texture.getRegionHeight());
            //GameDebug.markPolygon(batch, targetPolygon);
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
        smokeEffect.dispose();
    }

    public float getSpeed()
    {
        return speed;
    }

    public int getDirection()
    {
        return direction;
    }

    public void targetHit()
    {
        if (--hp <= 0)
        {
            alive = false;
        }
    }
}
