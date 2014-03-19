package pl.trakos.ironClouds.screens.mainEntities.enemies;

import pl.trakos.ironClouds.screens.mainEntities.enemies.targets.AbstractTarget;
import pl.trakos.ironClouds.screens.mainEntities.enemies.targets.Plane;
import pl.trakos.lib.GameEntitiesContainer;

import java.util.Random;

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

    public AbstractTarget getRandom()
    {
        if (entities.size() < 1)
        {
            return null;
        }
        return (AbstractTarget) entities.get(new Random().nextInt(entities.size()));
    }

}
