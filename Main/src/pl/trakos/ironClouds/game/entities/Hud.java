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
    protected final TextureRegion planeTexture;

    public Hud()
    {
        heartTexture = IronCloudsAssets.textureHudHeart;
        emptyHeartTexture = IronCloudsAssets.textureHudHeartEmpty;
        shellTexture = IronCloudsAssets.textureShell;
        planeTexture = IronCloudsAssets.texturePlane1;
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
    public int enemiesLeft = 0;

    protected float typeNumber(SpriteBatch batch, int number, float positionX, float positionY)
    {
        number = Math.min(Math.max(number, 0), 999);
        int firstDigit = (int) Math.floor(number / 100);
        int secondDigit = (int) Math.floor((number - firstDigit * 100) / 10);
        int thirdDigit = number - firstDigit * 100 - secondDigit * 10;

        batch.draw(
                IronCloudsAssets.textureHudDigits.get(firstDigit),
                positionX,
                positionY - 8,
                IronCloudsAssets.textureHudDigits.get(firstDigit).getRegionWidth(),
                IronCloudsAssets.textureHudDigits.get(firstDigit).getRegionHeight()
        );
        positionX += 24;
        batch.draw(
                IronCloudsAssets.textureHudDigits.get(secondDigit),
                positionX,
                positionY - 8,
                IronCloudsAssets.textureHudDigits.get(secondDigit).getRegionWidth(),
                IronCloudsAssets.textureHudDigits.get(secondDigit).getRegionHeight()
        );
        positionX += 24;
        batch.draw(
                IronCloudsAssets.textureHudDigits.get(thirdDigit),
                positionX,
                positionY - 8,
                IronCloudsAssets.textureHudDigits.get(thirdDigit).getRegionWidth(),
                IronCloudsAssets.textureHudDigits.get(thirdDigit).getRegionHeight()
        );
        positionX += 24;
        return positionX;
    }

    protected float drawX(SpriteBatch batch, float positionX, float positionY)
    {
        positionX += 4;
        batch.draw(
                IronCloudsAssets.textureHudX,
                positionX,
                positionY - IronCloudsAssets.textureHudX.getRegionHeight() / 2,
                IronCloudsAssets.textureHudX.getRegionWidth(),
                IronCloudsAssets.textureHudX.getRegionHeight()
        );
        positionX += 28;
        return positionX;
    }

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

            positionX = GameSettings.getCameraWidth() - 258;
            positionY = 28;
            positionX = typeNumber(batch, enemiesLeft, positionX, positionY);
            positionX = drawX(batch, positionX, positionY);
            positionX = drawEnemy(batch, positionX, positionY);
            positionX = typeNumber(batch, missiles, positionX, positionY);
            positionX = drawX(batch, positionX, positionY);
            positionX = drawShell(batch, positionX, positionY);
        }
    }

    private float drawEnemy(SpriteBatch batch, float positionX, float positionY)
    {
        batch.draw(
                planeTexture,
                positionX,
                positionY - planeTexture.getRegionWidth() / 4 - 2,
                0,
                planeTexture.getRegionHeight() / 4,
                planeTexture.getRegionWidth() / 2,
                planeTexture.getRegionHeight() / 2,
                1,
                1,
                90);
        positionX += planeTexture.getRegionHeight() / 2 + 10;
        return positionX;
    }

    private float drawShell(SpriteBatch batch, float positionX, float positionY)
    {
        batch.draw(
                shellTexture,
                positionX,
                positionY - shellTexture.getRegionWidth() / 2 - 2,
                0,
                shellTexture.getRegionHeight() / 2,
                shellTexture.getRegionWidth(),
                shellTexture.getRegionHeight(),
                1,
                1,
                90);
        positionX += shellTexture.getRegionHeight() + 10;
        return positionX;
    }

    @Override
    public void dispose()
    {

    }
}
