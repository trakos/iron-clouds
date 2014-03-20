package pl.trakos.ironClouds.screens.mainEntities;

import pl.trakos.ironClouds.screens.mainEntities.enemies.BombsContainer;
import pl.trakos.ironClouds.screens.mainEntities.enemies.PlaneBomb;
import pl.trakos.ironClouds.screens.mainEntities.enemies.TargetsContainer;
import pl.trakos.ironClouds.screens.mainEntities.enemies.targets.AbstractTarget;
import pl.trakos.ironClouds.screens.mainEntities.tank.Tank;
import pl.trakos.lib.GameEntitiesContainer;
import pl.trakos.lib.GameEntity;

public class TargetsAndBombs extends GameEntitiesContainer
{
    public final TargetsContainer targets;
    public final BombsContainer bombs;

    public TargetsAndBombs()
    {
        targets = new TargetsContainer();
        bombs = new BombsContainer();

        add(targets);
        add(bombs);
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        for (AbstractTarget target : targets)
        {
            if (target.shouldDropBombNow())
            {
                bombs.add(target.getX() + target.getWidth() / 2, target.getY(), .5f * target.getSpeed(), target.getDirection() == 1);
            }
        }
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
