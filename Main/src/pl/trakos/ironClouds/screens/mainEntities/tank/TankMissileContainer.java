package pl.trakos.ironClouds.screens.mainEntities.tank;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.lib.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 22:12
 */
public class TankMissileContainer extends GameEntity
{
    public int maxMissileCount = 4;

    List<TankMissile> missiles = new ArrayList<TankMissile>();

    public void add(float initX, float initY, float destX, float destY)
    {
        missiles.add(new TankMissile(initX, initY, destX, destY));
    }

    public void add(TankMissile missile)
    {
        missiles.add(missile);
    }

    public boolean canSpawnAdditionalMissile()
    {
        return missiles.size() < maxMissileCount;
    }

    @Override
    public void update(float delta)
    {
        for (int i = 0; i < missiles.size(); i++)
        {
            missiles.get(i).update(delta);
            if (!missiles.get(i).alive)
            {
                missiles.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(Camera camera, SpriteBatch batch)
    {
        for (TankMissile missile : missiles)
        {
            missile.draw(camera, batch);
        }
    }
}
