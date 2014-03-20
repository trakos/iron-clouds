package pl.trakos.ironClouds.screens.mainEntities.enemies.targets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import pl.trakos.lib.*;

import java.util.ArrayList;

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
    int hp;
    float timeout = 0;
    float nextBombIn;

    Polygon targetPolygon;
    TextureRegion texture;
    ArrayList<TParticle> particles = new ArrayList<TParticle>(4);

    abstract protected int getInitialHp();
    abstract protected void addHitEffectParticle(int remainingHp);
    protected abstract float getNextBombDelay();
    protected abstract float getOutOfMapTimeout();

    public AbstractTarget()
    {
        hp = getInitialHp();
        nextBombIn = getNextBombDelay();
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
        if (timeout > 0)
        {
            timeout -= delta;
            return;
        }
        nextBombIn-=delta;
        if (direction == 1 && positionX > GameSettings.getMapWidth())
        {
            direction = -1;
            positionX = GameSettings.getMapWidth() + texture.getRegionWidth();
            timeout = getOutOfMapTimeout();
        }
        else if (direction == -1 && positionX < -texture.getRegionWidth())
        {
            direction = 1;
            timeout = getOutOfMapTimeout();
        }
        positionX += direction * speed * delta;

        targetPolygon.setPosition(positionX - (direction == 1 ? 0 : texture.getRegionWidth()), positionY);
        for (TParticle particle : particles)
        {
            particle.effect.setPosition(positionX + particle.x * direction, getY() + particle.y);
            particle.effect.update(delta);
        }
    }

    public boolean shouldDropBombNow()
    {
        if (nextBombIn < 0)
        {
            nextBombIn = getNextBombDelay();
            return true;
        }
        return false;
    }


    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (timeout > 0)
        {
            return;
        }
        if (layer == GameLayers.LayerPrepareParticlesForeground)
        {
            for (TParticle particle : particles)
            {
                GameFboParticle.foregroundInstance.renderParticle(particle.effect);
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
        for (TParticle particle : particles)
        {
            particle.effect.dispose();
        }
        particles.clear();
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
        else
        {
            addHitEffectParticle(hp);
        }
    }
}
