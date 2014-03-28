package pl.trakos.ironClouds.game.levels;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.lib._;

import java.util.ArrayList;

public class Level extends AbstractLevel
{
    enum EnemyStrength
    {
        Weakest,
        Weak,
        Normal,
        Strong,
        Strongest
    }

    final int levelNumber;

    ArrayList<AbstractTarget.EnemyType> enemyComposition = new ArrayList<AbstractTarget.EnemyType>();
    public Level(int levelNumber)
    {
        this.levelNumber = levelNumber;
        addEnemyToComposition(EnemyStrength.Weakest);
        addEnemyToComposition(EnemyStrength.Weak);
        addEnemyToComposition(EnemyStrength.Normal);
        addEnemyToComposition(EnemyStrength.Weakest);
        addEnemyToComposition(EnemyStrength.Weak);
        addEnemyToComposition(EnemyStrength.Normal);
        addEnemyToComposition(EnemyStrength.Weakest);
        addEnemyToComposition(EnemyStrength.Weak);
        addEnemyToComposition(EnemyStrength.Strong);
        addEnemyToComposition(EnemyStrength.Weakest);
        addEnemyToComposition(EnemyStrength.Weak);
        addEnemyToComposition(EnemyStrength.Normal);
        addEnemyToComposition(EnemyStrength.Weakest);
        addEnemyToComposition(EnemyStrength.Weak);
        addEnemyToComposition(EnemyStrength.Normal);
        addEnemyToComposition(EnemyStrength.Weakest);
        addEnemyToComposition(EnemyStrength.Weak);
        addEnemyToComposition(EnemyStrength.Strongest);
        addEnemyToComposition(EnemyStrength.Weakest);
        addEnemyToComposition(EnemyStrength.Weak);
        addEnemyToComposition(EnemyStrength.Normal);
        addEnemyToComposition(EnemyStrength.Weakest);
        addEnemyToComposition(EnemyStrength.Weak);
        addEnemyToComposition(EnemyStrength.Normal);
        int compositionIndex = 0;
        for (float time = 0; time < 20 + levelNumber * 5; time += 2 - levelNumber * .08f + Math.random())
        {
            enemySpawns.add(new EnemySpawn(enemyComposition.get(compositionIndex++), time));
            if (compositionIndex >= enemyComposition.size())
            {
                compositionIndex = 0;
            }
        }
        //sortSpawns();
    }

    private void addEnemyToComposition(EnemyStrength enemyStrength)
    {
        enemyComposition.add(getEnemyTypeForLevel(enemyStrength));
    }

    final int levelsForEnemyType = 4;
    public AbstractTarget.EnemyType getEnemyTypeForLevel(EnemyStrength enemyStrength)
    {
        if (levelNumber <= levelsForEnemyType)
        {
            return AbstractTarget.EnemyType.PlaneWeak;
        }
        if (levelNumber <= 2 * levelsForEnemyType)
        {
            switch (enemyStrength)
            {
                case Weakest:
                case Weak:
                    return AbstractTarget.EnemyType.PlaneWeak;
                case Normal:
                case Strong:
                case Strongest:
                    return AbstractTarget.EnemyType.PlaneNormal;
            }
        }
        if (levelNumber <= 3 * levelsForEnemyType)
        {
            switch (enemyStrength)
            {
                case Weakest:
                    return AbstractTarget.EnemyType.PlaneWeak;
                case Weak:
                case Normal:
                    return AbstractTarget.EnemyType.PlaneNormal;
                case Strong:
                case Strongest:
                    return AbstractTarget.EnemyType.Heli;
            }
        }
        if (levelNumber <= 4 * levelsForEnemyType)
        {
            switch (enemyStrength)
            {
                case Weakest:
                    return AbstractTarget.EnemyType.PlaneWeak;
                case Weak:
                    return AbstractTarget.EnemyType.PlaneNormal;
                case Normal:
                case Strong:
                    return AbstractTarget.EnemyType.Heli;
                case Strongest:
                    return AbstractTarget.EnemyType.Zeppelin;
            }
        }
        switch (enemyStrength)
        {
            case Weakest:
                return AbstractTarget.EnemyType.PlaneWeak;
            case Weak:
                return AbstractTarget.EnemyType.PlaneNormal;
            case Normal:
                return AbstractTarget.EnemyType.Heli;
            case Strong:
                return AbstractTarget.EnemyType.Zeppelin;
            case Strongest:
                return AbstractTarget.EnemyType.Bomber;
        }
        throw new RuntimeException("unknown type? level number? error?");
    }

    @Override
    protected String getTitle()
    {
        return _.tr("game.level", levelNumber);
    }
}
