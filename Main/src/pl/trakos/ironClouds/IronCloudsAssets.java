package pl.trakos.ironClouds;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 07:01
 */
public class IronCloudsAssets
{
    public static Music music01spaceFighterLoop;

    public static Sound soundBomb;
    public static Sound soundHeli;
    public static Sound soundTank;
    public static Sound soundTankShot;
    public static Sound soundSimpleExplosion;

    public static Texture textureGround;

    public static Array<TextureAtlas.AtlasRegion> textureHudDigits;
    public static TextureRegion textureHudX;
    public static TextureRegion textureHudHeart;
    public static TextureRegion textureHudHeartEmpty;

    public static TextureRegion textureGun;
    public static TextureRegion textureShell;
    public static TextureRegion textureTank;

    public static TextureRegion texturePlane1;
    public static TextureRegion texturePlane2;
    public static TextureRegion textureHeli;
    public static TextureRegion textureHeliTail;
    public static TextureRegion textureZeppelin;
    public static TextureRegion textureBomber;
    public static TextureRegion textureBomb;

    public static TextureRegion textureCloud1;
    public static TextureRegion textureCloud2;
    public static TextureRegion textureCloud3;
    public static TextureRegion textureCloud4;
    public static TextureRegion textureCloud5;
    public static TextureRegion textureCloud6;
    public static TextureRegion textureCloud7;

    public static ParticleEffectPool particleEffectExhaust;
    public static ParticleEffectPool particleEffectGrayExhaust;
    public static ParticleEffectPool particleEffectExplosion;
    public static ParticleEffectPool particleEffectSmallExplosion;
}
