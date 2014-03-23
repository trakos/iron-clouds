package pl.trakos.lib.input;

import pl.trakos.lib.GameEntity;

public abstract class GameInputElement extends GameEntity implements IGameInput
{

    public boolean active = false;
    float x;
    float y;
    float width;
    float height;

    @Override
    public boolean getActive()
    {
        return active;
    }

    @Override
    public void setActive(boolean value)
    {
        active = value;
    }

    @Override
    public float getX()
    {
        return x;
    }

    @Override
    public float getY()
    {
        return y;
    }

    public void setX(float x)
    {
        this.x = x;
    }
    public void setY(float y)
    {
        this.y = y;
    }

    @Override
    public float getWidth()
    {
        return width;
    }

    @Override
    public float getHeight()
    {
        return height;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void dispose()
    {

    }
}
