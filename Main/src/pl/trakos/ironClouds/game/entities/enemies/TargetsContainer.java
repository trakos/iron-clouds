package pl.trakos.ironClouds.game.entities.enemies;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
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
    }

    public void addEnemy(AbstractTarget.EnemyType enemyType, float y)
    {
        add(AbstractTarget.instantiate(enemyType, y));
    }

    public void addEnemy(AbstractTarget.EnemyType enemyType, float x, float y)
    {
        add(AbstractTarget.instantiate(enemyType, x, y));
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
    }

    public void randomlyPlaceEnemiesForBackground()
    {
        addEnemy(AbstractTarget.EnemyType.PlaneWeak, (float) Math.random(), (float) Math.random());
        addEnemy(AbstractTarget.EnemyType.PlaneNormal, (float) Math.random(), (float) Math.random());
        addEnemy(AbstractTarget.EnemyType.Heli, (float) Math.random(), (float) Math.random());
        addEnemy(AbstractTarget.EnemyType.Zeppelin, (float) Math.random(), (float) Math.random());
        addEnemy(AbstractTarget.EnemyType.Bomber, (float) Math.random(), (float) Math.random());
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
