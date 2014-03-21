package pl.trakos.ironClouds.game.entities.enemies.targets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public enum EnemyType
    {
        PlaneWeak(.2f, .7f, 1),
        PlaneNormal(.4f, .9f, 1),
        Heli(0, .6f, 1),
        Zeppelin(.7f, 1, 5),
        Bomber(.8f, 1, 3);

        public float minHeight;
        public float maxHeight;
        public int hitPoints;

        EnemyType(float minHeight, float maxHeight, int hitPoints)
        {
            this.minHeight = minHeight;
            this.maxHeight = maxHeight;
            this.hitPoints = hitPoints;
        }
    }

    static public AbstractTarget instantiate(EnemyType enemyType, float y)
    {
        switch (enemyType)
        {
            case PlaneWeak:
                return new PlaneWeak(y);
            case PlaneNormal:
                return new PlaneNormal(y);
            case Heli:
                return new Heli(y);
            case Zeppelin:
                return new Zeppelin(y);
            case Bomber:
                return new Bomber(y);
            default:
                throw new RuntimeException("unknown enemy type: " + enemyType.toString());
        }
    }

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

    public AbstractTarget(float y)
    {
        hp = getInitialHp();
        nextBombIn = getNextBombDelay();
        float minHeight = GameSettings.groundPositionY + 50;
        positionY = (GameSettings.getMapHeight() - minHeight - 50) * y + minHeight;
    }


    public void initPosition()
    {
        positionX -= texture.getRegionWidth();

        targetPolygon = new Polygon(new float[]{
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
        nextBombIn -= delta;
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
