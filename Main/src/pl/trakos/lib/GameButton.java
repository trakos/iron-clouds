package pl.trakos.lib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;

public class GameButton extends GameEntity
{
    String text;
    NinePatch textureRegion;
    NinePatch activeTextureRegion;

    float x;
    float y;
    float width = getStandardButtonWidth();
    float height = getStandardButtonHeight();
    public boolean active = false;

    public GameButton(String text, float x, float y)
    {
        textureRegion = IronCloudsAssets.textureHudButtonN;
        activeTextureRegion = IronCloudsAssets.textureHudButtonA;
        this.text = text;
        this.x = x;
        this.y = y;
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

    static public float getStandardButtonWidth()
    {
        return 400;
    }

    static public float getStandardButtonHeight()
    {
        return 80;
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
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerHud)
        {
            NinePatch ninePatch = active ? activeTextureRegion : textureRegion;
            ninePatch.draw(
                    batch,
                    getX(),
                    getY(),
                    getWidth(),
                    getHeight()
            );
            IronCloudsAssets.fontKenVector.setColor(new Color(1, 1, 1, .8f));
            IronCloudsAssets.fontKenVector.drawWrapped(
                    batch,
                    text,
                    getX(),
                    getY() + getHeight() / 2 + IronCloudsAssets.fontKenVector.getLineHeight() / 2 - getHeight() / 20
                    + (active ? -2 : 0),
                    getWidth(),
                    BitmapFont.HAlignment.CENTER
            );
            IronCloudsAssets.fontKenVector.setColor(Color.WHITE);
        }
    }

    @Override
    public void dispose()
    {

    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
