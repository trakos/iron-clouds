package pl.trakos.lib;

import com.badlogic.gdx.graphics.OrthographicCamera;
import pl.trakos.ironClouds.IronCloudsAssets;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 07:19
 */
public class GameSettings
{
    private static int resolutionWidth;
    private static int resolutionHeight;
    private static int screenWidth;
    private static int screenHeight;
    private static float musicVolume = 0;
    private static float soundVolume = .1f;
    private static GameDifficulty gameDifficulty = GameDifficulty.Medium;
    static private final OrthographicCamera camera = new OrthographicCamera();
    static public final int groundPositionY = 58;

    public static void setSoundVolume(float soundVolume)
    {
        GameSettings.soundVolume = soundVolume;
    }

    public static void setMusicVolume(float musicVolume)
    {
        GameSettings.musicVolume = musicVolume;
        refreshMusicVolume();
    }


    public static GameDifficulty getGameDifficulty()
    {
        return gameDifficulty;
    }

    public static void setGameDifficulty(GameDifficulty gameDifficulty)
    {
        GameSettings.gameDifficulty = gameDifficulty;
    }


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

    public static float getMusicVolume()
    {
        return musicVolume;
    }

    public static float getSoundVolume()
    {
        return soundVolume;
    }

    public static void refreshMusicVolume()
    {
        IronCloudsAssets.music01spaceFighterLoop.setVolume(GameSettings.getMusicVolume() * 0.4f);
    }

    public static void setScreenWidth(int screenWidth)
    {
        GameSettings.screenWidth = screenWidth;
    }

    public static void setScreenHeight(int screenHeight)
    {
        GameSettings.screenHeight = screenHeight;
    }

    public static int getScreenWidth()
    {
        return screenWidth;
    }

    public static int getScreenHeight()
    {
        return screenHeight;
    }

    public static void saveOptions()
    {
        // @TODO
    }
}
