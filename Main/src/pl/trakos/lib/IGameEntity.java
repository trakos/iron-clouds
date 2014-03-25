package pl.trakos.lib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IGameEntity
{

    public abstract float getX();
    public abstract float getY();
    public abstract float getWidth();
    public abstract float getHeight();
    public abstract GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType, Integer activeTouchId);

    public abstract void update(float delta);
    public abstract void draw(GameLayers layer, SpriteBatch batch);
    public abstract void dispose();

    public abstract void entityPause();
}
