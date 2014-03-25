package pl.trakos.ironClouds.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.ironClouds.game.entities.menu.*;
import pl.trakos.lib.*;
import pl.trakos.lib.input.GameButton;

public class Menu extends GameEntitiesContainer
{
    public enum CurrentMenu
    {
        CreditsMenu,
        HighScoresMenu,
        LevelsMenu,
        MainMenu,
        OptionsMenu,
    }

    static public Menu instance = new Menu();
    protected final CreditsMenu creditsMenu;
    protected final HighScoresMenu highScoresMenu;
    protected final LevelMenuButtons levelButtons;
    protected final MainMenuButtons menuButtons;
    protected final OptionsMenuButtons optionsMenuButtons;

    public CurrentMenu currentMenu = CurrentMenu.MainMenu;


    protected Menu()
    {
        creditsMenu = new CreditsMenu();
        highScoresMenu = new HighScoresMenu();
        levelButtons = new LevelMenuButtons();
        menuButtons = new MainMenuButtons();
        optionsMenuButtons = new OptionsMenuButtons();
        add(menuButtons);
    }

    public void filterAvailableLevels()
    {
        levelButtons.filterAvailableLevels();
    }

    @Override
    public GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType, Integer activeTouchId)
    {
        if (GameCoreEntity.instance.getGameState() != GameCoreEntity.GameState.MainMenu)
        {
            return GameTouchType.NotIntercepted;
        }

        return getCurrentMenu().handleTouch(x, y, previousTouchType, activeTouchId);
    }

    protected GameEntityMenu getCurrentMenu()
    {
        switch (currentMenu)
        {
            case CreditsMenu:
                return creditsMenu;
            case HighScoresMenu:
                return highScoresMenu;
            case LevelsMenu:
                return levelButtons;
            case MainMenu:
                return menuButtons;
            case OptionsMenu:
                return optionsMenuButtons;
            default:
                return null;
        }
    }

    @Override
    public void update(float delta)
    {
        if (GameCoreEntity.instance.getGameState() != GameCoreEntity.GameState.MainMenu)
        {
            return;
        }
        getCurrentMenu().update(delta);
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (GameCoreEntity.instance.getGameState() != GameCoreEntity.GameState.MainMenu)
        {
            return;
        }

        if (layer == GameLayers.LayerHud)
        {
            float width =
                currentMenu == CurrentMenu.CreditsMenu || currentMenu == CurrentMenu.OptionsMenu
                ? GameSettings.getCameraWidth() - 80
                : GameButton.getStandardButtonWidth() + 50;
            float positionX = (GameSettings.getCameraWidth() - width) / 2;

            IronCloudsAssets.textureHudPanelDark.setColor(new Color(0, 0, 0, .7f));
            IronCloudsAssets.textureHudPanelDark.draw(
                    batch,
                    positionX,
                    15,
                    width,
                    450
            );
            IronCloudsAssets.textureHudPanelLight.setColor(new Color(0, 0, 0, .3f));
            IronCloudsAssets.textureHudPanelLight.draw(
                    batch,
                    positionX,
                    15,
                    width,
                    410
            );
            IronCloudsAssets.fontKenVector.setColor(new Color(.8f, .8f, .8f, .8f));
            IronCloudsAssets.fontKenVector.drawWrapped(
                    batch,
                    "Iron Clouds",
                    positionX,
                    454,
                    width,
                    BitmapFont.HAlignment.CENTER
            );
            IronCloudsAssets.fontKenVector.setColor(Color.WHITE);

            getCurrentMenu().draw(layer, batch);
        }
    }

}
