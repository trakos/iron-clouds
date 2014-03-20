package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;

public class Level9 extends AbstractLevel
{
    public Level9()
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
        for (float time = 2f; time < 40; time+=10f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.Zeppelin, time));
        }
        for (float time = 2f; time < 40; time+=22f)
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
