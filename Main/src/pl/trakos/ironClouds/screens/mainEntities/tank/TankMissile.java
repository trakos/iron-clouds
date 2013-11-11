package pl.trakos.ironClouds.screens.mainEntities.tank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.*;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 21:31
 */
public class TankMissile extends GameEntity
{
    public final float speed = 200f;
    public final int leftRightImageMargin = 7;
    public final int topBottomImageMargin = 12;

    public boolean alive = true;

    TVector2 velocityComponents;
    TVector2 position;
    TVector2 destination;
    double angle;
    boolean turnedForward;

    public TankMissile(float initX, float initY, float destX, float destY)
    {
        turnedForward = destX > initX;
        position = new TVector2(initX, initY);
        destination = new TVector2(destX, destY);

        double cathetus = destination.distance(destination.x, position.y);
        double hypotenuse = destination.distance(position);
        double angleRadians = hypotenuse == 0 ? Math.PI / 2 : Math.asin(cathetus / hypotenuse);
        angle = 180 * (angleRadians / Math.PI);

        velocityComponents = new TVector2((float) (speed * Math.cos(angleRadians) * (turnedForward ? 1 : -1)), (float) (speed * Math.sin(angleRadians)));
    }

    private float distance;
    private float vx;
    private float vy;
    @Override
    public void update(float delta)
    {
        position.x += velocityComponents.x * delta;
        position.y += velocityComponents.y * delta;

        alive = position.x >= 0 && position.x <= GameSettings.getWidth() && position.y >= 0 && position.y <= GameSettings.getHeight();
    }

    @Override
    public void draw(Camera camera, SpriteBatch batch)
    {
        if (alive)
        {
            batch.draw(
                    IronCloudsAssets.textureRegionShell,
                    position.x - leftRightImageMargin,
                    position.y - IronCloudsAssets.textureRegionShell.getRegionHeight() / 2,
                    leftRightImageMargin,
                    IronCloudsAssets.textureRegionShell.getRegionHeight() / 2,
                    IronCloudsAssets.textureRegionShell.getRegionWidth(),
                    IronCloudsAssets.textureRegionShell.getRegionHeight(),
                    turnedForward ? 1 : -1,
                    1,
                    (float) angle * (turnedForward ? 1 : -1));
        }
    }
}
