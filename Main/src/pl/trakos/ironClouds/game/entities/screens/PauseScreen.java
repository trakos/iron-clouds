package pl.trakos.ironClouds.game.entities.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsUtils;
import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.ironClouds.game.entities.menu.PauseMenu;
import pl.trakos.lib.*;
import pl.trakos.lib.input.GameButton;

public class PauseScreen extends GameEntity
{
    protected final PauseMenu hudMenu;

    public PauseScreen()
    {
        hudMenu = new PauseMenu();
    }

    @Override
    public float getX()
    {
        return 0;
    }

    @Override
    public float getY()
    {
        return 0;
    }

    @Override
    public float getWidth()
    {
        return 0;
    }

    @Override
    public float getHeight()
    {
        return 0;
    }

    @Override
    public void update(float delta)
    {
        hudMenu.update(delta);
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerHud)
        {
            if (GameCoreEntity.instance.getGamePauseType() == GameCoreEntity.GamePauseType.InPauseMenu)
            {
                IronCloudsUtils.drawBlackingMask(batch);
                float width = GameButton.getStandardButtonWidth() + 50;
                IronCloudsUtils.drawMenuBox(batch, _.tr("game.pause"), (GameSettings.getCameraWidth() - width)
                                                                       / 2, 15, width, 450);
                hudMenu.draw(layer, batch);
            }
        }
    }

    public GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType, Integer activeTouchId)
    {
        if (
            previousTouchType == GameTouchType.InterceptedByGame
            || GameCoreEntity.instance.getGamePauseType() != GameCoreEntity.GamePauseType.InPauseMenu
        )
        {
            return GameTouchType.NotIntercepted;
        }
        return hudMenu.handleTouch(x, y, previousTouchType, activeTouchId);
    }

    @Override
    public void dispose()
    {

    }
}
