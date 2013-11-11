package pl.trakos.ironClouds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.trakos.ironClouds.screens.GameScreen;


public class IronCloudsMain extends Game
{
    Screen gameScreen;




    @Override
    public void create()
    {
        //IronCloudsAssets.music01spaceFighterLoop = Gdx.audio.newMusic(Gdx.files.internal("music01_space_fighter_loop.mp3"));

        IronCloudsAssets.textureGround = new Texture(Gdx.files.internal("ground.gif"));
        IronCloudsAssets.textureGround.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        IronCloudsAssets.textureGun = new Texture(Gdx.files.internal("gun.gif"));
        IronCloudsAssets.textureShell = new Texture(Gdx.files.internal("shell.gif"));
        IronCloudsAssets.textureRegionShell = new TextureRegion(IronCloudsAssets.textureShell);
        IronCloudsAssets.textureTank = new Texture(Gdx.files.internal("tank.gif"));
        IronCloudsAssets.textureJumboJet = new Texture(Gdx.files.internal("jumbojet.gif"));

        //IronCloudsAssets.music01spaceFighterLoop.setLooping(true);
        //IronCloudsAssets.music01spaceFighterLoop.play();

        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
