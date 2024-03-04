package adpgame.mvcgame.model.managers;

import adpgame.mvcgame.strategy.SimpleMovingStrategy;
import adpgame.mvcgame.abstractfactory.IGameObjectsFactory;
import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.model.gameObjects.AbstractCanon;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.GameObject;
import adpgame.mvcgame.model.utils.IShootingLimit;
import adpgame.mvcgame.strategy.BirdLikeMovingStrategy;
import adpgame.mvcgame.strategy.IMovingStrategy;
import adpgame.mvcgame.strategy.RealisticMovingStrategy;
import adpgame.mvcgame.strategy.VibratingMovingStrategy;
import adpgame.mvcgame.view.GameSoundPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PlayerManager extends AbstractManager {

    private final IGameObjectsFactory gameObjectsFactory;
    private final GameSoundPlayer soundPlayer;
    private final AbstractCanon cannon;
    private final List<AbstractMissile> missiles;
    private IMovingStrategy movingStrategy;
    private final IShootingLimit shootLimiter;

    public PlayerManager(IGameModel mediator, IGameObjectsFactory gameObjectsFactory, GameSoundPlayer gameSoundPlayer, IShootingLimit shootLimiter) {
        super(mediator);
        this.missiles = new ArrayList<>();
        this.gameObjectsFactory = gameObjectsFactory;
        this.cannon = gameObjectsFactory.createCanon();
        this.soundPlayer = gameSoundPlayer;
        this.movingStrategy = new SimpleMovingStrategy();
        this.shootLimiter = shootLimiter;
    }

    public void update() {
        this.moveMissiles();
        this.destroyMissiles();
    }

    public List<GameObject> getGameObjects() {
        return Stream.of(
                Stream.of(this.cannon),
                this.missiles.stream()
        ).flatMap(i -> i).collect(Collectors.toList());
    }

    public IMovingStrategy getMovingStrategy() {
        return this.movingStrategy;
    }

    public void removeMissile(AbstractMissile missile) {
        this.missiles.remove(missile);
    }

    public void setMovingStrategy(IMovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void toggleMovingStrategy() {
        if (this.movingStrategy instanceof SimpleMovingStrategy) {
            this.movingStrategy = new RealisticMovingStrategy();
        }
        else if (this.movingStrategy instanceof RealisticMovingStrategy) {
            this.movingStrategy = new VibratingMovingStrategy();
        }
        else if (this.movingStrategy instanceof VibratingMovingStrategy) {
            this.movingStrategy = new BirdLikeMovingStrategy();
        } else {
            this.movingStrategy = new SimpleMovingStrategy();
        }
    }

    public void cannonShoot() {
        if (!this.shootLimiter.canShoot()) {
            return;
        }
        this.shootLimiter.updateLimit();
        this.missiles.addAll(this.cannon.shoot());
        this.cannon.acceptVisitor(this.soundPlayer);
    }

    public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
    }

    public void aimCannonUp() {
        this.cannon.aimUp();
    }


    public void aimCannonDown() {
        this.cannon.aimDown();
    }


    public void cannonPowerUp() {
        this.cannon.powerUp();
    }


    public void cannonPowerDown() {
        this.cannon.powerDown();
    }

    public void increaseMissiles() {
        this.cannon.increaseMissiles();
    }

    public void decreaseMissiles() {
        this.cannon.decreaseMissiles();
    }

    public AbstractCanon getCannon() {
        return this.cannon;
    }

    public List<AbstractMissile> getMissiles() {
        return this.missiles;
    }

    private void moveMissiles() {
        this.missiles.forEach(AbstractMissile::move);
        this.destroyMissiles();
    }

    private void destroyMissiles() {
        this.missiles.removeAll(
                this.missiles.stream().filter(missile ->
                        missile.getPosition().getX() > MvcGameConfig.MAX_X
                                || missile.getPosition().getY() > MvcGameConfig.MAX_Y
                                || missile.getPosition().getY() < 0).toList()
        );
    }
}
