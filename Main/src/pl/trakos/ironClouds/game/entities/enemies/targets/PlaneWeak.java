package pl.trakos.ironClouds.game.entities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;

public class PlaneWeak extends AbstractTarget
{

    public PlaneWeak(float x, float y)
    {
        super(x, y, IronCloudsAssets.texturePlane1);
        speed = 200;
    }

    @Override
    protected int getInitialHp()
    {
        return EnemyType.PlaneWeak.hitPoints;
    }

    @Override
    protected void addHitEffectParticle(int remainingHp)
    {
    }

    @Override
    protected float getNextBombDelay()
    {
        return 2.5f + .5f * (float)Math.random();
    }

    @Override
    protected float getOutOfMapTimeout()
    {
        return 1;
    }
}
