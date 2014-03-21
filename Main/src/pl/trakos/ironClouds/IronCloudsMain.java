package pl.trakos.ironClouds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import pl.trakos.ironClouds.game.GameScreen;
import pl.trakos.lib.GameSettings;


public class IronCloudsMain extends Game
{
    Screen gameScreen;

    @Override
    public void create()
    {
        IronCloudsAssets.loadAssets();

        IronCloudsAssets.music01spaceFighterLoop.setLooping(true);
        GameSettings.refreshMusicVolume();
        IronCloudsAssets.music01spaceFighterLoop.play();

        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
