package pl.trakos.ironClouds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import pl.trakos.ironClouds.game.GameScreen;


public class IronCloudsMain extends Game
{
    Screen gameScreen;

    @Override
    public void create()
    {
        IronCloudsAssets.loadAssets();

        IronCloudsAssets.music01spaceFighterLoop.setLooping(true);
        IronCloudsAssets.music01spaceFighterLoop.setVolume(0.4f);
        IronCloudsAssets.music01spaceFighterLoop.play();

        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
