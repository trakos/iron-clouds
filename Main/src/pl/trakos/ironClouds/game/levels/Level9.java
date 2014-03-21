package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;

public class Level9 extends AbstractLevel
{
    public Level9()
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
        for (float time = 2f; time < 60; time+=15f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.Zeppelin, time));
        }
        for (float time = 8f; time < 60; time+=32f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.Bomber, time));
        }
        sortSpawns();
    }

    @Override
    protected String getTitle()
    {
        return "LEVEL 9";
    }
}
