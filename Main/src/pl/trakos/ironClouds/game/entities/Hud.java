package pl.trakos.ironClouds.game.entities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameLayers;
import pl.trakos.lib.GameSettings;

public class Hud extends GameEntity
{
    static public Hud instance = new Hud();

    protected final TextureRegion heartTexture;
    protected final TextureRegion shellTexture;
    protected final TextureRegion emptyHeartTexture;

    public Hud()
    {
        heartTexture = IronCloudsAssets.textureHudHeart;
        emptyHeartTexture = IronCloudsAssets.textureHudHeartEmpty;
        shellTexture = IronCloudsAssets.textureShell;
    }

    @Override
    public void update(float delta)
    {
        if (timeTitleLeft > 0)
        {
            timeTitleLeft -= delta;
        }
    }

    float timeTitleLeft = 0;
    String titleText;

    public void showTitle(String text, float time)
    {
        timeTitleLeft = time;
        titleText = text;
    }

    public int health = 5;
    protected int maxHealth = 5;
    public int missiles = 0;

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerHud)
        {
            if (timeTitleLeft > 0)
            {
                IronCloudsAssets.fontDejavuBI.drawWrapped(
                        batch,
                        titleText,
                        0,
                        GameSettings.getCameraHeight() / 2 + 60,
                        GameSettings.getCameraWidth(),
                        BitmapFont.HAlignment.CENTER
                );
            }


            float positionX = 20;
            float positionY = 15;
            for (int k = 1; k <= maxHealth; k++)
            {
                batch.draw(
                        k <= health ? heartTexture : emptyHeartTexture,
                        positionX,
                        positionY,
                        heartTexture.getRegionWidth(),
                        heartTexture.getRegionHeight());
                positionX += heartTexture.getRegionWidth() + 10;
            }

            int missiles = Math.min(Math.max(this.missiles, 0), 999);
            int firstDigit = (int) Math.floor(missiles / 100);
            int secondDigit = (int) Math.floor((missiles - firstDigit * 100) / 10);
            int thirdDigit = missiles - firstDigit * 100 - secondDigit * 10;

            positionX = GameSettings.getCameraWidth() - 108;
            positionY = 28;

            batch.draw(
                    IronCloudsAssets.textureHudDigits.get(firstDigit),
                    positionX,
                    positionY - 8,
                    IronCloudsAssets.textureHudDigits.get(firstDigit).getRegionWidth(),
                    IronCloudsAssets.textureHudDigits.get(firstDigit).getRegionHeight()
            );
            positionX += 20;
            batch.draw(
                    IronCloudsAssets.textureHudDigits.get(secondDigit),
                    positionX,
                    positionY - 8,
                    IronCloudsAssets.textureHudDigits.get(secondDigit).getRegionWidth(),
                    IronCloudsAssets.textureHudDigits.get(secondDigit).getRegionHeight()
            );
            positionX += 20;
            batch.draw(
                    IronCloudsAssets.textureHudDigits.get(thirdDigit),
                    positionX,
                    positionY - 8,
                    IronCloudsAssets.textureHudDigits.get(thirdDigit).getRegionWidth(),
                    IronCloudsAssets.textureHudDigits.get(thirdDigit).getRegionHeight()
            );
            positionX += 20;
            batch.draw(
                    IronCloudsAssets.textureHudX,
                    positionX,
                    positionY - IronCloudsAssets.textureHudX.getRegionHeight() / 2,
                    IronCloudsAssets.textureHudX.getRegionWidth(),
                    IronCloudsAssets.textureHudX.getRegionHeight()
            );
            positionX += 24;
            batch.draw(
                    shellTexture,
                    positionX,
                    positionY - shellTexture.getRegionWidth() / 2 - 2,
                    0,
                    shellTexture.getRegionHeight() / 2,
                    shellTexture.getRegionWidth(),
                    IronCloudsAssets.textureShell.getRegionHeight(),
                    1,
                    1,
                    90);


        }
    }

    @Override
    public void dispose()
    {

    }
}
