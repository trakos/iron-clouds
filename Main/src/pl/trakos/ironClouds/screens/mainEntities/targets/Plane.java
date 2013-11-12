package pl.trakos.ironClouds.screens.mainEntities.targets;

import com.badlogic.gdx.math.Polygon;
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
        texture = IronCloudsAssets.textureJumboJet;
        targetPolygon = new Polygon(new float[] {
                        0, 24,
                        64, 24,
                        0, 40,
                        64, 40
                });
        initPosition();
    }
}
