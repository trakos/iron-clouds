package pl.trakos.ironClouds.game.levels;


import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.ironClouds.game.entities.Hud;
import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.lib.GameSettings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public abstract class AbstractLevel
{
    class EnemySpawn
    {
        public AbstractTarget.EnemyType enemyType;
        public float time;

        EnemySpawn(AbstractTarget.EnemyType enemyType, float time)
        {
            this.enemyType = enemyType;
            this.time = time;
        }
    }

    float timeTaken = 0;
    float nextSpawnTime;
    int nextSpawn;

    ArrayList<EnemySpawn> enemySpawns = new ArrayList<EnemySpawn>();
    Random random = new Random();

    protected void sortSpawns()
    {
        Collections.sort(enemySpawns, new Comparator<EnemySpawn>()
        {
            @Override
            public int compare(EnemySpawn p1, EnemySpawn p2)
            {
                return p1.time < p2.time ? -1 : (p1.time == p2.time ? 0 : 1);
            }

        });
    }

    public void start()
    {
        timeTaken = 0;
        nextSpawn = 0;
        nextSpawnTime = enemySpawns.get(0).time;
        GameCoreEntity.instance.resetGame(getTotalHitPoints() * GameSettings.getMissilesPerHitPoint());
        Hud.instance.showTitle(getTitle(), 2f);
    }

    abstract protected String getTitle();

    public int getTotalHitPoints()
    {
        int totalHitPoints = 0;
        for (EnemySpawn enemySpawn : enemySpawns)
        {
            totalHitPoints += enemySpawn.enemyType.hitPoints;
        }
        return totalHitPoints;
    }

    public void update(float delta)
    {
        timeTaken += delta;
        if (timeTaken > nextSpawnTime)
        {
            AbstractTarget.EnemyType enemyType = enemySpawns.get(nextSpawn).enemyType;
            GameCoreEntity.instance.addEnemy(enemyType, enemyType.minHeight + random.nextFloat() * (enemyType.maxHeight - enemyType.minHeight));
            nextSpawn++;
            if (nextSpawn >= enemySpawns.size())
            {
                nextSpawnTime = Float.MAX_VALUE;
            }
            else
            {
                nextSpawnTime = enemySpawns.get(nextSpawn).time;
            }
        }
    }

    public boolean checkForWin()
    {
        return nextSpawn >= enemySpawns.size() && GameCoreEntity.instance.getTargetsCount() == 0;
    }

    public boolean checkForLoss()
    {
        GameCoreEntity game = GameCoreEntity.instance;
        return game.getTankHealth() <= 0 || ( game.getTankMissilesLeft() <= 0 && game.getAirborneMissilesCount() == 0);
    }
}
