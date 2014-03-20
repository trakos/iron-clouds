package pl.trakos.ironClouds.game.entities.tank;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.*;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 21:31
 */
public class TankMissile extends GameEntity
{
    static final float speed = 400f;
    static final int leftRightImageMargin = 7;
    static final int topBottomImageMargin = 12;
    static final int imageWidth = 18;
    static final int imageHeight = 8;
    static final float[] missilePolygonVertices = new float[] {
            leftRightImageMargin, topBottomImageMargin,
            leftRightImageMargin, topBottomImageMargin + imageHeight,
            leftRightImageMargin + imageWidth, topBottomImageMargin,
            leftRightImageMargin + imageWidth, topBottomImageMargin + imageHeight,
    };
    public Polygon missilePolygon = new Polygon(new float[8]);

    TVector2 velocityComponents;
    TVector2 position;
    TVector2 destination;
    TVector2 missileBottomTip;
    double angle;
    boolean turnedForward;
    ParticleEffectPool.PooledEffect exhaustEffect;

    public float getPositionX()
    {
        return position.x;
    }

    public float getPositionY()
    {
        return position.y;
    }

    public TankMissile(float initX, float initY, float destX, float destY)
    {
        turnedForward = destX > initX;
        position = new TVector2(initX, initY);
        destination = new TVector2(destX, destY);
        missileBottomTip = new TVector2(leftRightImageMargin, topBottomImageMargin);

        double cathetus = destination.distance(destination.x, position.y);
        double hypotenuse = destination.distance(position);
        double angleRadians = hypotenuse == 0 ? Math.PI / 2 : Math.asin(cathetus / hypotenuse);
        angle = 180 * (angleRadians / Math.PI);

        velocityComponents = new TVector2((float) (speed * Math.cos(angleRadians) * (turnedForward ? 1 : -1)), (float) (speed * Math.sin(angleRadians)));
        missilePolygon.setVertices(missilePolygonVertices);
        missilePolygon.rotate((float) angle);

        exhaustEffect = IronCloudsAssets.particleEffectExhaust.obtain();
    }

    @Override
    public Polygon getHitBox()
    {
        return missilePolygon;
    }

    private float distance;
    private float vx;
    private float vy;
    @Override
    public void update(float delta)
    {
        position.x += velocityComponents.x * delta;
        position.y += velocityComponents.y * delta;

        alive = alive && position.x >= 0 && position.x <= GameSettings.getMapWidth() && position.y >= 0 && position.y <= GameSettings.getMapHeight();

        missilePolygon.setPosition(position.x, position.y);
        exhaustEffect.setPosition(position.x, position.y);
        exhaustEffect.update(delta);
    }

    @Override
    public void dispose()
    {
        exhaustEffect.dispose();
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (!alive)
        {
            return;
        }
        else if (layer == GameLayers.LayerPrepareParticles)
        {
            GameFboParticle.instance.renderParticle(exhaustEffect);
        }
        else if (layer == GameLayers.LayerMain)
        {
            batch.draw(
                    IronCloudsAssets.textureShell,
                    position.x - leftRightImageMargin,
                    position.y - IronCloudsAssets.textureShell.getRegionHeight() / 2,
                    leftRightImageMargin,
                    IronCloudsAssets.textureShell.getRegionHeight() / 2,
                    IronCloudsAssets.textureShell.getRegionWidth(),
                    IronCloudsAssets.textureShell.getRegionHeight(),
                    turnedForward ? 1 : -1,
                    1,
                    (float) angle * (turnedForward ? 1 : -1));
        }
    }
}
