package pl.trakos.ironClouds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import pl.trakos.ironClouds.screens.GameScreen;


public class IronCloudsMain extends Game
{
    Screen gameScreen;




    @Override
    public void create()
    {
        loadAssets();


        IronCloudsAssets.music01spaceFighterLoop.setLooping(true);
        IronCloudsAssets.music01spaceFighterLoop.setVolume(0.4f);
        IronCloudsAssets.music01spaceFighterLoop.play();

        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    protected void loadAssets()
    {
        // music
        IronCloudsAssets.music01spaceFighterLoop = Gdx.audio.newMusic(Gdx.files.internal("music01_space_fighter_loop.mp3"));

        // sound
        IronCloudsAssets.soundHeli = Gdx.audio.newSound(Gdx.files.internal("heli.wav"));
        IronCloudsAssets.soundBomb = Gdx.audio.newSound(Gdx.files.internal("bomb.wav"));
        IronCloudsAssets.soundTank = Gdx.audio.newSound(Gdx.files.internal("tank.wav"));
        IronCloudsAssets.soundTankShot = Gdx.audio.newSound(Gdx.files.internal("tank_shot.wav"));
        IronCloudsAssets.soundSimpleExplosion = Gdx.audio.newSound(Gdx.files.internal("simple_explosion.wav"));

        // textures (non-atlased)
        Texture groundTexture = new Texture(Gdx.files.internal("ground.png"));
        groundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        // texture atlas
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images.atlas"));

        // textures
        IronCloudsAssets.textureGround = groundTexture;

        IronCloudsAssets.textureHudDigits = atlas.findRegions("hud/hud");
        IronCloudsAssets.textureHudX = atlas.findRegion("hud/x");
        IronCloudsAssets.textureHudHeart = atlas.findRegion("hud/heart");
        IronCloudsAssets.textureHudHeartEmpty = atlas.findRegion("hud/empty_heart");

        IronCloudsAssets.textureGun = atlas.findRegion("tank/gun");
        IronCloudsAssets.textureShell = atlas.findRegion("tank/shell");
        IronCloudsAssets.textureTank = atlas.findRegion("tank/tank");

        IronCloudsAssets.texturePlane1 = atlas.findRegion("enemies/plane1");
        IronCloudsAssets.texturePlane2 = atlas.findRegion("enemies/plane2");
        IronCloudsAssets.textureHeli = atlas.findRegion("enemies/heli");
        IronCloudsAssets.textureHeliTail = atlas.findRegion("enemies/heli_tail");
        IronCloudsAssets.textureZeppelin = atlas.findRegion("enemies/zeppelin");
        IronCloudsAssets.textureBomber = atlas.findRegion("enemies/bomber");
        IronCloudsAssets.textureBomb = atlas.findRegion("enemies/bomb1");

        IronCloudsAssets.textureCloud1 = atlas.findRegion("clouds/cloud1");
        IronCloudsAssets.textureCloud2 = atlas.findRegion("clouds/cloud2");
        IronCloudsAssets.textureCloud3 = atlas.findRegion("clouds/cloud3");
        IronCloudsAssets.textureCloud4 = atlas.findRegion("clouds/cloud4");
        IronCloudsAssets.textureCloud5 = atlas.findRegion("clouds/cloud5");
        IronCloudsAssets.textureCloud6 = atlas.findRegion("clouds/cloud6");
        IronCloudsAssets.textureCloud7 = atlas.findRegion("clouds/cloud7");

        // particles
        ParticleEffect exhaustEffect = new ParticleEffect();
        exhaustEffect.load(Gdx.files.internal("exhaust.particle"), atlas);
        IronCloudsAssets.particleEffectExhaust = new ParticleEffectPool(exhaustEffect, 1, 1);
        ParticleEffect grayExhaustEffect = new ParticleEffect();
        grayExhaustEffect.load(Gdx.files.internal("exhaust_gray.particle"), atlas);
        IronCloudsAssets.particleEffectGrayExhaust = new ParticleEffectPool(grayExhaustEffect, 1, 1);
        ParticleEffect explosionEffect = new ParticleEffect();
        explosionEffect.load(Gdx.files.internal("explosion.particle"), atlas);
        IronCloudsAssets.particleEffectExplosion = new ParticleEffectPool(explosionEffect, 1, 1);
        ParticleEffect smallExplosionEffect = new ParticleEffect();
        smallExplosionEffect.load(Gdx.files.internal("small_explosion.particle"), atlas);
        IronCloudsAssets.particleEffectSmallExplosion = new ParticleEffectPool(smallExplosionEffect, 1, 1);
    }
}
