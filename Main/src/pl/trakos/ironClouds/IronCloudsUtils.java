package pl.trakos.ironClouds;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IronCloudsUtils
{

    static public void drawMenuBox(SpriteBatch batch, String title, float positionX, float positionY, float width, float height)
    {
        IronCloudsAssets.textureHudPanelDark.setColor(new Color(0, 0, 0, .7f));
        IronCloudsAssets.textureHudPanelDark.draw(
                batch,
                positionX,
                positionY,
                width,
                height
        );
        IronCloudsAssets.textureHudPanelLight.setColor(new Color(0, 0, 0, .3f));
        IronCloudsAssets.textureHudPanelLight.draw(
                batch,
                positionX,
                positionY,
                width,
                height - 40
        );
        IronCloudsAssets.fontKenVector.setColor(new Color(.8f, .8f, .8f, .8f));
        IronCloudsAssets.fontKenVector.drawWrapped(
                batch,
                title,
                positionX,
                positionY + height - 11,
                width,
                BitmapFont.HAlignment.CENTER
        );
        IronCloudsAssets.fontKenVector.setColor(Color.WHITE);
    }
}
