package pl.trakos.lib;

public enum GameDifficulty
{
    Easy(2f, 7, 1.5f, 1.5f),
    Medium(1.7f, 5, 1f, 1f),
    Hard(1.4f, 3, .9f, .9f);

    GameDifficulty(float missilesPerHitPoint, int maxHealth, float spawnTimeMultiplier, float bombTimeMultiplier)
    {
        this.missilesPerHitPoint = missilesPerHitPoint;
        this.maxHealth = maxHealth;
        this.spawnTimeMultiplier = spawnTimeMultiplier;
        this.bombTimeMultiplier = bombTimeMultiplier;
    }

    public float missilesPerHitPoint = 1.7f;
    public int maxHealth = 5;
    public float spawnTimeMultiplier = 1;

    public float bombTimeMultiplier = 1;
}
