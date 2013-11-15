package pl.trakos.ironClouds.screens.mainEntities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.*;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 07:15
 */
public class Background extends GameEntity
{
    Sprite groundSprite;

    public Background()
    {
        groundSprite = new Sprite(
                IronCloudsAssets.textureGround,
                0,
                0,
                GameSettings.getMapWidth(),
                IronCloudsAssets.textureGround.getHeight());

    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerBackground)
        {
            TGradient.sky.drawVertical(batch, 0, GameSettings.groundPositionY, GameSettings.getMapWidth(),
                    GameSettings.getMapHeight() - GameSettings.groundPositionY);
        }
        else if (layer == GameLayers.LayerGround)
        {
            groundSprite.draw(batch);
        }
    }

    @Override
    public void dispose()
    {
    }
}
