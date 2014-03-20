package pl.trakos.ironClouds.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.screens.mainEntities.Background;
import pl.trakos.ironClouds.screens.mainEntities.Hud;
import pl.trakos.ironClouds.screens.mainEntities.TankAndMissiles;
import pl.trakos.ironClouds.screens.mainEntities.TargetsAndBombs;
import pl.trakos.ironClouds.screens.mainEntities.enemies.targets.AbstractTarget;
import pl.trakos.lib.GameEntitiesContainer;
import pl.trakos.lib.GameEntity;
import pl.trakos.lib.GameFboParticle;
import pl.trakos.lib.GameLayers;

import java.util.Hashtable;
import java.util.Map;

public class GameCoreEntity extends GameEntitiesContainer
{

    Background background;
    TankAndMissiles tankAndMissiles;
    TargetsAndBombs targetsAndBombs;

    public GameCoreEntity()
    {
        background = new Background();
        tankAndMissiles = new TankAndMissiles();
        targetsAndBombs = new TargetsAndBombs();

        add(background);
        add(GameFboParticle.instance);
        add(tankAndMissiles);
        add(targetsAndBombs);
        add(GameFboParticle.foregroundInstance);
        add(Hud.instance);
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);

        Hashtable<GameEntity, GameEntity[]> entitiesHitByAnyOf = GameEntitiesContainer.getEntitiesHitByAnyOf(tankAndMissiles.missiles, targetsAndBombs.targets);
        for (Map.Entry<GameEntity, GameEntity[]> entry : entitiesHitByAnyOf.entrySet())
        {
            entry.getKey().alive = false;
            AbstractTarget targetHit = (AbstractTarget) entry.getValue()[0];
            targetHit.targetHit();
            GameFboParticle.instance.playParticleEffect(
                    targetHit.alive ? IronCloudsAssets.particleEffectSmallExplosion : IronCloudsAssets.particleEffectExplosion,
                    (targetHit.alive ? 0 : targetHit.getX()) + entry.getValue()[0].getWidth() / 2,
                    (targetHit.alive ? 0 : targetHit.getY()) + entry.getValue()[0].getHeight() / 2,
                    targetHit.alive ? targetHit : null);
            IronCloudsAssets.soundSimpleExplosion.play(0.7f);
        }

        if (targetsAndBombs.isTankHit(tankAndMissiles.tank))
        {
            tankAndMissiles.hitTank();
        }
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        super.draw(layer, batch);
    }

    public void handleTouch(float x, float y)
    {
        tankAndMissiles.handleTouch(x, y);
    }

    public float getPlayerCameraX()
    {
        return tankAndMissiles.getPlayerTankX();
    }
}
