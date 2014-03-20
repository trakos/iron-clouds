package pl.trakos.lib;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 07:19
 */
public class GameSettings
{
    static public int getResolutionWidth()
    {
        return 800;
    }

    static public int getResolutionHeight()
    {
        return 480;
    }

    static public float getCameraWidth()
    {
        return camera.viewportWidth;
    }

    static public float getCameraHeight()
    {
        return camera.viewportHeight;
    }

    static public int getMapWidth()
    {
        return 800;
    }

    static public int getMapHeight()
    {
        return 480;
    }

    static public final int groundPositionY = 58;

    static private final OrthographicCamera camera = new OrthographicCamera();
    static public OrthographicCamera getCamera()
    {
        return camera;
    }
    static public float getCameraX()
    {
        return camera.position.x;
    }
    static public float getCameraY()
    {
        return camera.position.y;
    }
    static public float getCameraStartX()
    {
        return camera.position.x - getCameraWidth() / 2;
    }
    static public float getCameraStartY()
    {
        return camera.position.y - getCameraHeight() / 2;
    }

    public static int getMissilesPerHitPoint()
    {
        return 3;
    }
}
