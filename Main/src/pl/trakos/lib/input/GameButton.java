package pl.trakos.lib.input;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.GameLayers;

public class GameButton extends GameInputElement
{
    String text;
    NinePatch textureRegion;
    NinePatch activeTextureRegion;

    public GameButton(String text, float x, float y)
    {
        width = getStandardButtonWidth();
        height = getStandardButtonHeight();

        textureRegion = IronCloudsAssets.textureHudButtonN;
        activeTextureRegion = IronCloudsAssets.textureHudButtonA;
        this.text = text;
        this.x = x;
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

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
