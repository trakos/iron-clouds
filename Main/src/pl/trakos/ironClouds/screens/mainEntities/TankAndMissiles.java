package pl.trakos.ironClouds.screens.mainEntities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.screens.mainEntities.tank.*;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameSettings;

/**
 * User: trakos
 * Date: 11.11.13
 * Time: 03:04
 */
public class TankAndMissiles extends GameEntity
{
    public Tank tank;
    public TankMissileContainer missiles;

    public TankAndMissiles()
    {
        tank = new Tank();
        missiles = new TankMissileContainer();
    }

    public void handleTouch(float touchPosX, float touchPosY)
    {
        if (touchPosY < GameSettings.groundPositionY)
        {
            tank.setDestinationX(touchPosX - 20);
        }
        else
        {
            tank.aimAt(touchPosX, touchPosY);
            if (tank.isReadyToShoot() && missiles.canSpawnAdditionalMissile())
            {
                missiles.add(tank.getTankGunOriginX(), tank.getTankGunOriginY(), touchPosX, touchPosY);
                tank.registerShot();
            }
        }
    }

    @Override
    public void update(float delta)
    {
        tank.update(delta);
        missiles.update(delta);
    }

    @Override
    public void draw(Camera camera, SpriteBatch batch)
    {
        missiles.draw(camera, batch);
        tank.draw(camera, batch);
    }
}
