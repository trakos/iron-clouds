package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;

public class Level5 extends AbstractLevel
{
    public Level5()
    {
        for (float time = 3f; time < 60; time+=3.2f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        for (float time = 2f; time < 60; time+=4.1f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneNormal, time));
        }
        for (float time = 6f; time < 60; time+=6.4f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.Heli, time));
        }
        sortSpawns();
    }

    @Override
    protected String getTitle()
    {
        return "LEVEL 5";
    }
}
