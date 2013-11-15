package pl.trakos.lib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Disposable;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 02:40
 */
public abstract class GameEntity implements Disposable
{
    public Polygon getHitBox()
    {
        return null;
    }

    public boolean alive = true;

    public boolean checkIfHits(GameEntity entity)
    {
        return checkIfHits(entity.getHitBox());
    }

    public boolean checkIfHits(Polygon polygon)
    {
        Polygon polygon2 = getHitBox();
        return polygon2 != null && Intersector.overlapConvexPolygons(polygon, polygon2);
    }

    public float getX()
    {
        return 0;
    }

    public float getY()
    {
        return 0;
    }

    public int getWidth()
    {
        return 0;
    }

    public int getHeight()
    {
        return 0;
    }

    public abstract void update(float delta);
    public abstract void draw(GameLayers layer, SpriteBatch batch);
    public abstract void dispose();
}
