package pl.trakos.ironClouds.game.entities.menu;

import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.ironClouds.game.entities.Menu;
import pl.trakos.lib.GameEntityMenu;
import pl.trakos.lib.GameSettings;
import pl.trakos.lib.input.GameButton;
import pl.trakos.lib.input.IGameInput;

public class LevelMenuButtons extends GameEntityMenu
{
    private final GameButton backButton;
    GameButton[] levelButtons = new GameButton[10];

    public LevelMenuButtons()
    {
        float positionX = (GameSettings.getCameraWidth() - GameButton.getStandardButtonWidth()) / 2;
        float positionY = 350;

        float levelWidth = 50;
        float levelHeight = 50;

        buttons = new GameButton[11];
        float currentX = positionX;
        int rowCount = 5;
        for (int i = 1; i <= 10; i++)
        {
            GameButton levelButton = new GameButton(Integer.toString(i), currentX, positionY);
            levelButton.setWidth(levelWidth);
            levelButton.setHeight(levelHeight);
            levelButtons[i-1] = levelButton;
            buttons[i-1] = levelButton;

            currentX += levelWidth + (GameButton.getStandardButtonWidth() - rowCount * levelWidth) / (rowCount  - 1);

            if (i % rowCount == 0)
            {
                currentX = positionX;
                positionY -= levelHeight + 10;
            }
        }

        positionY -= GameButton.getStandardButtonHeight() + 50;
        this.backButton = new GameButton("back", positionX, positionY);
        buttons[10] = this.backButton;
    }

    @Override
    protected void inputClicked(IGameInput button)
    {
        if (button == backButton)
        {
            Menu.instance.currentMenu = Menu.CurrentMenu.MainMenu;
        }
        else
        {
            for (int i = 0, levelButtonsLength = levelButtons.length; i < levelButtonsLength; i++)
            {
                GameButton levelButton = levelButtons[i];
                if (levelButton == button)
                {
                    GameCoreEntity.instance.startLevel(i);
                }
            }

        }

    }
}
