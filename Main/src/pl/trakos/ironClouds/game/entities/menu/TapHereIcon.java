package pl.trakos.ironClouds.game.entities.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.GameLayers;
import pl.trakos.lib.GameSettings;
import pl.trakos.lib.input.GameInputElement;

public class TapHereIcon extends GameInputElement
{
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    TextureRegion hudFinger = IronCloudsAssets.textureHudFinger;

    float currentDiffY = 0;
    int direction = 1;
    boolean invert = true;
    String text = "tap here to shoot";
    final float amplitude = 20f;
    final float speed = 20f;

    @Override
    public void update(float delta)
    {
        currentDiffY += speed * delta * direction;
        if (currentDiffY > amplitude)
        {
            direction = -1;
        }
        else if (currentDiffY <= 0)
        {
            direction = 1;
        }
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (!visible)
        {
            return;
        }
        batch.end();
        shapeRenderer.setProjectionMatrix(GameSettings.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0xA91818FF));
        shapeRenderer.circle(getX(), getY(), 24f);
        shapeRenderer.setColor(new Color(0xE02727FF));
        shapeRenderer.circle(getX(), getY(), 16f);
        shapeRenderer.end();
        batch.begin();
        batch.draw(
            hudFinger,
            getX() - hudFinger.getRegionWidth() / 2,
            getY() + (invert ? currentDiffY :  - currentDiffY - hudFinger.getRegionHeight()),
            hudFinger.getRegionWidth() / 2,
            hudFinger.getRegionHeight() / 2,
            hudFinger.getRegionWidth(),
            hudFinger.getRegionHeight(),
            1,
            1,
            invert ? 180 : 0
        );
        IronCloudsAssets.fontPrimeRegular.setColor(new Color(0xE02727FF));
        IronCloudsAssets.fontPrimeRegular.drawWrapped(
            batch,
            text,
            getX() - 300,
            getY() + 40 * (invert ? -.8f : 1.3f),
            600,
            BitmapFont.HAlignment.CENTER
        );
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void setInvert(boolean invert)
    {
        this.invert = invert;
    }
}
