package adpgame.mvcgame.abstractfactory;

import adpgame.mvcgame.model.gameObjects.AbstractCanon;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.Item;


public interface IGameObjectsFactory {
    AbstractCanon createCanon();
    AbstractMissile createMissile(double initAngle, int initVelocity);
    AbstractEnemy createEnemy();
    Item createItem();
}
