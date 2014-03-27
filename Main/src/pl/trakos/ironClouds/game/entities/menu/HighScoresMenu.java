package pl.trakos.ironClouds.game.entities.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsUtils;
import pl.trakos.ironClouds.game.entities.Menu;
import pl.trakos.lib.*;
import pl.trakos.lib.input.GameButton;
import pl.trakos.lib.input.IGameInput;

public class HighScoresMenu extends GameEntityMenu
{
    final GameButton backButton;

    public HighScoresMenu()
    {
        this.backButton = new GameButton(
            _.tr("menu.back"),
            (GameSettings.getResolutionWidth() - GameButton.getStandardButtonWidth()) / 2,
            20
        );
        buttons = new GameButton[]
        {
                backButton
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

    final float tableStartY = 390;
    final float positionNameX = 220;
    final float positionScoreX = 430;

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        super.draw(layer, batch);
        typeHighScores(batch);
    }

    public void typeHighScores(SpriteBatch batch)
    {
        float positionY = tableStartY;
        for (Tuple<String, Integer> score : GameSettings.getHighScores())
        {
            boolean isCurrentScore = false;
            if (score.a != null && !score.a.equals(""))
            {
                String name = score.a.length() > 11 ? score.a.substring(0, 10) : score.a;
                IronCloudsUtils.typeWrapped(batch, score.a, positionNameX, positionY, 150, BitmapFont.HAlignment.LEFT, !isCurrentScore);
            }
            if (score.b != null && score.b > 0)
            {
                IronCloudsUtils.typeWrapped(batch, Integer.toString(score.b), positionScoreX, positionY, 110, BitmapFont.HAlignment.RIGHT, !isCurrentScore);
            }
            positionY -= 28;
        }
    }
}
