package adpgame.mvcgame.model;

import adpgame.mvcgame.model.gameObjects.GameObject;
import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.command.AbstractGameCommand;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.observer.IObservable;
import adpgame.mvcgame.strategy.IMovingStrategy;

import java.util.List;


public interface IGameModel extends IObservable {
    void update();
    Position getCannonPosition();
    void moveCannonUp();
    void moveCannonDown();
    void aimCannonUp();
    void aimCannonDown();
    void cannonPowerUp();
    void cannonPowerDown();
    void cannonShoot();
    List<AbstractMissile> getMissiles();
    List<GameObject> getGameObjects();
    IMovingStrategy getMovingStrategy();
    void toggleMovingStrategy();
    void toggleShootingMode();
    Object createMemento();
    void setMemento(Object memento);
    void registerCommand(AbstractGameCommand command);
    void undoLastCommand();
    void increaseMissiles();
    void decreaseMissiles();

    void increaseDifficulty();
    void reduceHP();
    void triggerMissileEnemyCollision(AbstractMissile missile, AbstractEnemy enemy);
    void triggerMissileItemCollision(AbstractMissile missile, Item item);
    int getHighScore();
}
