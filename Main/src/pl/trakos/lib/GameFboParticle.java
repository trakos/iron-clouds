package pl.trakos.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import pl.trakos.ironClouds.IronCloudsAssets;

import java.util.ArrayList;
import java.util.List;

public class GameFboParticle extends GameEntity
{
    static public GameFboParticle instance = new GameFboParticle();

    public ArrayList<ParticleEffectPool.PooledEffect> pooledEffectList = new ArrayList<ParticleEffectPool.PooledEffect>();
    FrameBuffer particleFbo = new FrameBuffer(Pixmap.Format.RGBA4444, GameSettings.getWidth(), GameSettings.getHeight(), false);
    SpriteBatch particleBatch = new SpriteBatch();
    TextureRegion particleFboRegion;


    public GameFboParticle()
    {
        particleFboRegion = new TextureRegion(particleFbo.getColorBufferTexture(), 0, 0, GameSettings.getWidth(), GameSettings.getHeight());
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
            pooledEffectList.get(i).update(delta);
            renderParticle(pooledEffectList.get(i));
            if (pooledEffectList.get(i).isComplete())
            {
                pooledEffectList.get(i).free();
                pooledEffectList.remove(i);
                i--;
            }
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
        particleFbo.begin();
        {
            particleBatch.begin();
            {
                particleEffect.draw(particleBatch);
            }
            particleBatch.end();
        }
        particleFbo.end();
    }

    @Override
    public void draw(Camera camera, SpriteBatch batch)
    {
        batch.setColor(1, 1, 1, .8f);
        batch.draw(particleFboRegion, 0, 0);
        batch.setColor(1, 1, 1, 1);
    }

    @Override
    public void dispose()
    {
        particleFbo.dispose();
        particleBatch.dispose();
    }
}
