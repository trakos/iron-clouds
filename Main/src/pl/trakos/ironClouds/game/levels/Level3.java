package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.lib._;

public class Level3 extends AbstractLevel
{
    public Level3()
    {
        for (float time = 3f; time < 80; time+=2.2f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        for (float time = 2f; time < 60; time+=4f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneNormal, time));
        }
        sortSpawns();
    }

    @Override
    protected String getTitle()
    {
        return _.tr("game.level", 3);
    }
}
