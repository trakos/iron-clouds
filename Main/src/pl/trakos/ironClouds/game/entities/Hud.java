package pl.trakos.ironClouds.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.IronCloudsUtils;
import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameLayers;
import pl.trakos.lib.GameSettings;
import pl.trakos.lib.GameTouchType;

public class Hud extends GameEntity
{
    static public Hud instance = new Hud();

    protected final TextureRegion heartTexture;
    protected final TextureRegion shellTexture;
    protected final TextureRegion emptyHeartTexture;
    protected final TextureRegion planeTexture;
    protected final TextureRegion pauseTexture;

    private final float pauseX;
    private final float pauseY;

    public Hud()
    {
        heartTexture = IronCloudsAssets.textureHudHeart;
        emptyHeartTexture = IronCloudsAssets.textureHudHeartEmpty;
        shellTexture = IronCloudsAssets.textureShell;
        planeTexture = IronCloudsAssets.texturePlane1;
        pauseTexture = IronCloudsAssets.textureHudPause;

        pauseX = GameSettings.getCameraWidth() - pauseTexture.getRegionWidth() - 10;
        pauseY = GameSettings.groundPositionY + 10;
    }

    @Override
    public float getX()
    {
        return 0;
    }

    @Override
    public float getY()
    {
        return 0;
    }

    @Override
    public float getWidth()
    {
        return 0;
    }

    @Override
    public float getHeight()
    {
        return 0;
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
    public int maxHealth = 5;
    public int missiles = 0;
    public int enemiesLeft = 0;


    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerHud)
        {
            if (GameCoreEntity.instance.getGameState() == GameCoreEntity.GameState.MainMenu)
            {
                return;
            }

            float topHudMarginX = 10;
            float positionX = topHudMarginX;
            float positionY = GameSettings.getCameraHeight() - 10 - 23;
            positionX = drawHearts(batch, positionX, positionY);

            positionX = GameSettings.getCameraWidth() - 238 - topHudMarginX;
            positionY = positionY + 13;
            positionX = typeNumber(batch, enemiesLeft, positionX, positionY);
            positionX = drawX(batch, positionX, positionY);
            positionX = drawEnemy(batch, positionX, positionY);
            positionX = typeNumber(batch, missiles, positionX, positionY);
            positionX = drawX(batch, positionX, positionY);
            positionX = drawShell(batch, positionX, positionY);

            if (GameCoreEntity.instance.getGameState() == GameCoreEntity.GameState.GameActive
                    && timeTitleLeft > 0)
            {
                IronCloudsAssets.fontPrimeRegular.drawWrapped(
                        batch,
                        titleText,
                        0,
                        GameSettings.getCameraHeight() / 2 + 60,
                        GameSettings.getCameraWidth(),
                        BitmapFont.HAlignment.CENTER
                );
            }

            drawPauseButton(batch, pauseX, pauseY);
        }
    }

    private float drawHearts(SpriteBatch batch, float positionX, float positionY)
    {
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
        return positionX;
    }

    private float drawPauseButton(SpriteBatch batch, float positionX, float positionY)
    {
        batch.draw(
                pauseTexture,
                positionX,
                positionY,
                pauseTexture.getRegionWidth(),
                pauseTexture.getRegionHeight()
        );

        return positionX + pauseTexture.getRegionWidth();
    }

    private float typeNumber(SpriteBatch batch, int number, float positionX, float positionY)
    {
        return IronCloudsUtils.typeNumber(batch, number, 3, positionX, positionY, Color.WHITE);
    }

    private float drawX(SpriteBatch batch, float positionX, float positionY)
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

    public GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType, Integer activeTouchId)
    {
        if (previousTouchType == GameTouchType.InterceptedByGame)
        {
            return GameTouchType.NotIntercepted;
        }
        if (
                x > pauseX
                && y > pauseY
                && x < pauseX + pauseTexture.getRegionWidth()
                && y < pauseY + pauseTexture.getRegionHeight()
        )
        {
            if (previousTouchType == null)
            {
                if (GameCoreEntity.instance.getGameState() != GameCoreEntity.GameState.GameActive)
                {
                    GameCoreEntity.instance.changeGameState(GameCoreEntity.GameState.GameActive);
                }
                else
                {
                    GameCoreEntity.instance.changeGameState(GameCoreEntity.GameState.GamePaused, GameCoreEntity.GamePauseType.InPauseMenu);
                }
            }
            return GameTouchType.InterceptedByMenu;
        }
        return GameTouchType.NotIntercepted;
    }
}
