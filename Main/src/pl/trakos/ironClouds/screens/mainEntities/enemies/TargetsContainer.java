package pl.trakos.ironClouds.screens.mainEntities.enemies;

import pl.trakos.ironClouds.screens.mainEntities.enemies.targets.AbstractTarget;
import pl.trakos.ironClouds.screens.mainEntities.enemies.targets.PlaneWeak;
import pl.trakos.ironClouds.screens.mainEntities.enemies.targets.Zeppelin;
import pl.trakos.lib.GameEntitiesContainer;
import pl.trakos.lib.GameEntity;

import java.util.Iterator;
import java.util.List;

/**
 * User: trakos
 * Date: 11.11.13
 * Time: 18:17
 */
public class TargetsContainer extends GameEntitiesContainer implements Iterable<AbstractTarget>
{
    public TargetsContainer()
    {
        add(new PlaneWeak());
    }

    final float spawnDelay = 4.0f;
    float nextPlane = 0f;
    @Override
    public void update(float delta)
    {
        super.update(delta);
        if (nextPlane <= 0 && entities.size() < 5)
        {
            add(new Zeppelin());
            nextPlane = spawnDelay;
        }
        nextPlane -= delta;
    }

    class TargetsIterator implements Iterator<AbstractTarget>
    {
        private final Iterator<GameEntity> iterator;

        public TargetsIterator(List<GameEntity> entitiesList)
        {
            iterator = entitiesList.iterator();
        }

        @Override
        public boolean hasNext()
        {
            return iterator.hasNext();
        }

        @Override
        public AbstractTarget next()
        {
            return (AbstractTarget) iterator.next();
        }

        @Override
        public void remove()
        {
            iterator.remove();
        }
    }

    @Override
    public Iterator<AbstractTarget> iterator()
    {
        return new TargetsIterator(entities);
    }
}
