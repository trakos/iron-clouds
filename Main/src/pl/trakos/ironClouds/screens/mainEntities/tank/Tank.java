package pl.trakos.ironClouds.screens.mainEntities.tank;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameLayers;
import pl.trakos.lib.GameSettings;
import pl.trakos.lib.TVector2;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 07:38
 */
public class Tank extends GameEntity
{
    final int tankWidth;
    final int tankHeight;
    final int gunWidth;
    final int gunHeight;
    final Polygon tankPolygon;

    final float shootingDelay = 1f;
    final float maxSpeedX = 500f;


    TVector2 tankPos = new TVector2(0, GameSettings.groundPositionY);
    TVector2 destinationPos = new TVector2(0, GameSettings.groundPositionY);
    TVector2 gunOriginPos;
    TVector2 gunMuzzlePos = new TVector2(0, 0);
    TVector2 aimPos = new TVector2(GameSettings.getCameraWidth() / 2, GameSettings.groundPositionY);

    private float currentShootingDelay = 0;
    private float deltaX;
    private boolean turnedForward = false;
    private TextureRegion tankRegion;
    private TextureRegion gunRegion;

    public Tank()
    {
        tankRegion = new TextureRegion(IronCloudsAssets.textureTank);
        gunRegion = new TextureRegion(IronCloudsAssets.textureGun);

        tankWidth = tankRegion.getRegionWidth();
        tankHeight = tankRegion.getRegionHeight();
        gunWidth = gunRegion.getRegionWidth();
        gunHeight = gunRegion.getRegionHeight();
        tankPolygon = new Polygon(new float[]{
                0, 0,
                tankWidth, 0,
                tankWidth, tankHeight,
                0, tankHeight
        });
        gunOriginPos = new TVector2(0, GameSettings.groundPositionY + tankHeight - 10);

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

        turnedForward = aimPos.x >= gunOriginPos.x;
        gunOriginPos.x = tankPos.x + tankWidth / 2;
        gunMuzzlePos.x = gunOriginPos.x + gunWidth * (turnedForward ? 1 : -1);
        gunMuzzlePos.y = gunOriginPos.y;

        gunMuzzlePos.rotate(gunOriginPos.x, gunOriginPos.y, (float) getAimAngle() * (turnedForward ? 1 : -1));

        if (currentShootingDelay >= 0)
        {
            currentShootingDelay -= delta;
        }
        tankPolygon.setPosition(tankPos.x, tankPos.y);
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerMain)
        {
            batch.draw(
                    gunRegion,
                    gunOriginPos.x,
                    gunOriginPos.y - gunHeight / 2,
                    0,
                    gunHeight / 2,
                    gunWidth,
                    gunHeight,
                    turnedForward ? 1 : -1,
                    1,
                    (float)getAimAngle() * (turnedForward ? 1 : -1));
            batch.draw(
                    tankRegion,
                    turnedForward ? this.tankPos.x : (this.tankPos.x + tankWidth),
                    GameSettings.groundPositionY,
                    0,
                    0,
                    tankWidth,
                    tankHeight,
                    turnedForward ? 1 : -1,
                    1,
                    0);
        }
    }

    @Override
    public void dispose()
    {
    }

    private double cathetus;
    private double hypotenuse;
    public double getAimAngle()
    {
        cathetus = aimPos.distance(aimPos.x, gunOriginPos.y);
        hypotenuse = aimPos.distance(gunOriginPos);
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
        aimPos.y = Math.max(y, gunOriginPos.y);
    }

    public float getDestinationX()
    {
        return this.destinationPos.x;
    }

    public void setDestinationX(float destinationX)
    {
        this.destinationPos.x = Math.max(0, Math.min(destinationX, GameSettings.getMapWidth()));
    }

    public float getAimX()
    {
        return aimPos.x;
    }

    public float getAimY()
    {
        return aimPos.y;
    }

    @Override
    public float getX()
    {
        return getTankX();
    }

    @Override
    public float getY()
    {
        return getTankY();
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
