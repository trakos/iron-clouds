package pl.trakos.ironClouds.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.game.entities.decorations.Cloud;
import pl.trakos.lib.*;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 07:15
 */
public class Background extends GameEntitiesContainer
{
    Sprite groundSprite;
    final float wind = MathUtils.random(0, 1) == 0 ? -1 : 1;

    public Background()
    {
        super();
        groundSprite = new Sprite(
                IronCloudsAssets.textureGround,
                0,
                0,
                GameSettings.getMapWidth(),
                IronCloudsAssets.textureGround.getHeight());

        int random = MathUtils.random(5, 10);
        for (int i = 0; i < random; i++)
        {
            add(Cloud.randomizeNewCloud(wind, false));
        }
    }

    float randomlyAddCloudDelay = 1f;

    @Override
    public void update(float delta)
    {
        super.update(delta);
        if (randomlyAddCloudDelay < 0)
        {
            if (MathUtils.random(1, 100) > 95)
            {
                add(Cloud.randomizeNewCloud(wind, true));
            }
            randomlyAddCloudDelay = 1;
        }
        randomlyAddCloudDelay-=delta;
    }

    TGradient sky = new TGradient(
            new Color[] {
                    new Color(0xc5a497ff),
                    new Color(0xcaad9eff),
                    new Color(0xc2c5c2ff),
                    new Color(0xb5c8c9ff),
                    new Color(0x98baceff),
                    new Color(0x7ca7c8ff),
                    new Color(0x6b99bdff),
            },
            new float[] {
                    0,
                    .1f,
                    .23f,
                    .36f,
                    .56f,
                    .86f,
                    1,
            }
    );

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerBackground)
        {
            sky.drawVertical(batch, 0, GameSettings.groundPositionY, GameSettings.getMapWidth(),
                    GameSettings.getMapHeight() - GameSettings.groundPositionY);
            float scale = 1f;
            batch.draw(
                    IronCloudsAssets.textureBackground,
                    0,
                    50,
                    scale * IronCloudsAssets.textureBackground.getRegionWidth(),
                    scale * IronCloudsAssets.textureBackground.getRegionHeight());
        }
        else if (layer == GameLayers.LayerGround)
        {
            groundSprite.draw(batch);
        }
        super.draw(layer, batch);
    }

    @Override
    public void dispose()
    {
    }
}
