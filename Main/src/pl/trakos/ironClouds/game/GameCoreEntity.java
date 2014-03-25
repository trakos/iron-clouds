package pl.trakos.ironClouds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.game.entities.*;
import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.ironClouds.game.levels.*;
import pl.trakos.lib.*;

import java.util.Hashtable;
import java.util.Map;

public class GameCoreEntity extends GameEntitiesContainer
{

    public enum GameState
    {
        MainMenu,
        GameActive,
        GamePausedInMenu,
        GamePausedByOSMainMenu,
        GamePausedByOSGame,
    }

    static AbstractLevel[] levels = new AbstractLevel[]
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
    static public GameCoreEntity instance = new GameCoreEntity();
    static public int getLevelsCount()
    {
        return levels.length;
    }

    GameState gameState = GameState.GameActive;
    Menu menu;
    Background background;
    TankAndMissiles tankAndMissiles;
    TargetsAndBombs targetsAndBombs;
    AbstractLevel currentLevel;
    int currentLevelIndex = 0;

    protected GameCoreEntity()
    {
        menu = Menu.instance;
        background = new Background();
        tankAndMissiles = new TankAndMissiles();
        targetsAndBombs = new TargetsAndBombs();

        add(background);
        add(GameFboParticle.instance);
        add(tankAndMissiles);
        add(targetsAndBombs);
        add(GameFboParticle.foregroundInstance);
        add(Hud.instance);
        add(menu);
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void showRandomlyPlacedEnemiesForBackground()
    {
        resetGame(1);
        targetsAndBombs.targets.randomlyPlaceEnemiesForBackground();
        targetsAndBombs.targets.randomlyPlaceEnemiesForBackground();
    }

    public void changeGameState(GameState newGameState)
    {
        gameState = newGameState;
    }

    public void start()
    {
        showMainMenu();
    }

    public void startLevel(int levelNumber)
    {
        changeGameState(GameState.GameActive);
        if (levelNumber > levels.length)
        {
            throw new RuntimeException("Level number too high!");
        }
        currentLevelIndex = levelNumber;
        currentLevel = levels[currentLevelIndex];
        currentLevel.start();
    }

    public void showMainMenu()
    {
        showRandomlyPlacedEnemiesForBackground();
        menu.currentMenu = Menu.CurrentMenu.MainMenu;
        changeGameState(GameState.MainMenu);
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
        tankAndMissiles.tank.setMaxHealth(GameSettings.getGameDifficulty().maxHealth);
        tankAndMissiles.tank.setHealth(GameSettings.getGameDifficulty().maxHealth);
        tankAndMissiles.tank.setMissiles(missilesLeft);
    }

    @Override
    public void update(float delta)
    {
        if (gameState != GameState.GameActive)
        {
            if (gameState == GameState.MainMenu)
            {
                Menu.instance.update(delta);
            }
            else if (gameState == GameState.GamePausedInMenu)
            {
                Hud.instance.update(delta);
            }
            return;
        }
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
            GameSettings.setReachedLevel(GameSettings.getGameDifficulty(), currentLevelIndex);
            GameSettings.saveOptions();
            if (currentLevelIndex >= levels.length)
            {
                Gdx.app.exit();
                return;
            }
            currentLevel = levels[currentLevelIndex];
            currentLevel.start();
        }
        else if (currentLevel.checkForLoss())
        {
            restartCurrentLevel();
        }
    }

    public void restartCurrentLevel()
    {
        currentLevel.start();
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        super.draw(layer, batch);
    }

    public GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType, Integer activeTouchId)
    {
        if (gameState == GameState.MainMenu)
        {
            return menu.handleTouch(x, y, previousTouchType, activeTouchId);
        }
        else
        {
            GameTouchType hudInterception = Hud.instance.handleTouch(x, y, previousTouchType, activeTouchId);
            if (hudInterception != GameTouchType.NotIntercepted)
            {
                return hudInterception;
            }
            if (gameState == GameState.GameActive)
            {
                return tankAndMissiles.handleTouch(x, y, previousTouchType, activeTouchId);
            }
            return GameTouchType.NotIntercepted;
        }
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
