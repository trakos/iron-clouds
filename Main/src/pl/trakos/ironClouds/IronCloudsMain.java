package pl.trakos.ironClouds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
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

        IronCloudsAssets.soundTank = Gdx.audio.newSound(Gdx.files.internal("tank.wav"));
        IronCloudsAssets.soundTankShot = Gdx.audio.newSound(Gdx.files.internal("tank_shot.wav"));
        IronCloudsAssets.soundSimpleExplosion = Gdx.audio.newSound(Gdx.files.internal("simple_explosion.wav"));

        IronCloudsAssets.textureGround = new Texture(Gdx.files.internal("ground.gif"));
        IronCloudsAssets.textureGround.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        IronCloudsAssets.textureGun = new Texture(Gdx.files.internal("gun.gif"));
        IronCloudsAssets.textureShell = new Texture(Gdx.files.internal("shell.gif"));
        IronCloudsAssets.textureRegionShell = new TextureRegion(IronCloudsAssets.textureShell);
        IronCloudsAssets.textureTank = new Texture(Gdx.files.internal("tank.gif"));
        IronCloudsAssets.textureJumboJet = new Texture(Gdx.files.internal("jumbojet.png"));

        ParticleEffect exhaustEffect = new ParticleEffect();
        exhaustEffect.load(Gdx.files.internal("exhaust.particle"), Gdx.files.internal(""));
        IronCloudsAssets.particleEffectExhaust = new ParticleEffectPool(exhaustEffect, 1, 1);
        ParticleEffect explosionEffect = new ParticleEffect();
        explosionEffect.load(Gdx.files.internal("explosion.particle"), Gdx.files.internal(""));
        IronCloudsAssets.particleEffectExplosion = new ParticleEffectPool(explosionEffect, 1, 1);

        /*IronCloudsAssets.music01spaceFighterLoop.setLooping(true);
        IronCloudsAssets.music01spaceFighterLoop.setVolume(0.1f);
        IronCloudsAssets.music01spaceFighterLoop.play();*/

        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
