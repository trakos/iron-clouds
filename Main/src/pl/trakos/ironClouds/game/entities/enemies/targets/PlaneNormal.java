package pl.trakos.ironClouds.game.entities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;

public class PlaneNormal extends AbstractTarget
{
    public PlaneNormal(float y)
    {
        super(y);
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
        return EnemyType.PlaneNormal.hitPoints;
    }

    @Override
    protected void addHitEffectParticle(int remainingHp)
    {

    }

    @Override
    protected float getNextBombDelay()
    {
        return 1f + .5f * (float)Math.random();
    }
}
