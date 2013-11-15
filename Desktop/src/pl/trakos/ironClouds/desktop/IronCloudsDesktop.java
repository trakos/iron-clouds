package pl.trakos.ironClouds.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.trakos.ironClouds.IronCloudsMain;
import pl.trakos.lib.GameSettings;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 05:28
 */
public class IronCloudsDesktop
{
    public static void main(String[] args)
    {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Iron Clouds";
        cfg.useGL20 = true;
        cfg.width = GameSettings.getResolutionWidth();
        cfg.height = GameSettings.getResolutionHeight();
        cfg.vSyncEnabled = false;
        cfg.foregroundFPS = 2000;
        new LwjglApplication(new IronCloudsMain(), cfg);
    }
}
