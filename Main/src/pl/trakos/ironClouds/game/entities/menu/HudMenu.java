package pl.trakos.ironClouds.game.entities.menu;

import com.badlogic.gdx.Gdx;
import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.lib.GameEntityMenu;
import pl.trakos.lib.GameSettings;
import pl.trakos.lib._;
import pl.trakos.lib.input.GameButton;
import pl.trakos.lib.input.IGameInput;


public class HudMenu extends GameEntityMenu
{
    protected final GameButton resumeButton;
    protected final GameButton restartButton;
    protected final GameButton mainMenuButton;
    protected final GameButton quitButton;

    public HudMenu()
    {
        float positionX = (GameSettings.getCameraWidth() - GameButton.getStandardButtonWidth()) / 2;
        float positionY = 340;

        resumeButton = new GameButton(_.tr("pause.resume"), positionX, positionY);
        positionY -= GameButton.getStandardButtonHeight() + 10;
        restartButton = new GameButton(_.tr("pause.restart"), positionX, positionY);
        positionY -= GameButton.getStandardButtonHeight() + 10;
        mainMenuButton = new GameButton(_.tr("pause.mainMenu"), positionX, positionY);
        positionY -= GameButton.getStandardButtonHeight() + 10;
        quitButton = new GameButton(_.tr("pause.quit"), positionX, positionY);

        buttons = new GameButton[]{
                resumeButton,
                restartButton,
                mainMenuButton,
                quitButton
        };
    }

    @Override
    protected void inputClicked(IGameInput button)
    {
        if (button == resumeButton)
        {
            GameCoreEntity.instance.changeGameState(GameCoreEntity.GameState.GameActive);
        }
        else if (button == restartButton)
        {
            GameCoreEntity.instance.changeGameState(GameCoreEntity.GameState.GameActive);
            GameCoreEntity.instance.restartCurrentLevel();
        }
        else if (button == mainMenuButton)
        {
            GameCoreEntity.instance.showMainMenu();
        }
        else
        {
            Gdx.app.exit();
        }
    }
}
