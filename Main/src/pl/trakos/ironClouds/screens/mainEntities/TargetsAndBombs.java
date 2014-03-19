package pl.trakos.ironClouds.screens.mainEntities;

import pl.trakos.ironClouds.screens.mainEntities.enemies.BombsContainer;
import pl.trakos.ironClouds.screens.mainEntities.enemies.PlaneBomb;
import pl.trakos.ironClouds.screens.mainEntities.enemies.TargetsContainer;
import pl.trakos.ironClouds.screens.mainEntities.enemies.targets.AbstractTarget;
import pl.trakos.ironClouds.screens.mainEntities.tank.Tank;
import pl.trakos.lib.GameEntitiesContainer;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameSettings;

public class TargetsAndBombs extends GameEntitiesContainer
{
    public final TargetsContainer targets;
    public final BombsContainer bombs;

    public TargetsAndBombs()
    {
        targets = new TargetsContainer();
        bombs = new BombsContainer();
        bombs.add(10f, GameSettings.getMapHeight(), 100f, true);

        add(targets);
        add(bombs);
    }


    final float spawnDelay = .5f;
    float nextPlane = 0f;
    @Override
    public void update(float delta)
    {
        super.update(delta);

        if (nextPlane <= 0 && entities.size() < 5)
        {
            AbstractTarget target = targets.getRandom();
            if (target != null)
            {
                bombs.add(target.getX(), target.getY(), .5f * target.getSpeed(), target.getDirection() == 1);
            }
            nextPlane = spawnDelay;
        }
        nextPlane -= delta;
    }

    public boolean isTankHit(Tank tank)
    {
        GameEntity[] bombHits = bombs.getEntitiesHitBy(tank);
        if (bombHits == null)
        {
            return false;
        }
        for (GameEntity bombHit : bombHits)
        {
            if (bombHit instanceof PlaneBomb)
            {
                ((PlaneBomb) bombHit).makeBoom(true);
            }
        }
        return true;
    }
}
