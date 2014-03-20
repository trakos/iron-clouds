package pl.trakos.ironClouds.game.entities.enemies;

import pl.trakos.lib.GameEntitiesContainer;

public class BombsContainer extends GameEntitiesContainer
{
    public BombsContainer()
    {
    }

    public void add(float initX, float initY, float planeSpeed, boolean planeDirection)
    {
        add(new PlaneBomb(initX, initY, planeSpeed, planeDirection));
    }
}
