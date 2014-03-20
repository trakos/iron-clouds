package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;

public class Level2 extends AbstractLevel
{
    public Level2()
    {
        for (float time = 3f; time < 40; time+=1f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        sortSpawns();
    }
}
