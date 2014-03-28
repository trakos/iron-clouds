package pl.trakos.ironClouds.game.entities.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.trakos.ironClouds.IronCloudsAssets;
import pl.trakos.ironClouds.IronCloudsUtils;
import pl.trakos.ironClouds.game.GameCoreEntity;
import pl.trakos.ironClouds.game.entities.Menu;
import pl.trakos.ironClouds.game.entities.enemies.targets.AbstractTarget;
import pl.trakos.ironClouds.game.var.WinPoints;
import pl.trakos.lib.*;
import pl.trakos.lib.input.GameButton;
import pl.trakos.lib.input.IGameInput;

public class WinScreen extends GameEntityMenu
{
    protected final GameButton mainMenuButton;
    protected final GameButton nextButton;

    private WinPoints winPoints;
    private Integer nextLevel;

    @Override
    public GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType, Integer activeTouchId)
    {
        if (GameCoreEntity.instance.getGamePauseType() != GameCoreEntity.GamePauseType.JustWon)
        {
            return GameTouchType.NotIntercepted;
        }
        return super.handleTouch(x, y, previousTouchType, activeTouchId);
    }

    final float screenWidth = GameSettings.getCameraWidth();
    final float marginMaskX = 40;
    final float marginButtonX = 20;
    final int buttonCount = 2;
    final float maskWidth = screenWidth - 2 * marginMaskX;
    final float buttonWidth = (maskWidth - (buttonCount + 1) * marginButtonX) / buttonCount;
    public WinScreen()
    {

        float positionX = marginMaskX + marginButtonX;
        float positionY = 25;

        mainMenuButton = new GameButton(_.tr("pause.mainMenu"), positionX, positionY);
        mainMenuButton.setWidth(buttonWidth);
        positionX += mainMenuButton.getWidth() + marginButtonX;
        nextButton = new GameButton(_.tr("game.won.next"), positionX, positionY);
        nextButton.setWidth(buttonWidth);

        buttons = new GameButton[] {
                mainMenuButton,
                nextButton
        };

        textInputListener = new Input.TextInputListener()
        {
            @Override
            public void input(String text)
            {
                nick = text;
                GameSettings.setLastUsedNick(nick);
                GameSettings.addHighScore(nick, winPoints.getTotalPoints());
            }

            @Override
            public void canceled()
            {
                Gdx.input.getTextInput(textInputListener, _.tr("game.won.inputTitle"), GameSettings.getLastUsedNick());
            }
        };
    }

    @Override
    protected void inputClicked(IGameInput button)
    {
        if (button == mainMenuButton)
        {
            GameCoreEntity.instance.showMainMenu();
        }
        else if (nextLevel != null)
        {
            GameCoreEntity.instance.startLevel(nextLevel);
        }
        else
        {
            GameCoreEntity.instance.showMainMenu();
            Menu.instance.currentMenu = Menu.CurrentMenu.CreditsMenu;
        }
    }

    final float positionXStart = 70;
    final float firstColumnImageWidth = 30;
    final float positionXAlignedEquals = 210;
    @Override
    public void draw(GameLayers layer, SpriteBatch batch)
    {
        if (layer == GameLayers.LayerHud && GameCoreEntity.instance.getGamePauseType() == GameCoreEntity.GamePauseType.JustWon)
        {
            IronCloudsUtils.drawBlackingMask(batch);
            float width = GameButton.getStandardButtonWidth() + 50;
            IronCloudsUtils.drawMenuBox(batch, _.tr("game.won"), marginMaskX, 15, maskWidth, 450);

            if (this.winPoints != null)
            {
                float positionY = tableStartY;
                float positionX = positionXStart;

                positionX = drawHeart(batch, positionX, positionY);
                positionX = drawX(batch, positionX, positionY);
                positionX = IronCloudsUtils.typeNumber(batch, winPoints.healthLeft, 3, positionX, positionY, Color.WHITE);
                /*positionX = drawX(batch, positionX, positionY);
                positionX = IronCloudsUtils.typeNumber(batch, WinPoints.pointsPerHeart, 3, positionX, positionY, Color.WHITE);*/
                positionX = positionXAlignedEquals;
                positionX = drawEquals(batch, positionX, positionY);
                positionX = IronCloudsUtils.typeNumber(batch, winPoints.getPointsForHearts(), 4, positionX, positionY, Color.WHITE);

                positionX = positionXStart;
                positionY -= 40;

                positionX = drawShell(batch, positionX, positionY);
                positionX = drawX(batch, positionX, positionY);
                positionX = IronCloudsUtils.typeNumber(batch, winPoints.missilesLeft, 3, positionX, positionY, Color.WHITE);
                /*positionX = drawX(batch, positionX, positionY);
                positionX = IronCloudsUtils.typeNumber(batch, WinPoints.pointsPerMissile, 3, positionX, positionY, Color.WHITE);*/
                positionX = positionXAlignedEquals;
                positionX = drawEquals(batch, positionX, positionY);
                positionX = IronCloudsUtils.typeNumber(batch, winPoints.getPointsForMissiles(), 4, positionX, positionY, Color.WHITE);

                positionX = positionXStart;
                positionY -= 40;
                typeWrapped(batch, winPoints.gameDifficulty.getName(), positionX, positionY, positionXAlignedEquals - positionX, BitmapFont.HAlignment.RIGHT, false);
                positionX = positionXAlignedEquals;
                positionX = drawEquals(batch, positionX, positionY);
                positionX = IronCloudsUtils.typeNumber(batch, winPoints.getTotalDifficultyBonus(), 4, positionX, positionY, Color.WHITE);

                positionX = positionXStart;
                positionY -= 120;
                typeWrapped(batch, _.tr("game.won.total"), positionX, positionY, positionXAlignedEquals - positionX, BitmapFont.HAlignment.RIGHT, false);
                positionX = positionXAlignedEquals;
                positionX = drawEquals(batch, positionX, positionY);
                positionX = IronCloudsUtils.typeNumber(batch, winPoints.getTotalPoints(), 4, positionX, positionY, Color.WHITE);
            }

            typeHighScores(batch);
            super.draw(layer, batch);
        }
    }

    final float tableStartY = 390;
    final float positionNameX = 420;
    final float positionScoreX = 630;
    public void typeHighScores(SpriteBatch batch)
    {
        float positionY = tableStartY;
        for (Tuple<String, Integer> score : GameSettings.getHighScores())
        {
            boolean isCurrentScore = winPoints != null && nick != null && nick.equals(score.a) && score.b != null && score.b.equals(winPoints.getTotalPoints());
            if (score.a != null && !score.a.equals(""))
            {
                String name = score.a.length() > 11 ? score.a.substring(0, 10) : score.a;
                typeWrapped(batch, score.a, positionNameX, positionY, 150, BitmapFont.HAlignment.LEFT, !isCurrentScore);
            }
            if (score.b != null && score.b > 0)
            {
                typeWrapped(batch, Integer.toString(score.b), positionScoreX, positionY, 110, BitmapFont.HAlignment.RIGHT, !isCurrentScore);
            }
            positionY -= 28;
        }
    }

    String nick = null;
    final Input.TextInputListener textInputListener;
    public void setData(WinPoints winPointsParam, Integer nextLevel)
    {
        this.winPoints = winPointsParam;
        this.nextLevel = nextLevel;
        if (this.winPoints != null && GameSettings.isScoreHigh(this.winPoints.getTotalPoints()))
        {
            nick = null;

            Gdx.input.getTextInput(textInputListener, _.tr("game.won.inputTitle"), GameSettings.getLastUsedNick());
        }
    }

    public void typeWrapped(SpriteBatch batch, String text, float positionX, float positionY, float width, BitmapFont.HAlignment alignment, boolean makeDarker)
    {
        IronCloudsUtils.typeWrapped(batch, text, positionX, positionY, width, alignment, makeDarker);
    }

    private float drawEquals(SpriteBatch batch, float positionX, float positionY)
    {
        positionX += 10;
        batch.draw(
                IronCloudsAssets.textureHudEqualSign,
                positionX,
                positionY - IronCloudsAssets.textureHudEqualSign.getRegionHeight() / 2,
                IronCloudsAssets.textureHudEqualSign.getRegionWidth(),
                IronCloudsAssets.textureHudEqualSign.getRegionHeight()
        );
        positionX += IronCloudsAssets.textureHudEqualSign.getRegionWidth() + 10;
        return positionX;
    }

    private float drawX(SpriteBatch batch, float positionX, float positionY)
    {
        positionX += 4;
        batch.draw(
                IronCloudsAssets.textureHudX,
                positionX,
                positionY - IronCloudsAssets.textureHudX.getRegionHeight() / 2,
                IronCloudsAssets.textureHudX.getRegionWidth(),
                IronCloudsAssets.textureHudX.getRegionHeight()
        );
        positionX += 28;
        return positionX;
    }

    private float drawHeart(SpriteBatch batch, float positionX, float positionY)
    {
        batch.draw(
                IronCloudsAssets.textureHudHeart,
                positionX + (firstColumnImageWidth - IronCloudsAssets.textureHudHeart.getRegionWidth()) / 2,
                positionY - IronCloudsAssets.textureHudHeart.getRegionWidth() / 2,
                IronCloudsAssets.textureHudHeart.getRegionWidth(),
                IronCloudsAssets.textureHudHeart.getRegionHeight());
        positionX += IronCloudsAssets.textureHudHeart.getRegionWidth() + 2;
        return positionX + (firstColumnImageWidth - IronCloudsAssets.textureHudHeart.getRegionWidth());
    }

    private float drawEnemy(SpriteBatch batch, float positionX, float positionY, AbstractTarget.EnemyType enemyType)
    {
        batch.draw(
                enemyType.textureRegion,
                positionX + (firstColumnImageWidth - enemyType.textureRegion.getRegionWidth()) / 2,
                positionY - enemyType.textureRegion.getRegionWidth() / 2,
                enemyType.textureRegion.getRegionWidth() / 2,
                enemyType.textureRegion.getRegionHeight() / 2
        );
        positionX += enemyType.textureRegion.getRegionWidth() / 2;
        return positionX + (firstColumnImageWidth - enemyType.textureRegion.getRegionWidth()) / 2;
    }

    private float drawShell(SpriteBatch batch, float positionX, float positionY)
    {
        batch.draw(
                IronCloudsAssets.textureShell,
                positionX + (firstColumnImageWidth - IronCloudsAssets.textureShell.getRegionHeight()) / 2,
                positionY - IronCloudsAssets.textureShell.getRegionWidth() / 2,
                0,
                IronCloudsAssets.textureShell.getRegionHeight() / 2,
                IronCloudsAssets.textureShell.getRegionWidth(),
                IronCloudsAssets.textureShell.getRegionHeight(),
                1,
                1,
                90);
        positionX += IronCloudsAssets.textureShell.getRegionHeight() + 2;
        return positionX + (firstColumnImageWidth - IronCloudsAssets.textureShell.getRegionHeight());
    }
}
