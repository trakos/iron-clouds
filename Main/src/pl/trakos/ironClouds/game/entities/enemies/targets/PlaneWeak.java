package pl.trakos.ironClouds.game.entities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;

public class PlaneWeak extends AbstractTarget
{

    public PlaneWeak(float y)
    {
        super(y);
        speed = 200;
        texture = IronCloudsAssets.texturePlane1;
        initPosition();
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
