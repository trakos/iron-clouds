package pl.trakos.ironClouds.game.entities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.TParticle;

public class Bomber extends AbstractTarget
{

    public Bomber(float y)
    {
        super(y);
        speed = 500;
        texture = IronCloudsAssets.textureBomber;
        initPosition();
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