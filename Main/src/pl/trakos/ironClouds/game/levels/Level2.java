package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.lib._;

public class Level2 extends AbstractLevel
{
    public Level2()
    {
        for (float time = 3f; time < 80; time+=2f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        sortSpawns();
    }

    @Override
    protected String getTitle()
    {
        return _.tr("game.level", 2);
    }
}
