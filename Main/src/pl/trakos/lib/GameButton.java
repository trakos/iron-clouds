package pl.trakos.lib;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.trakos.ironClouds.IronCloudsAssets;

public class GameButton extends GameEntity
{
    String text;
    TextureRegion textureRegion;
    TextureRegion activeTextureRegion;

    float x;
    float y;
    public boolean active = false;

    public GameButton(String text, float x, float y)
    {
        textureRegion = IronCloudsAssets.textureHudOscButtonN;
        activeTextureRegion = IronCloudsAssets.textureHudOscButtonA;
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

    @Override
    public float getWidth()
    {
        return textureRegion.getRegionWidth();
    }

    @Override
    public float getHeight()
    {
        return textureRegion.getRegionHeight();
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
            batch.draw(
                    active ? activeTextureRegion : textureRegion,
                    getX(),
                    getY(),
                    textureRegion.getRegionWidth(),
                    textureRegion.getRegionHeight()
            );
            IronCloudsAssets.fontKenVector.drawWrapped(
                    batch,
                    text,
                    getX(),
                    getY() + 35 + (active ? -2 : 0),
                    textureRegion.getRegionWidth(),
                    BitmapFont.HAlignment.CENTER
            );
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
