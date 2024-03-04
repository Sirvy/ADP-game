package adpgame.mvcgame.model;

import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.command.AbstractGameCommand;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.GameObject;
import adpgame.mvcgame.observer.AspectEnum;
import adpgame.mvcgame.observer.IObserver;
import adpgame.mvcgame.strategy.IMovingStrategy;

import java.util.List;


public class NullGameModel implements IGameModel {

    @Override
    public void update() {

    }


    @Override
    public Position getCannonPosition() {
        return null;
    }


    @Override
    public void moveCannonUp() {

    }


    @Override
    public void moveCannonDown() {

    }


    @Override
    public void aimCannonUp() {

    }


    @Override
    public void aimCannonDown() {

    }


    @Override
    public void cannonPowerUp() {

    }


    @Override
    public void cannonPowerDown() {

    }


    @Override
    public void cannonShoot() {

    }


    @Override
    public List<AbstractMissile> getMissiles() {
        return null;
    }


    @Override
    public List<GameObject> getGameObjects() {
        return null;
    }


    @Override
    public IMovingStrategy getMovingStrategy() {
        return null;
    }


    @Override
    public void toggleMovingStrategy() {

    }


    @Override
    public void toggleShootingMode() {

    }


    @Override
    public Object createMemento() {
        return null;
    }


    @Override
    public void setMemento(Object memento) {

    }


    @Override
    public void registerCommand(AbstractGameCommand command) {

    }


    @Override
    public void undoLastCommand() {

    }


    @Override
    public void increaseMissiles() {

    }


    @Override
    public void decreaseMissiles() {

    }


    @Override
    public void increaseDifficulty() {

    }


    @Override
    public void reduceHP() {

    }


    @Override
    public void triggerMissileEnemyCollision(AbstractMissile missile, AbstractEnemy enemy) {

    }


    @Override
    public void triggerMissileItemCollision(AbstractMissile missile, Item item) {

    }


    @Override
    public int getHighScore() {
        return 0;
    }


    @Override
    public void registerObserver(IObserver observer, AspectEnum aspectEnum) {

    }


    @Override
    public void unregisterObserver(IObserver observer) {

    }


    @Override
    public void unregisterObserver(AspectEnum aspectEnum) {

    }


    @Override
    public void unregisterObserver(IObserver observer, AspectEnum aspectEnum) {

    }


    @Override
    public void notifyObservers(AspectEnum aspectEnum) {

    }
}
