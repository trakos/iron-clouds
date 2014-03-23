package pl.trakos.lib.input;

import pl.trakos.lib.GameEntityMenu;

public abstract class GameRadioGroup extends GameEntityMenu implements IGameInput
{
    static public class Definition
    {
        public int id;
        public String text;
        public float x;
        public float y;

        public Definition(int id, String text, float x, float y)
        {
            this.id = id;
            this.text = text;
            this.x = x;
            this.y = y;
        }
    }

    public GameRadioGroup(Definition[] definitions)
    {
        buttons = new IGameInput[definitions.length];
        int k = 0;
        for (Definition definition : definitions)
        {
            GameRadio gameRadio = new GameRadio(definition.text, definition.x, definition.y);
            gameRadio.id = definition.id;
            buttons[k++] = gameRadio;
        }
    }

    @Override
    protected void inputClicked(IGameInput button)
    {
        for (IGameInput iGameInput : buttons)
        {
            if (iGameInput instanceof GameRadio)
            {
                ((GameRadio) iGameInput).checked = false;
            }
        }
        ((GameRadio) button).checked = true;
        radioChecked((GameRadio) button);
    }

    @Override
    public boolean getActive()
    {
        return false;
    }

    @Override
    public void setActive(boolean value)
    {

    }

    abstract protected void radioChecked(GameRadio button);
}
