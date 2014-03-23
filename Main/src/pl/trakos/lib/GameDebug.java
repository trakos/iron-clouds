package pl.trakos.lib;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameDebug
{
    static ShapeRenderer shapeRenderer = new ShapeRenderer();

    static public void markPoint(SpriteBatch batch, Vector2 point, Color color)
    {
        batch.end();
        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(point.x, point.y, 4);
        shapeRenderer.end();
        batch.begin();
    }

    static public void markPoint(SpriteBatch batch, Vector2 point)
    {
        markPoint(batch, point, Color.WHITE);
    }

    static public void markPolygon(SpriteBatch batch, Polygon polygon)
    {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(polygon.getTransformedVertices());
        shapeRenderer.end();
        batch.begin();
    }

    static public void markRectangle(SpriteBatch batch, Rectangle rectangle)
    {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        shapeRenderer.end();
        batch.begin();
    }
}
