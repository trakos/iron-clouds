package pl.trakos.ironClouds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.ironClouds.game.GameScreen;
import pl.trakos.lib.GameSettings;


public class IronCloudsMain extends Game
{
    static Screen gameScreen;
    static boolean first = true;

    public IronCloudsMain()
    {

    }

    @Override
    public void create()
    {
        IronCloudsAssets.loadAssets();
        IronCloudsAssets.music01spaceFighterLoop.setLooping(true);
        GameSettings.refreshMusicVolume();
        IronCloudsAssets.music01spaceFighterLoop.play();
        if (first)
        {
            GameSettings.loadOptions();
            GameSettings.loadHighScores();
            Gdx.input.setCatchBackKey(true);
            Gdx.input.setCatchMenuKey(true);
            gameScreen = new GameScreen();

            first = false;
        }
        gameScreen.resume();
        setScreen(gameScreen);
        GameCoreEntity.instance.start();
    }

    @Override
    public void resume()
    {
        /*IronCloudsAssets.loadAssets();
        IronCloudsAssets.music01spaceFighterLoop.setLooping(true);
        GameSettings.refreshMusicVolume();
        IronCloudsAssets.music01spaceFighterLoop.play();*/

        super.resume();
    }

    @Override
    public void pause()
    {
        /*IronCloudsAssets.music01spaceFighterLoop.stop();
        IronCloudsAssets.unloadAssets();*/

        super.pause();
    }
}
