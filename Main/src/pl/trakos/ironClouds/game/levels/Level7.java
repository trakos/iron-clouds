package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.lib._;

public class Level7 extends AbstractLevel
{
    public Level7()
    {
        for (float time = 3f; time < 60; time+=3.2f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        for (float time = 2f; time < 60; time+=4.1f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneNormal, time));
        }
        for (float time = 6f; time < 60; time+=4.6f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.Heli, time));
        }
        for (float time = 2f; time < 60; time+=20f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.Zeppelin, time));
        }
        sortSpawns();
    }

    @Override
    protected String getTitle()
    {
        return _.tr("game.level", 7);
    }
}
