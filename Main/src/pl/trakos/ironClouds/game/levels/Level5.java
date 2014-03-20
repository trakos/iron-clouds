package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;

public class Level5 extends AbstractLevel
{
    public Level5()
    {
        for (float time = 3f; time < 40; time+=1.1f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        for (float time = 2f; time < 40; time+=1.25f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneNormal, time));
        }
        for (float time = 2f; time < 40; time+=1.3f)
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
