package pl.trakos.ironClouds.screens.mainEntities.enemies.targets.heli;

import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.GameAnimation;

public class Tail extends GameAnimation
{
    public Tail()
    {
        super(IronCloudsAssets.textureHeli, 9, 1, .025f);
        looping = true;
    }

    @Override
    public float getX()
    {
        return x;
    }

    @Override
    public float getY()
    {
        return y;
    }

    public void setX(float value)
    {
        x = value;
    }

    public void setY(float value)
    {
        y = value;
    }
}
