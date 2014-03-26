package pl.trakos.lib;

public interface IGameTouchHandler
{
    public abstract GameTouchType handleTouch(float x, float y, GameTouchType previousTouchType, Integer activeTouchId);
}
