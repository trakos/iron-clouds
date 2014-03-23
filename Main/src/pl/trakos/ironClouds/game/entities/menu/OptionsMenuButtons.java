package pl.trakos.ironClouds.game.entities.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.game.entities.Menu;
import pl.trakos.lib.GameDifficulty;
import pl.trakos.lib.GameEntityMenu;
import pl.trakos.lib.GameLayers;
import pl.trakos.lib.GameSettings;
import pl.trakos.lib.input.GameButton;
import pl.trakos.lib.input.GameRadio;
import pl.trakos.lib.input.GameRadioGroup;
import pl.trakos.lib.input.IGameInput;

public class OptionsMenuButtons extends GameEntityMenu
{
    final GameButton backButton;
    final GameRadioGroup difficultyRadio;

    public OptionsMenuButtons()
    {
        this.backButton = new GameButton(
                "back",
                (GameSettings.getResolutionWidth() - GameButton.getStandardButtonWidth()) / 2,
                20
        );
        difficultyRadio = new GameRadioGroup(new GameRadioGroup.Definition[]
        {
                new GameRadioGroup.Definition(GameDifficulty.Easy.ordinal(), "easy", 80, 300),
                new GameRadioGroup.Definition(GameDifficulty.Medium.ordinal(), "medium", 80, 220),
                new GameRadioGroup.Definition(GameDifficulty.Hard.ordinal(), "hard", 80, 140)
        })
        {
            @Override
            protected void radioChecked(GameRadio button)
            {
                GameSettings.setGameDifficulty(GameDifficulty.values()[button.id]);
                GameSettings.saveOptions();
            }
        };
        ((GameRadio)difficultyRadio.buttons[GameSettings.getGameDifficulty().ordinal()]).checked = true;
        buttons = new IGameInput[]
        {
                backButton,
                difficultyRadio
        };
    }

    @Override
    protected void inputClicked(IGameInput button)
    {
        if (button == backButton)
        {
            Menu.instance.currentMenu = Menu.CurrentMenu.MainMenu;
        }
    }


    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        IronCloudsAssets.fontKenVector.draw(
            batch,
            "Difficulty:",
            80,
            400
        );
        super.draw(layer, batch);
    }
}
