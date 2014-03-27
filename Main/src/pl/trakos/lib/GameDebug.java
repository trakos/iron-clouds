package pl.trakos.lib;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import pl.trakos.ironClouds.IronCloudsUtils;

public class GameDebug
{
    static public void markPoint(SpriteBatch batch, Vector2 point, Color color)
    {
        batch.end();

        IronCloudsUtils.getShapeRenderer().setColor(color);
        IronCloudsUtils.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        IronCloudsUtils.getShapeRenderer().circle(point.x, point.y, 4);
        IronCloudsUtils.getShapeRenderer().end();
        batch.begin();
    }

    static public void markPoint(SpriteBatch batch, Vector2 point)
    {
        markPoint(batch, point, Color.WHITE);
    }

    static public void markPolygon(SpriteBatch batch, Polygon polygon)
    {
        batch.end();
        IronCloudsUtils.getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        IronCloudsUtils.getShapeRenderer().polygon(polygon.getTransformedVertices());
        IronCloudsUtils.getShapeRenderer().end();
        batch.begin();
    }

    static public void markRectangle(SpriteBatch batch, Rectangle rectangle)
    {
        batch.end();
        IronCloudsUtils.getShapeRenderer().begin(ShapeRenderer.ShapeType.Line);
        IronCloudsUtils.getShapeRenderer().rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        IronCloudsUtils.getShapeRenderer().end();
        batch.begin();
    }
}
