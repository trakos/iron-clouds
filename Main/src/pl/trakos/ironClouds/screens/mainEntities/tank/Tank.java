package pl.trakos.ironClouds.screens.mainEntities.tank;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.*;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 07:38
 */
public class Tank extends GameEntity
{
    final int tankMarginLeft = 0;
    final int tankMarginRight = 20;
    final int tankWidth = 44;
    final int tankMarginTop = 36;
    final int tankMarginBottom = 0;
    final int tankHeight = 20;
    final int gunWidth = 24;
    final Polygon tankPolygon = new Polygon(new float[] {
            tankMarginLeft, tankMarginTop,
            tankMarginLeft, tankMarginTop + tankHeight,
            tankMarginLeft + tankWidth, tankMarginTop,
            tankMarginLeft + tankWidth, tankMarginTop + tankHeight,
    });

    final float shootingDelay = 0.5f * 100;
    final float maxSpeedX = 50f;


    TVector2 tankPos = new TVector2(0, GameSettings.groundPositionY);
    TVector2 destinationPos = new TVector2(0, GameSettings.groundPositionY);
    TVector2 gunOriginPos = new TVector2(tankWidth / 2, GameSettings.groundPositionY + tankHeight);
    TVector2 gunMuzzlePos = new TVector2(tankWidth / 2 + gunWidth, GameSettings.groundPositionY + tankHeight);
    TVector2 aimPos = new TVector2(GameSettings.getWidth() / 2, GameSettings.groundPositionY);

    private float currentShootingDelay = 0;
    private float deltaX;
    private boolean turnedForward = false;
    private TextureRegion tankRegion;
    private TextureRegion gunRegion;

    public Tank()
    {
        tankRegion = new TextureRegion(IronCloudsAssets.textureTank);
        gunRegion = new TextureRegion(IronCloudsAssets.textureGun);


        IronCloudsAssets.soundTank.loop(0.8f);
        IronCloudsAssets.soundTank.pause();
    }

    @Override
    public Polygon getHitBox()
    {
        return tankPolygon;
    }

    boolean isPlayingEngineSound = false;

    public void ensureEngineSoundIs(boolean stateToggle)
    {
        if (stateToggle != isPlayingEngineSound)
        {
            if (stateToggle)
            {
                IronCloudsAssets.soundTank.resume();
            }
            else
            {
                IronCloudsAssets.soundTank.pause();
            }
            isPlayingEngineSound = stateToggle;
        }
    }

    @Override
    public void update(float delta)
    {
        deltaX = maxSpeedX * delta;
        if (Math.abs(this.destinationPos.x - this.tankPos.x) < deltaX)
        {
            this.tankPos.x = this.destinationPos.x;
            ensureEngineSoundIs(false);
        }
        else if (this.destinationPos.x < this.tankPos.x)
        {
            this.tankPos.x -= deltaX;
            ensureEngineSoundIs(true);
        }
        else
        {
            this.tankPos.x += deltaX;
            ensureEngineSoundIs(true);
        }

        gunOriginPos.x = tankPos.x + tankWidth / 2 + tankMarginLeft;

        turnedForward = aimPos.x >= tankPos.x;

        gunMuzzlePos.x = gunOriginPos.x;
        gunMuzzlePos.y = gunOriginPos.y + gunWidth * (turnedForward ? 1 : -1);
        gunMuzzlePos.rotate(gunOriginPos.x, gunOriginPos.y, (float) getAimAngle() * (turnedForward ? 1 : -1) - 90);

        if (currentShootingDelay >= 0)
        {
            currentShootingDelay -= deltaX;
        }
    }

    @Override
    public void draw(Camera camera, SpriteBatch batch)
    {
        batch.draw(
                gunRegion,
                this.tankPos.x + (tankWidth / 2),
                GameSettings.groundPositionY + 2,
                0,
                16,
                gunRegion.getRegionWidth(),
                gunRegion.getRegionHeight(),
                turnedForward ? 1 : -1,
                1,
                (float)getAimAngle() * (turnedForward ? 1 : -1));
        batch.draw(
                tankRegion,
                turnedForward ? this.tankPos.x : (this.tankPos.x + tankWidth + tankMarginLeft),
                GameSettings.groundPositionY,
                0,
                0,
                tankRegion.getRegionWidth(),
                tankRegion.getRegionHeight(),
                turnedForward ? 1 : -1,
                1,
                0);
    }

    @Override
    public void dispose()
    {
        // @todo
    }

    private double cathetus;
    private double hypotenuse;
    public double getAimAngle()
    {
        cathetus = aimPos.distance(aimPos.x, tankPos.y);
        hypotenuse = aimPos.distance(tankPos);
        if (hypotenuse == 0) return 90;
        return (Math.asin(cathetus / hypotenuse) / Math.PI) * 180;
    }

    public void registerShot()
    {
        currentShootingDelay = shootingDelay;
    }

    public boolean isReadyToShoot()
    {
        return currentShootingDelay < 0;
    }

    public void aimAt(float x, float y)
    {
        aimPos.x = x;
        aimPos.y = y;
    }

    public float getDestinationX()
    {
        return this.destinationPos.x;
    }

    public void setDestinationX(float destinationX)
    {
        this.destinationPos.x = Math.max(0, Math.min(destinationX, GameSettings.getWidth()));
    }

    public float getTankX()
    {
        return tankPos.x;
    }

    public float getTankY()
    {
        return tankPos.y;
    }

    public float getTankMuzzleX()
    {
        return gunMuzzlePos.x;
    }

    public float getTankMuzzleY()
    {
        return gunMuzzlePos.y;
    }

    public float getTankGunOriginX()
    {
        return gunOriginPos.x;
    }

    public float getTankGunOriginY()
    {
        return gunOriginPos.y;
    }
}
