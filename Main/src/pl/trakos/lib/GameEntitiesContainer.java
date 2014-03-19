package pl.trakos.lib;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * User: trakos
 * Date: 11.11.13
 * Time: 18:17
 */
public class GameEntitiesContainer extends GameEntity
{
    static private Hashtable<GameEntity, GameEntity[]> hitPairsList = new Hashtable<GameEntity, GameEntity[]>();

    static public Hashtable<GameEntity, GameEntity[]> getEntitiesHitByAnyOf(GameEntitiesContainer entityContainer1, GameEntitiesContainer entityContainer2)
    {
        hitPairsList.clear();
        for (GameEntity targetEntity : entityContainer1.entities)
        {
            GameEntity[] hitsHelper = entityContainer2.getEntitiesHitBy(targetEntity);
            if (hitsHelper != null)
            {
                hitPairsList.put(targetEntity, hitsHelper);
            }
        }
        return hitPairsList;
    }

    protected List<GameEntity> entities;

    public GameEntitiesContainer()
    {
        entities = new ArrayList<GameEntity>();
    }

    public GameEntitiesContainer(int initialCapacity)
    {
        entities = new ArrayList<GameEntity>(initialCapacity);
    }

    public void add(GameEntity entity)
    {
        entities.add(entity);
    }

    @Override
    public void update(float delta)
    {
        for (int i = 0; i < entities.size(); i++)
        {
            entities.get(i).update(delta);
            if (!entities.get(i).alive)
            {
                entities.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        for (GameEntity entity : entities)
        {
            entity.draw(layer, batch);
        }
    }

    @Override
    public void dispose()
    {
        for (int i = entities.size() - 1; i > 0; i--)
        {
            entities.get(i).dispose();
            entities.remove(i);
        }
        entities = null;
    }

    private List<GameEntity> hitTargets = new ArrayList<GameEntity>();
    public GameEntity[] getEntitiesHitBy(GameEntity entity)
    {
        hitTargets.clear();
        for (GameEntity targetEntity : entities)
        {
            if (entity.checkIfHits(targetEntity))
            {
                hitTargets.add(targetEntity);
            }
        }
        return hitTargets.isEmpty() ? null : hitTargets.toArray(new GameEntity[hitTargets.size()]);
    }
}
