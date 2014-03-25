package pl.trakos.ironClouds.game.entities.menu;

import com.badlogic.gdx.Gdx;
import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.ironClouds.game.entities.Menu;
import pl.trakos.lib.GameEntityMenu;
import pl.trakos.lib.GameSettings;
import pl.trakos.lib.input.GameButton;
import pl.trakos.lib.input.IGameInput;

public class MainMenuButtons extends GameEntityMenu
{
    protected final GameButton playButton;
    protected final GameButton levelButton;
    protected final GameButton optionsButton;
    protected final GameButton highScoresButton;
    protected final GameButton creditsButton;
    protected final GameButton quitButton;


    public MainMenuButtons()
    {
        float positionX = (GameSettings.getCameraWidth() - GameButton.getStandardButtonWidth()) / 2;
        float positionY = 415;

        float mainButtonHeight = 80;
        float otherButtonHeight = 50;

        positionY -= mainButtonHeight + 10;
        this.playButton = new GameButton("play", positionX, positionY);
        playButton.setHeight(mainButtonHeight);
        positionY -= otherButtonHeight + 10;
        this.levelButton = new GameButton("choose level", positionX, positionY);
        levelButton.setHeight(otherButtonHeight);
        positionY -= otherButtonHeight + 10;
        this.optionsButton = new GameButton("settings", positionX, positionY);
        optionsButton.setHeight(otherButtonHeight);
        positionY -= otherButtonHeight + 10;
        this.highScoresButton = new GameButton("high scores", positionX, positionY);
        highScoresButton.setHeight(otherButtonHeight);
        positionY -= otherButtonHeight + 10;
        this.creditsButton = new GameButton("credits", positionX, positionY);
        creditsButton.setHeight(otherButtonHeight);
        positionY -= otherButtonHeight + 10;
        this.quitButton = new GameButton("quit", positionX, positionY);
        quitButton.setHeight(otherButtonHeight);

        buttons = new GameButton[]{
                playButton,
                levelButton,
                optionsButton,
                highScoresButton,
                creditsButton,
                quitButton
        };
    }


    @Override
    protected void inputClicked(IGameInput button)
    {
        if (button == playButton)
        {
            int reachedLevel = GameSettings.getReachedLevel(GameSettings.getGameDifficulty());
            GameCoreEntity.instance.startLevel(reachedLevel >= GameCoreEntity.getLevelsCount() ? 0 : reachedLevel);
        }
        else if (button == levelButton)
        {
            Menu.instance.currentMenu = Menu.CurrentMenu.LevelsMenu;
            Menu.instance.filterAvailableLevels();
        }
        else if (button == optionsButton)
        {
            Menu.instance.currentMenu = Menu.CurrentMenu.OptionsMenu;
        }
        else if (button == highScoresButton)
        {
            Menu.instance.currentMenu = Menu.CurrentMenu.HighScoresMenu;
        }
        else if (button == creditsButton)
        {
            Menu.instance.currentMenu = Menu.CurrentMenu.CreditsMenu;
        }
        else if (button == quitButton)
        {
            Gdx.app.exit();
        }
    }
}
