package pl.trakos.ironClouds.game.entities;

import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.game.entities.tank.Tank;
import pl.trakos.ironClouds.game.entities.tank.TankMissileContainer;
import pl.trakos.lib.GameEntitiesContainer;
import pl.trakos.lib.GameSettings;


public class TankAndMissiles extends GameEntitiesContainer
{
    public Tank tank;
    public TankMissileContainer missiles;
    final int missileWidth;
    final int missileHeight;

    public TankAndMissiles()
    {
        tank = new Tank();
        missiles = new TankMissileContainer();
        add(tank);
        add(missiles);

        missileWidth = IronCloudsAssets.textureShell.getRegionWidth();
        missileHeight = IronCloudsAssets.textureShell.getRegionHeight();
    }

    public void handleTouch(float touchPosX, float touchPosY)
    {
        if (touchPosY < tank.getTankGunOriginY())
        {
            tank.setDestinationX(touchPosX - 20);
        }
        else
        {
            tank.aimAt(touchPosX, touchPosY);
            if (tank.isReadyToShoot() && missiles.canSpawnAdditionalMissile())
            {
                missiles.add(tank.getTankGunOriginX(), tank.getTankGunOriginY(), tank.getAimX(), tank.getAimY());
                tank.registerShot();
                IronCloudsAssets.soundTankShot.play(0.4f * GameSettings.getSoundVolume());
            }
        }
    }

    public float getPlayerTankX()
    {
        return tank.getTankX();
    }

    public void hitTank()
    {
        tank.hit();
    }
}
