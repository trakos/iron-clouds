package pl.trakos.ironClouds.screens.mainEntities.targets;

import pl.trakos.ironClouds.IronCloudsAssets;

/**
 * User: trakos
 * Date: 11.11.13
 * Time: 03:27
 */
public class Plane extends AbstractTarget
{
    public Plane()
    {
        texture = IronCloudsAssets.texturePlane5;
        initPosition();
    }
}
