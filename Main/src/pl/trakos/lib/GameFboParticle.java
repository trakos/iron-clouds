package pl.trakos.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import java.util.ArrayList;

public class GameFboParticle extends GameEntity
{
    static public GameFboParticle instance = new GameFboParticle();

    public ArrayList<ParticleEffectPool.PooledEffect> pooledEffectList = new ArrayList<ParticleEffectPool.PooledEffect>();
    FrameBuffer particleFbo = new FrameBuffer(Pixmap.Format.RGBA4444, GameSettings.getResolutionWidth(), GameSettings.getResolutionHeight(), false);
    SpriteBatch particleBatch = new SpriteBatch();
    TextureRegion particleFboRegion;


    public GameFboParticle()
    {
        particleFboRegion = new TextureRegion(particleFbo.getColorBufferTexture(), 0, 0, GameSettings.getResolutionWidth(), GameSettings.getResolutionHeight());
        particleFboRegion.flip(false, true);
        particleBatch.setBlendFunction(-1, -1);
        particleFbo.begin();
        Gdx.gl20.glBlendFuncSeparate(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA, GL10.GL_ONE, GL10.GL_ONE);
        particleFbo.end();
    }

    @Override
    public void update(float delta)
    {
        particleFbo.begin();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        particleFbo.end();
        for (int i = 0; i < pooledEffectList.size(); i++)
        {
            if (pooledEffectList.get(i).isComplete())
            {
                pooledEffectList.get(i).free();
                pooledEffectList.remove(i);
                i--;
                continue;
            }
            pooledEffectList.get(i).update(delta);
        }
    }

    public void playParticleEffect(ParticleEffectPool particleEffectPool, float x, float y)
    {
        ParticleEffectPool.PooledEffect pooledEffect = particleEffectPool.obtain();
        pooledEffect.setPosition(x, y);
        pooledEffectList.add(pooledEffect);
    }

    public void renderParticle(ParticleEffectPool.PooledEffect particleEffect)
    {
        particleEffect.draw(particleBatch);
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerPrepareParticles)
        {
            for (ParticleEffectPool.PooledEffect pooledEffect : pooledEffectList)
            {
                renderParticle(pooledEffect);
            }
        }
        if (layer == GameLayers.LayerParticles)
        {
            batch.setColor(1, 1, 1, .8f);
            batch.draw(particleFboRegion, GameSettings.getCameraStartX(), GameSettings.getCameraStartY());
            batch.setColor(1, 1, 1, 1);
        }
    }

    public void beginPreparing()
    {
        particleFbo.begin();
        particleBatch.setProjectionMatrix(GameSettings.getCamera().combined);
        particleBatch.begin();
    }

    public void endPreparing()
    {
        particleBatch.end();
        particleFbo.end();
    }

    @Override
    public void dispose()
    {
        particleFbo.dispose();
        particleBatch.dispose();
    }
}
