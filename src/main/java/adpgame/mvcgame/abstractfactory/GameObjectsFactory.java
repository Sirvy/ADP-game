package adpgame.mvcgame.abstractfactory;

import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.Enemy;
import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.model.gameObjects.Missile;
import adpgame.mvcgame.model.gameObjects.AbstractCanon;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.Cannon;

import java.util.concurrent.ThreadLocalRandom;


public class GameObjectsFactory implements IGameObjectsFactory {

    private IGameModel model;

    private static final GameObjectsFactory INSTANCE = new GameObjectsFactory();

    private GameObjectsFactory() {}

    public static void setModel(IGameModel model) {
        INSTANCE.model = model;
    }


    public static GameObjectsFactory getInstance() {
        return INSTANCE;
    }


    @Override
    public AbstractCanon createCanon() {
        return new Cannon(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }


    @Override
    public AbstractMissile createMissile(double initAngle, int initVelocity) {
        return new Missile(
                new Position(
                        this.model.getCannonPosition().getX(),
                        this.model.getCannonPosition().getY()
                ),
                initAngle,
                initVelocity,
                this.model.getMovingStrategy()
        );
    }

    @Override
    public AbstractEnemy createEnemy() {
        return new Enemy(new Position(MvcGameConfig.MAX_X, ThreadLocalRandom.current().nextInt(MvcGameConfig.MAX_Y - 40) + 20));
    }

    @Override
    public Item createItem() {
        return new Item(new Position(ThreadLocalRandom.current().nextInt(MvcGameConfig.MAX_X - 40) + 20, ThreadLocalRandom.current().nextInt(MvcGameConfig.MAX_Y - 40) + 20));
    }
}
