package adpgame.mvcgame.model;

import adpgame.mvcgame.abstractfactory.GameObjectsFactory;
import adpgame.mvcgame.command.AbstractGameCommand;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.GameObject;
import adpgame.mvcgame.model.gameObjects.IHit;
import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.model.gameObjects.SimpleHit;
import adpgame.mvcgame.model.gameObjects.decorator.EnemyDiedDecorator;
import adpgame.mvcgame.model.gameObjects.decorator.SpecialMoveUsedDecorator;
import adpgame.mvcgame.model.managers.CollisionManager;
import adpgame.mvcgame.model.managers.EnemyManager;
import adpgame.mvcgame.model.managers.GameInfoManager;
import adpgame.mvcgame.model.managers.ItemManager;
import adpgame.mvcgame.model.managers.PlayerManager;
import adpgame.mvcgame.model.utils.EnemyCreationLimit;
import adpgame.mvcgame.model.utils.ShootingLimit;
import adpgame.mvcgame.state.IShootingMode;
import adpgame.mvcgame.strategy.IMovingStrategy;
import adpgame.mvcgame.strategy.SimpleMovingStrategy;
import adpgame.mvcgame.abstractfactory.IGameObjectsFactory;
import adpgame.mvcgame.observer.AspectEnum;
import adpgame.mvcgame.observer.IObserver;
import adpgame.mvcgame.view.GameSoundPlayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameModel implements IGameModel {

    private final Map<AspectEnum, Set<IObserver>> observers;

    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;

    private final EnemyManager enemyManager;
    private final GameInfoManager gameInfoManager;
    private final PlayerManager playerManager;
    private final ItemManager itemManager;
    private final CollisionManager collisionManager;

    public GameModel() {
        GameObjectsFactory.setModel(this);
        IGameObjectsFactory gameObjectsFactory = GameObjectsFactory.getInstance();
        this.observers = new HashMap<>();
        this.unexecutedCommands = new LinkedBlockingQueue<>();
        this.executedCommands = new Stack<>();

        this.enemyManager = new EnemyManager(this, gameObjectsFactory, new EnemyCreationLimit());
        this.playerManager = new PlayerManager(this, gameObjectsFactory, new GameSoundPlayer(), new ShootingLimit());
        this.gameInfoManager = new GameInfoManager(this);
        this.itemManager = new ItemManager(this, gameObjectsFactory);
        this.collisionManager = new CollisionManager(this);
    }


    public EnemyManager getEnemyManager() {
        return enemyManager;
    }


    public GameInfoManager getGameInfoManager() {
        return gameInfoManager;
    }


    public PlayerManager getPlayerManager() {
        return playerManager;
    }


    public ItemManager getItemManager() {
        return itemManager;
    }


    public CollisionManager getCollisionManager() {
        return collisionManager;
    }


    public void update() {
        this.enemyManager.update(this.gameInfoManager.getGameStatus().getDifficulty());
        this.playerManager.update();
        this.collisionManager.detectCollisions(this.enemyManager.getEnemies(), this.playerManager.getMissiles(), this.itemManager.getItems());
        this.gameInfoManager.update(
                String.valueOf(this.playerManager.getCannon().getPower()),
                this.playerManager.getCannon().getAngle(),
                this.playerManager.getMovingStrategy().getName(),
                this.playerManager.getCannon().getShootingMode()
        );
        this.itemManager.update();
        this.executeCommands();
        this.notifyObservers(AspectEnum.ASPECT_ALL);
    }

    public int getHighScore() {
        return this.gameInfoManager.getGameStatus().getScore();
    }

    public void triggerMissileEnemyCollision(AbstractMissile missile, AbstractEnemy enemy) {
        IHit hit = new SimpleHit();
        if (!(this.playerManager.getMovingStrategy() instanceof SimpleMovingStrategy)) {
            hit = new SpecialMoveUsedDecorator(hit);
        }
        this.playerManager.removeMissile(missile);
        enemy.setState(enemy.getState().nextState());
        if (enemy.getState() == null) {
            hit = new EnemyDiedDecorator(hit);
            this.enemyManager.removeEnemy(enemy);
        }

        this.gameInfoManager.increaseScore(hit.getScore());
    }

    public void triggerMissileItemCollision(AbstractMissile missile, Item item) {
        this.playerManager.removeMissile(missile);
        this.itemManager.removeItem(item);
        this.toggleShootingMode();
    }

    public void increaseDifficulty() {
        this.gameInfoManager.increaseDifficulty();
    }

    public void reduceHP() {
        this.gameInfoManager.reduceHP();
        if (this.gameInfoManager.isOutOfHP()) {
            this.notifyObservers(AspectEnum.ASPECT_GAME_OVER);
        }
    }

    public Position getCannonPosition() {
        return this.playerManager.getCannon().getPosition();
    }

    public void moveCannonUp() {
        this.playerManager.getCannon().moveUp();
    }

    public void moveCannonDown() {
        this.playerManager.getCannon().moveDown();
    }

    public List<AbstractMissile> getMissiles() {
        return this.playerManager.getMissiles();
    }

    public List<GameObject> getGameObjects() {
        return Stream.of(
                this.playerManager.getGameObjects().stream(),
                this.gameInfoManager.getGameObjects().stream(),
                this.enemyManager.getEnemies().stream(),
                this.itemManager.getItems().stream()
        ).flatMap(i -> i).collect(Collectors.toList());
    }


    @Override
    public void registerObserver(IObserver observer, AspectEnum aspectEnum) {
        if (!this.observers.containsKey(aspectEnum)) {
            this.observers.put(aspectEnum, new HashSet<>());
        }
        this.observers.get(aspectEnum).add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        this.observers.forEach((aspectEnum, iObservers) -> iObservers.remove(observer));
    }


    @Override
    public void unregisterObserver(AspectEnum aspectEnum) {
        this.observers.remove(aspectEnum);
    }


    @Override
    public void unregisterObserver(IObserver observer, AspectEnum aspectEnum) {
        if (this.observers.containsKey(aspectEnum)) {
            this.observers.get(aspectEnum).remove(observer);
        }
    }


    @Override
    public void notifyObservers(AspectEnum aspectEnum) {
        this.observers.getOrDefault(aspectEnum, new HashSet<>()).forEach(IObserver::update);
    }


    @Override
    public void aimCannonUp() {
        this.playerManager.aimCannonUp();
    }


    @Override
    public void aimCannonDown() {
        this.playerManager.aimCannonDown();
    }


    @Override
    public void cannonPowerUp() {
        this.playerManager.cannonPowerUp();
    }


    @Override
    public void cannonPowerDown() {
        this.playerManager.cannonPowerDown();
    }


    @Override
    public void cannonShoot() {
        this.playerManager.cannonShoot();
    }


    @Override
    public IMovingStrategy getMovingStrategy() {
        return this.playerManager.getMovingStrategy();
    }

    @Override
    public void toggleMovingStrategy() {
        this.playerManager.toggleMovingStrategy();
    }

    @Override
    public void toggleShootingMode() {
        this.playerManager.toggleShootingMode();
    }

    private static class Memento {
        private int cannonPositionX;
        private int cannonPositionY;
        private int power;
        private int score;
        private double angle;
        private IMovingStrategy movingStrategy;
        private IShootingMode shootingMode;
    }

    @Override
    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionX = this.getCannonPosition().getX();
        gameModelSnapshot.cannonPositionY = this.getCannonPosition().getY();
        gameModelSnapshot.power = this.playerManager.getCannon().getPower();
        gameModelSnapshot.angle = this.playerManager.getCannon().getAngle();
        gameModelSnapshot.score = this.gameInfoManager.getGameStatus().getScore();
        gameModelSnapshot.movingStrategy = this.playerManager.getMovingStrategy();
        gameModelSnapshot.shootingMode = this.playerManager.getCannon().getShootingModeState();
        return gameModelSnapshot;
    }

    @Override
    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        this.playerManager.getCannon().getPosition().setX(gameModelSnapshot.cannonPositionX);
        this.playerManager.getCannon().getPosition().setY(gameModelSnapshot.cannonPositionY);
        this.playerManager.getCannon().setPower(gameModelSnapshot.power);
        this.playerManager.getCannon().setAngle(gameModelSnapshot.angle);
        this.gameInfoManager.getGameStatus().setScore(gameModelSnapshot.score);
        this.playerManager.setMovingStrategy(gameModelSnapshot.movingStrategy);
        this.playerManager.getCannon().setShootingMode(gameModelSnapshot.shootingMode);
    }


    @Override
    public void registerCommand(AbstractGameCommand command) {
        this.unexecutedCommands.add(command);
    }


    @Override
    public void undoLastCommand() {
        if (!this.executedCommands.isEmpty()) {
            this.executedCommands.pop().unExecute();
            this.notifyObservers(AspectEnum.ASPECT_ALL);
        }
    }


    @Override
    public void increaseMissiles() {
        this.playerManager.increaseMissiles();
    }

    @Override
    public void decreaseMissiles() {
        this.playerManager.decreaseMissiles();
    }

    private void executeCommands() {
        while (!this.unexecutedCommands.isEmpty()) {
            AbstractGameCommand command = this.unexecutedCommands.poll();
            command.doExecute();
            this.executedCommands.add(command);
        }
    }

}
