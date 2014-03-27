package pl.trakos.lib.input;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.GameLayers;

public class GameRadio extends GameInputElement
{
    String text;
    TextureRegion radioCircleOff = IronCloudsAssets.textureHudRadioOff;
    TextureRegion radioCircleOn = IronCloudsAssets.textureHudRadioOn;
    public int id;
    public boolean checked;

    public GameRadio(String text, float x, float y)
    {
        width = getStandardButtonWidth();
        height = getStandardButtonHeight();

        this.text = text;
        this.x = x;
        this.y = y;
    }


    static public float getStandardButtonWidth()
    {
        return 200;
    }

    static public float getStandardButtonHeight()
    {
        return 36;
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (!visible) return;
        if (layer == GameLayers.LayerHud)
        {
            TextureRegion textureRegion = checked ? radioCircleOn : radioCircleOff;
            batch.draw(
                    textureRegion,
                    getX(),
                    getY(),
                    textureRegion.getRegionWidth(),
                    textureRegion.getRegionHeight()
            );
            float marginX = 20;
            IronCloudsAssets.fontPrimeRegular.setColor(new Color(1, 1, 1, .8f));
            IronCloudsAssets.fontPrimeRegular.drawWrapped(
                    batch,
                    text,
                    getX() + textureRegion.getRegionWidth() + marginX,
                    getY() + textureRegion.getRegionHeight() / 2 + IronCloudsAssets.fontPrimeRegular.getLineHeight() / 2 - getHeight() / 16
                    + (active ? -2 : 0),
                    getWidth() - textureRegion.getRegionHeight() - marginX,
                    BitmapFont.HAlignment.LEFT
            );
            IronCloudsAssets.fontPrimeRegular.setColor(Color.WHITE);
        }
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
