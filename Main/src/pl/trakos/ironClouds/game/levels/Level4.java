package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.lib._;

public class Level4 extends AbstractLevel
{
    public Level4()
    {
        for (float time = 3f; time < 40; time+=2.2f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        for (float time = 2f; time < 40; time+=3.1f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneNormal, time));
        }
        sortSpawns();
    }

    @Override
    protected String getTitle()
    {
        return _.tr("game.level", 4);
    }
}
