package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;

public class Level7 extends AbstractLevel
{
    public Level7()
    {
        for (float time = 3f; time < 40; time+=1f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        for (float time = 2f; time < 40; time+=1.15f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneNormal, time));
        }
        for (float time = 2f; time < 40; time+=1.2f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.Heli, time));
        }
        for (float time = 2f; time < 40; time+=20f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.Zeppelin, time));
        }
        sortSpawns();
    }

    @Override
    protected String getTitle()
    {
        return "LEVEL 7";
    }
}
