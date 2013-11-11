package pl.trakos.lib;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * User: trakos
 * Date: 10.11.13
 * Time: 02:40
 */
public abstract class GameEntity
{
    public abstract void update(float delta);
    public abstract void draw(Camera camera, SpriteBatch batch);
}
