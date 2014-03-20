package pl.trakos.ironClouds.screens.mainEntities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;

public class PlaneWeak extends AbstractTarget
{
    @Override
    protected int getInitialHp()
    {
        return 1;
    }

    @Override
    protected void addHitEffectParticle(int remainingHp)
    {
    }

    @Override
    protected float getNextBombDelay()
    {
        return 3;
    }

    public PlaneWeak()
    {
        speed = 200;
        texture = IronCloudsAssets.texturePlane1;
        initPosition();
    }

    @Override
    protected float getOutOfMapTimeout()
    {
        return 1;
    }
}
