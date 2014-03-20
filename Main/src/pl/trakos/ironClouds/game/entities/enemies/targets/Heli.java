package pl.trakos.ironClouds.game.entities.enemies.targets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.game.entities.enemies.targets.heli.Tail;
import pl.trakos.lib.GameLayers;

import java.util.Random;

public class Heli extends AbstractTarget
{
    final Tail tail = new Tail();

    public Heli(float y)
    {
        super(y);
        texture = IronCloudsAssets.textureHeli;
        initPosition();
    }

    @Override
    public void dispose()
    {
        ensureEngineSoundIs(false);
        super.dispose();
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        super.draw(layer, batch);
        if (layer == GameLayers.LayerMain)
        {
            tail.draw(layer, batch);
        }
    }

    float time = 0;

    @Override
    public void update(float delta)
    {
        time += delta;
        Random random = new Random(new Random((long) Math.floor(time)).nextLong());
        float randomValue = random.nextFloat();
        speed = (float) (10 + 190 * randomValue);
        super.update(delta);
        tail.setX(getX() + (direction < 0 ? getWidth() - tail.getWidth() - 7f : -2f));
        tail.setY(getY() + 16f);
        tail.update(delta);
        ensureEngineSoundIs(alive);
    }

    @Override
    protected float getOutOfMapTimeout()
    {
        return 1;
    }

    @Override
    protected int getInitialHp()
    {
        return EnemyType.Heli.hitPoints;
    }

    @Override
    protected void addHitEffectParticle(int remainingHp)
    {

    }

    @Override
    protected float getNextBombDelay()
    {
        return 2;
    }

    long soundId = 0;

    public void ensureEngineSoundIs(boolean stateToggle)
    {
        if (stateToggle != (soundId != 0))
        {
            if (stateToggle && soundId == 0)
            {
                soundId = IronCloudsAssets.soundHeli.loop(0.2f);
            }
            else
            {
                IronCloudsAssets.soundHeli.stop(soundId);
                soundId = 0;
            }
        }
    }
}
