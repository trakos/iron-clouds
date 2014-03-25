package pl.trakos.ironClouds.game.entities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.TParticle;

public class Bomber extends AbstractTarget
{

    public Bomber(float x, float y)
    {
        super(x, y, IronCloudsAssets.textureBomber);
        speed = 300 + 10f * (float) Math.random();
    }

    @Override
    protected float getOutOfMapTimeout()
    {
        return (float) (Math.random() * 10);
    }

    @Override
    protected int getInitialHp()
    {
        return EnemyType.Bomber.hitPoints;
    }

    @Override
    protected void addHitEffectParticle(int remainingHp)
    {
        if (remainingHp == 2)
        {
            particles.add(new TParticle(IronCloudsAssets.particleEffectGrayExhaust.obtain(), getWidth() / 2, getHeight() / 2));
        }
        else if (remainingHp == 1)
        {
            particles.add(new TParticle(IronCloudsAssets.particleEffectExhaust.obtain(), getWidth() / 2, getHeight() / 2));
        }
    }

    @Override
    protected float getNextBombDelay()
    {
        return .5f;
    }
}
