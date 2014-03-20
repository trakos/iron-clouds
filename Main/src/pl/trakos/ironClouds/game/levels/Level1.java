package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;

public class Level1 extends AbstractLevel
{
    public Level1()
    {
        for (float time = 3f; time < 30; time+=1.5f)
        {
            enemySpawns.add(new EnemySpawn(AbstractTarget.EnemyType.PlaneWeak, time));
        }
        sortSpawns();
    }
}
