package pl.trakos.ironClouds.game.entities.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.IronCloudsUtils;
import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.ironClouds.game.enums.LossReason;
import pl.trakos.lib.*;
import pl.trakos.lib.input.GameButton;
import pl.trakos.lib.input.IGameInput;

public class LossScreen extends GameEntityMenu
{
    protected final GameButton restartButton;
    protected final GameButton mainMenuButton;
    protected final GameButton quitButton;
    private String lossReason;

    @Override
    public GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType, Integer activeTouchId)
    {
        if (GameCoreEntity.instance.getGamePauseType() != GameCoreEntity.GamePauseType.JustLost)
        {
            return GameTouchType.NotIntercepted;
        }
        return super.handleTouch(x, y, previousTouchType, activeTouchId);
    }

    public LossScreen()
    {
        float positionX = (GameSettings.getCameraWidth() - GameButton.getStandardButtonWidth()) / 2;
        float positionY = 215;

        restartButton = new GameButton(_.tr("pause.restart"), positionX, positionY);
        positionY -= GameButton.getStandardButtonHeight() + 10;
        mainMenuButton = new GameButton(_.tr("pause.mainMenu"), positionX, positionY);
        positionY -= GameButton.getStandardButtonHeight() + 10;
        quitButton = new GameButton(_.tr("pause.quit"), positionX, positionY);

        buttons = new GameButton[] {
                restartButton,
                mainMenuButton,
                quitButton
        };
    }

    @Override
    protected void inputClicked(IGameInput button)
    {
        if (button == restartButton)
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

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerHud && GameCoreEntity.instance.getGamePauseType() == GameCoreEntity.GamePauseType.JustLost)
        {
            IronCloudsUtils.drawBlackingMask(batch);
            float width = GameButton.getStandardButtonWidth() + 50;
            IronCloudsUtils.drawMenuBox(batch, _.tr("game.loss"), (GameSettings.getCameraWidth() - width)
                                                                   / 2, 15, width, 450);
            IronCloudsAssets.fontKenVector.setColor(new Color(.8f, .8f, .8f, .8f));
            IronCloudsAssets.fontKenVector.drawWrapped(
                    batch,
                    lossReason,
                    (GameSettings.getCameraWidth() - width) / 2 + 10,
                    405,
                    width - 20,
                    BitmapFont.HAlignment.CENTER
            );
            IronCloudsAssets.fontKenVector.setColor(Color.WHITE);
            super.draw(layer, batch);
        }
    }

    public void setLossReason(LossReason lossReason)
    {
        this.lossReason = lossReason.reason;
    }
}
