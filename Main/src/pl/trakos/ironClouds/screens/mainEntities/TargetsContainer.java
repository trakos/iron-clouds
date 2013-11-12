package pl.trakos.ironClouds.screens.mainEntities;

import pl.trakos.ironClouds.screens.mainEntities.targets.Plane;
import pl.trakos.lib.GameEntitiesContainer;

/**
 * User: trakos
 * Date: 11.11.13
 * Time: 18:17
 */
public class TargetsContainer extends GameEntitiesContainer
{
    public TargetsContainer()
    {
        add(new Plane());
    }

    final float spawnDelay = 4.0f;
    float nextPlane = 0f;
    @Override
    public void update(float delta)
    {
        super.update(delta);
        if (nextPlane <= 0 && entities.size() < 5)
        {
            add(new Plane());
            nextPlane = spawnDelay;
        }
        nextPlane -= delta;
    }

}
