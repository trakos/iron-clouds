package pl.trakos.ironClouds.screens.mainEntities.enemies.targets;

import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.lib.TParticle;

public class Zeppelin extends AbstractTarget
{
    public Zeppelin()
    {
        speed = 50;
        texture = IronCloudsAssets.textureZeppelin;
        initPosition();
    }

    @Override
    protected float getOutOfMapTimeout()
    {
        return 1;
    }

    @Override
    protected int getInitialHp()
    {
        return 5;
    }

    @Override
    protected void addHitEffectParticle(int remainingHp)
    {
        switch (remainingHp)
        {
            case 4:
                particles.add(
                        new TParticle(
                                IronCloudsAssets.particleEffectGrayExhaust.obtain(),
                                (float) (getWidth() / 2 + (Math.random() - .5) * getWidth() / 3 * 2),
                                (float) (getHeight() / 2 + (Math.random() - .5) * getHeight() / 3 * 2)
                        )
                );
                break;
            case 3:
                particles.add(
                        new TParticle(
                                IronCloudsAssets.particleEffectGrayExhaust.obtain(),
                                (float) (getWidth() / 2 + (Math.random() - .5) * getWidth() / 3 * 2),
                                (float) (getHeight() / 2 + (Math.random() - .5) * getHeight() / 3 * 2)
                        )
                );
                break;
            case 2:
                particles.add(
                        new TParticle(
                                IronCloudsAssets.particleEffectGrayExhaust.obtain(),
                                (float) (getWidth() / 2 + (Math.random() - .5) * getWidth() / 3 * 2),
                                (float) (getHeight() / 2 + (Math.random() - .5) * getHeight() / 3 * 2)
                        )
                );
                break;
            case 1:
                particles.add(
                        new TParticle(
                                IronCloudsAssets.particleEffectExhaust.obtain(),
                                (float) (getWidth() / 2 + (Math.random() - .5) * getWidth() / 3 * 2),
                                (float) (getHeight() / 2 + (Math.random() - .5) * getHeight() / 3 * 2)
                        )
                );
                break;
        }
    }

    int bombBurstIndex = 0;
    @Override
    protected float getNextBombDelay()
    {
        if (bombBurstIndex >= 5)
        {
            bombBurstIndex = 0;
            return 4f;
        }
        bombBurstIndex++;
        return .75f;
    }
}
