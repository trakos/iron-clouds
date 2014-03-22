package pl.trakos.ironClouds.game.entities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;

public class PlaneNormal extends AbstractTarget
{
    public PlaneNormal(float x, float y)
    {
        super(x, y, IronCloudsAssets.texturePlane2);
        speed = 300;
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
