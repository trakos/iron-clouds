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
    }

    protected void deactivateButtons()
    {
        for (GameButton button : buttons)
        {
            button.active = false;
        }
    }

    public boolean handleButtonTouch(float x, float y, boolean justTouched)
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
                if (justTouched)
                {
                    buttonClicked(button);
                }
                return true;
            }
        }
        return false;
    }

    abstract protected void buttonClicked(GameButton button);
}
