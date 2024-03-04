package adpgame.mvcgame.strategy;

import adpgame.mvcgame.model.gameObjects.AbstractMissile;


public interface IMovingStrategy {
    String getName();
    void updatePosition(AbstractMissile missile);
}
