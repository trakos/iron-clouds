package pl.trakos.ironClouds.screens.mainEntities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;

public class PlaneNormal extends AbstractTarget
{
    public PlaneNormal()
    {
        speed = 300;
        texture = IronCloudsAssets.texturePlane2;
        initPosition();
    }

    @Override
    protected float getOutOfMapTimeout()
    {
        return 1;
    }

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
        return 1.5f;
    }
}
