package pl.trakos.ironClouds.game.var;

import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.lib.GameDifficulty;

import java.util.Dictionary;
import java.util.Hashtable;

public class WinPoints
{
    public int missilesLessThanOnEasiest;
    public int missilesLeft;
    public int healthLessThanOnEasiest;
    public int healthLeft;
    public int difficultyBonus;
    public float timeTaken;
    public GameDifficulty gameDifficulty;
    public Dictionary<AbstractTarget.EnemyType, Integer> targetsShotDictionary = new Hashtable<AbstractTarget.EnemyType, Integer>();

    public static final int pointsPerMissile = 5;
    public static final int pointsPerHeart = 20;

    public int getTotalPoints()
    {
        return getPointsForHearts() + getPointsForMissiles() + getTotalDifficultyBonus();
    }

    public int getTotalDifficultyBonus()
    {
        return (missilesLessThanOnEasiest) * pointsPerMissile + (healthLessThanOnEasiest) * pointsPerHeart + difficultyBonus;
    }

    public int getPointsForMissiles()
    {
        return (missilesLeft) * pointsPerMissile;
    }

    public int getPointsForHearts()
    {
        return (healthLeft) * pointsPerHeart;
    }
}
