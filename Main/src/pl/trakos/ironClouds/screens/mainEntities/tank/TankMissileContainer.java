package pl.trakos.ironClouds.screens.mainEntities.tank;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.lib.GameEntitiesContainer;
import pl.trakos.lib.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 22:12
 */
public class TankMissileContainer extends GameEntitiesContainer
{
    static final int maxMissileCount = 4;

    public TankMissileContainer()
    {
        super(maxMissileCount);
    }

    public void add(float initX, float initY, float destX, float destY)
    {
        entities.add(new TankMissile(initX, initY, destX, destY));
    }

    public boolean canSpawnAdditionalMissile()
    {
        return entities.size() < maxMissileCount;
    }
}
