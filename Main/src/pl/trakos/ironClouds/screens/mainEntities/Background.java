package pl.trakos.ironClouds.screens.mainEntities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.*;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 07:15
 */
public class Background extends GameEntity
{
    Rectangle groundRectangle;
    Sprite groundSprite;

    public Background()
    {
        groundSprite = new Sprite(
                IronCloudsAssets.textureGround,
                0,
                0,
                GameSettings.getWidth(),
                IronCloudsAssets.textureGround.getHeight());

    }

    @Override
    public void update(float delta)
    {

    }

    @Override
    public void draw(Camera camera, SpriteBatch batch)
    {
        groundSprite.draw(batch);
    }
}
