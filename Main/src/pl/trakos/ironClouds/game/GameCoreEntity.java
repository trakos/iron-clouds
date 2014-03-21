package pl.trakos.ironClouds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.game.entities.Background;
import pl.trakos.ironClouds.game.entities.Hud;
import pl.trakos.ironClouds.game.entities.TankAndMissiles;
import pl.trakos.ironClouds.game.entities.TargetsAndBombs;
import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.ironClouds.game.levels.*;
import pl.trakos.lib.*;

import java.util.Hashtable;
import java.util.Map;

public class GameCoreEntity extends GameEntitiesContainer
{
    static public GameCoreEntity instance = new GameCoreEntity();

    Background background;
    TankAndMissiles tankAndMissiles;
    TargetsAndBombs targetsAndBombs;
    AbstractLevel currentLevel;
    AbstractLevel[] levels;
    int currentLevelIndex = 0;

    protected GameCoreEntity()
    {
        background = new Background();
        tankAndMissiles = new TankAndMissiles();
        targetsAndBombs = new TargetsAndBombs();

        add(background);
        add(GameFboParticle.instance);
        add(tankAndMissiles);
        add(targetsAndBombs);
        add(GameFboParticle.foregroundInstance);
        add(Hud.instance);

        levels = new AbstractLevel[]
                {
                        new Level1(),
                        new Level2(),
                        new Level3(),
                        new Level4(),
                        new Level5(),
                        new Level6(),
                        new Level7(),
                        new Level8(),
                        new Level9(),
                        new Level10(),
                };
    }

    public void start()
    {
        // @TODO
        currentLevel = levels[currentLevelIndex];
        currentLevel.start();
    }

    public void addEnemy(AbstractTarget.EnemyType enemyType, float y)
    {
        targetsAndBombs.targets.addEnemy(enemyType, y);
    }

    public void resetGame(int missilesLeft)
    {
        tankAndMissiles.missiles.clear();
        targetsAndBombs.targets.clear();
        targetsAndBombs.bombs.clear();
        tankAndMissiles.tank.setPositionX(GameSettings.getMapWidth() / 2);
        tankAndMissiles.tank.setDestinationX(GameSettings.getMapWidth() / 2);
        tankAndMissiles.tank.setHealth(5);
        tankAndMissiles.tank.setMissiles(missilesLeft);
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        Hashtable<GameEntity, GameEntity[]> entitiesHitByAnyOf = GameEntitiesContainer.getEntitiesHitByAnyOf(tankAndMissiles.missiles, targetsAndBombs.targets);
        for (Map.Entry<GameEntity, GameEntity[]> entry : entitiesHitByAnyOf.entrySet())
        {
            entry.getKey().alive = false;
            AbstractTarget targetHit = (AbstractTarget) entry.getValue()[0];
            targetHit.targetHit();
            GameFboParticle.instance.playParticleEffect(
                    targetHit.alive ? IronCloudsAssets.particleEffectSmallExplosion : IronCloudsAssets.particleEffectExplosion,
                    (targetHit.alive ? 0 : targetHit.getX()) + entry.getValue()[0].getWidth() / 2,
                    (targetHit.alive ? 0 : targetHit.getY()) + entry.getValue()[0].getHeight() / 2,
                    targetHit.alive ? targetHit : null);
            IronCloudsAssets.soundSimpleExplosion.play(GameSettings.getSoundVolume() * 0.7f);
        }

        if (targetsAndBombs.isTankHit(tankAndMissiles.tank))
        {
            tankAndMissiles.hitTank();
        }

        currentLevel.update(delta);

        // @TODO
        if (currentLevel.checkForWin())
        {
            currentLevelIndex++;
            if (currentLevelIndex >= levels.length)
            {
                Gdx.app.exit();
            }
            currentLevel = levels[currentLevelIndex];
            currentLevel.start();
        }
        else if (currentLevel.checkForLoss())
        {
            currentLevel.start();
        }
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        super.draw(layer, batch);
    }

    public void handleTouch(float x, float y)
    {
        tankAndMissiles.handleTouch(x, y);
    }

    public float getPlayerCameraX()
    {
        return tankAndMissiles.getPlayerTankX();
    }

    public int getTargetsCount()
    {
        return targetsAndBombs.targets.entitiesSize();
    }

    public int getAirborneMissilesCount()
    {
        return tankAndMissiles.missiles.entitiesSize();
    }

    public int getTankHealth()
    {
        return tankAndMissiles.tank.getHealth();
    }

    public int getTankMissilesLeft()
    {
        return tankAndMissiles.tank.getMissilesCount();
    }

    public int getTargetsSize()
    {
        return targetsAndBombs.targets.entitiesSize();
    }
}
