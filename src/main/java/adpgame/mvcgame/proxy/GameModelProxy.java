package adpgame.mvcgame.proxy;

import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.command.AbstractGameCommand;
import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.GameObject;
import adpgame.mvcgame.observer.AspectEnum;
import adpgame.mvcgame.observer.IObserver;
import adpgame.mvcgame.strategy.IMovingStrategy;

import java.util.List;


public class GameModelProxy implements IGameModel {
    private IGameModel subject;
    public GameModelProxy(IGameModel model) {
        this.subject = model;
    }
    @Override
    public void registerObserver(IObserver obs, AspectEnum aspect) {
        this.subject.registerObserver(obs, aspect);
    }
    @Override
    public void unregisterObserver(IObserver obs) {
        this.subject.unregisterObserver(obs);
    }


    @Override
    public void unregisterObserver(AspectEnum aspectEnum) {
        this.subject.unregisterObserver(aspectEnum);
    }


    @Override
    public void unregisterObserver(IObserver observer, AspectEnum aspectEnum) {
        this.subject.unregisterObserver(observer, aspectEnum);
    }


    @Override
    public void notifyObservers(AspectEnum aspect) {
        this.subject.notifyObservers(aspect);
    }
    @Override
    public void update() {
        this.subject.update();
    }
    @Override
    public Position getCannonPosition() {
        return this.subject.getCannonPosition();
    }
    @Override
    public void moveCannonUp() {
        this.subject.moveCannonUp();
    }
    @Override
    public void moveCannonDown() {
        this.subject.moveCannonDown();
    }
    @Override
    public void aimCannonUp() {
        this.subject.aimCannonUp();
    }
    @Override
    public void aimCannonDown() {
        this.subject.aimCannonDown();
    }
    @Override
    public void cannonPowerUp() {
        this.subject.cannonPowerUp();
    }
    @Override
    public void cannonPowerDown() {
        this.subject.cannonPowerDown();
    }
    @Override
    public void cannonShoot() {
        this.subject.cannonShoot();
    }
    @Override
    public List<AbstractMissile> getMissiles() {
        return this.subject.getMissiles();
    }
    @Override
    public List<GameObject> getGameObjects() {
        return this.subject.getGameObjects();
    }
    @Override
    public IMovingStrategy getMovingStrategy() {
        return this.subject.getMovingStrategy();
    }
    @Override
    public void toggleMovingStrategy() {
        this.subject.toggleMovingStrategy();
    }
    @Override
    public void toggleShootingMode() {
        this.subject.toggleShootingMode();
    }
    @Override
    public Object createMemento() {
        return this.subject.createMemento();
    }
    @Override
    public void setMemento(Object memento) {
        this.subject.setMemento(memento);
    }
    @Override
    public void registerCommand(AbstractGameCommand command) {
        this.subject.registerCommand(command);
    }
    @Override
    public void undoLastCommand() {
        this.subject.undoLastCommand();
    }


    @Override
    public void increaseMissiles() {
        this.subject.increaseMissiles();
    }


    @Override
    public void decreaseMissiles() {
        this.subject.decreaseMissiles();
    }


    @Override
    public void increaseDifficulty() {
        this.subject.increaseDifficulty();
    }


    @Override
    public void reduceHP() {
        this.subject.reduceHP();
    }


    @Override
    public void triggerMissileEnemyCollision(AbstractMissile missile, AbstractEnemy enemy) {
        this.subject.triggerMissileEnemyCollision(missile, enemy);
    }


    @Override
    public void triggerMissileItemCollision(AbstractMissile missile, Item item) {
        this.subject.triggerMissileItemCollision(missile, item);
    }


    @Override
    public int getHighScore() {
        return this.subject.getHighScore();
    }

}
