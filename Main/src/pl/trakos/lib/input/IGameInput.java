package pl.trakos.lib.input;

import pl.trakos.lib.IGameEntity;

public interface IGameInput extends IGameEntity
{
    public boolean getActive();
    public void setActive(boolean value);
    public boolean getVisible();
    public void setVisible(boolean value);
}
