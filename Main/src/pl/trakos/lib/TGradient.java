package pl.trakos.lib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * User: trakos
 * Date: 14.11.13
 * Time: 07:32
 */
public class TGradient
{

    static TextureRegion textureRegion;
    static
    {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA4444);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        textureRegion = new TextureRegion(new Texture(pixmap));
    }
    static float[] vertices = new float[20];

    private static void drawGradient(SpriteBatch batch, float x, float y, float width, float height, Color a, Color b, boolean horizontal)
    {
        float ca = a.toFloatBits();
        float cb = b.toFloatBits();

        int idx = 0;
        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = horizontal ? ca : cb; // bottom left
        vertices[idx++] = textureRegion.getU(); //NOTE: texture coords origin is top left
        vertices[idx++] = textureRegion.getV2();

        vertices[idx++] = x;
        vertices[idx++] = y + height;
        vertices[idx++] = ca; // top left
        vertices[idx++] = textureRegion.getU();
        vertices[idx++] = textureRegion.getV();

        vertices[idx++] = x + width;
        vertices[idx++] = y + height;
        vertices[idx++] = horizontal ? cb : ca; // top right
        vertices[idx++] = textureRegion.getU2();
        vertices[idx++] = textureRegion.getV();

        vertices[idx++] = x + width;
        vertices[idx++] = y;
        vertices[idx++] = cb; // bottom right
        vertices[idx++] = textureRegion.getU2();
        vertices[idx++] = textureRegion.getV2();

        batch.draw(textureRegion.getTexture(), vertices, 0, vertices.length);
    }

    private static float currentPos;
    private static float dPos;
    private static void drawGradientVertical(SpriteBatch batch, float x, float y, float width, float height, Color[] colors, float[] positions)
    {
        currentPos = y;
        for (int i = 1; i < positions.length; i++)
        {
            dPos = (positions[i] - positions[i - 1]) * height;
            drawGradient(batch, x, currentPos, width, dPos, colors[i], colors[i - 1], false);
            currentPos += dPos;
        }
    }

    private static void drawGradientHorizontal(SpriteBatch batch, float x, float y, float width, float height, Color[] colors, float[] positions)
    {
        currentPos = x;
        for (int i = 1; i < positions.length; i++)
        {
            dPos = (positions[i] - positions[i - 1]) * width;
            drawGradient(batch, currentPos, y, dPos, height, colors[i - 1], colors[i], true);
            currentPos += dPos;
        }
    }

    private Color[] colors;
    private float[] positions;

    public TGradient(Color[] colors, float[] positions)
    {
        if (positions.length != colors.length || positions.length < 2 || positions[0] != 0 || positions[positions.length - 1] != 1)
        {
            throw new IllegalArgumentException();
        }
        this.colors = colors;
        this.positions = positions;
    }

    public void drawHorizontal(SpriteBatch batch, float x, float y, float w, float h)
    {
        drawGradientHorizontal(batch, x, y, w, h, colors, positions);
    }

    public void drawVertical(SpriteBatch batch, float x, float y, float w, float h)
    {
        drawGradientVertical(batch, x, y, w, h, colors, positions);
    }

    public static TGradient sky = new TGradient(
            new Color[] {
                    Color.valueOf("c5a497ff"),
                    Color.valueOf("caad9eff"),
                    Color.valueOf("c2c5c2ff"),
                    Color.valueOf("b5c8c9ff"),
                    Color.valueOf("98baceff"),
                    Color.valueOf("7ca7c8ff"),
                    Color.valueOf("6b99bdff"),
            },
            new float[] {
                    0,
                    .1f,
                    .23f,
                    .36f,
                    .56f,
                    .86f,
                    1,
            }
    );
}
