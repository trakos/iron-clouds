package pl.trakos.lib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameEntityMenu extends GameEntitiesContainer
{
    protected GameButton[] buttons;

    protected void drawButtons(GameLayers layer, SpriteBatch batch)
    {
        for (GameButton button : buttons)
        {
            button.draw(layer, batch);
        }
        deactivateButtons();
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        super.draw(layer, batch);
        drawButtons(layer, batch);
    }

    protected void deactivateButtons()
    {
        for (GameButton button : buttons)
        {
            button.active = false;
        }
    }

    public GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType)
    {
        for (GameButton button : buttons)
        {
            if (
                    x > button.getX()
                    && y > button.getY()
                    && x < button.getX() + button.getWidth()
                    && y < button.getY() + button.getHeight()
                    )
            {
                button.active = true;
                if (previousTouchType == null || previousTouchType == GameTouchType.NotIntercepted)
                {
                    buttonClicked(button);
                }
                return GameTouchType.InterceptedByMenu;
            }
        }
        return GameTouchType.NotIntercepted;
    }

    abstract protected void buttonClicked(GameButton button);
}
