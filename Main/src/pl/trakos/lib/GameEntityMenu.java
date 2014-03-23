package pl.trakos.lib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.lib.input.IGameInput;

public abstract class GameEntityMenu extends GameEntitiesContainer
{
    public IGameInput[] buttons;

    protected void drawButtons(GameLayers layer, SpriteBatch batch)
    {
        for (IGameInput button : buttons)
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
        for (IGameInput button : buttons)
        {
            button.setActive(false);
        }
    }

    public GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType)
    {
        GameTouchType gameTouchType = GameTouchType.NotIntercepted;
        for (IGameInput button : buttons)
        {
            if (button instanceof GameEntityMenu)
            {
                if (button.handleTouch(x, y, previousTouchType) == GameTouchType.NotIntercepted)
                {
                    break;
                }
            }
            else if (
                x > button.getX()
                && y > button.getY()
                && x < button.getX() + button.getWidth()
                && y < button.getY() + button.getHeight()
            )
            {
                button.setActive(true);
                if (previousTouchType == null || previousTouchType == GameTouchType.NotIntercepted)
                {
                    inputClicked(button);
                }
                return GameTouchType.InterceptedByMenu;
            }
        }
        return GameTouchType.NotIntercepted;
    }

    abstract protected void inputClicked(IGameInput button);
}
