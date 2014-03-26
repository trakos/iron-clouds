package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.lib._;

public class Level1 extends AbstractLevel
{
    public Level1()
    {
        for (float time = 3f; time < 60; time+=3f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        sortSpawns();
    }

    @Override
    protected String getTitle()
    {
        return _.tr("game.level", 1);
    }
}
