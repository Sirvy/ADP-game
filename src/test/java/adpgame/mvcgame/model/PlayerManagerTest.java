package adpgame.mvcgame.model;

import adpgame.mvcgame.state.DoubleShootingMode;
import adpgame.mvcgame.state.DynamicShootingMode;
import adpgame.mvcgame.state.SingleShootingMode;
import adpgame.mvcgame.strategy.SimpleMovingStrategy;
import adpgame.mvcgame.abstractfactory.GameObjectsFactory;
import adpgame.mvcgame.model.gameObjects.AbstractCanon;
import adpgame.mvcgame.model.managers.PlayerManager;
import adpgame.mvcgame.model.utils.ShootingLimit;
import adpgame.mvcgame.strategy.BirdLikeMovingStrategy;
import adpgame.mvcgame.strategy.RealisticMovingStrategy;
import adpgame.mvcgame.strategy.VibratingMovingStrategy;
import adpgame.mvcgame.view.GameSoundPlayer;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;


public class PlayerManagerTest {
    @Mocked
    private GameSoundPlayer gameSoundPlayer;

    @Mocked
    private ShootingLimit shootLimiter;

    private static final int ITERATION_START_CONST = 1;
    private static final int MISSILE_COUNT = 50;
    private static final int MOVE_COUNT = 100000;
    private static final int EXPECTED_MISSILES_COUNT = 0;

    @Test
    public void shootingTest() {
        this.generalMockSetup();
        GameModel model = new GameModel();
        GameObjectsFactory.setModel(model);
        PlayerManager playerManager = new PlayerManager(model, GameObjectsFactory.getInstance(), this.gameSoundPlayer, this.shootLimiter);

        IntStream.rangeClosed(ITERATION_START_CONST, MISSILE_COUNT).forEach(i -> playerManager.cannonShoot());
        playerManager.update();
        Assert.assertEquals(MISSILE_COUNT, playerManager.getMissiles().size());

        IntStream.rangeClosed(ITERATION_START_CONST, MOVE_COUNT).forEach(i -> playerManager.update());
        Assert.assertEquals(EXPECTED_MISSILES_COUNT, playerManager.getMissiles().size());
    }

    @Test
    public void toggleMovingStrategyTest() {
        this.generalMockSetup();
        GameModel model = new GameModel();
        GameObjectsFactory.setModel(model);
        PlayerManager playerManager = new PlayerManager(model, GameObjectsFactory.getInstance(), this.gameSoundPlayer, this.shootLimiter);

        Assert.assertTrue(playerManager.getMovingStrategy() instanceof SimpleMovingStrategy);
        playerManager.toggleMovingStrategy();
        Assert.assertTrue(playerManager.getMovingStrategy() instanceof RealisticMovingStrategy);
        playerManager.toggleMovingStrategy();
        Assert.assertTrue(playerManager.getMovingStrategy() instanceof VibratingMovingStrategy);
        playerManager.toggleMovingStrategy();
        Assert.assertTrue(playerManager.getMovingStrategy() instanceof BirdLikeMovingStrategy);
        playerManager.toggleMovingStrategy();
        Assert.assertTrue(playerManager.getMovingStrategy() instanceof SimpleMovingStrategy);
    }


    @Test
    public void toggleShootingModeTest() {
        this.generalMockSetup();
        GameModel model = new GameModel();
        GameObjectsFactory.setModel(model);
        PlayerManager playerManager = new PlayerManager(model, GameObjectsFactory.getInstance(), this.gameSoundPlayer, this.shootLimiter);

        Assert.assertTrue(playerManager.getCannon().getShootingModeState() instanceof SingleShootingMode);
        playerManager.toggleShootingMode();
        Assert.assertTrue(playerManager.getCannon().getShootingModeState() instanceof DoubleShootingMode);
        playerManager.toggleShootingMode();
        Assert.assertTrue(playerManager.getCannon().getShootingModeState() instanceof DynamicShootingMode);
        playerManager.toggleShootingMode();
        Assert.assertTrue(playerManager.getCannon().getShootingModeState() instanceof SingleShootingMode);
    }


    @Test
    public void simpleShootingModeIncreaseMissileTest() {
        this.generalMockSetup();

        GameModel model = new GameModel();
        GameObjectsFactory.setModel(model);
        PlayerManager playerManager = new PlayerManager(model, GameObjectsFactory.getInstance(), this.gameSoundPlayer, this.shootLimiter);

        playerManager.increaseMissiles();
        playerManager.increaseMissiles();
        playerManager.increaseMissiles();

        playerManager.cannonShoot();
        playerManager.update();

        Assert.assertEquals(1, playerManager.getMissiles().size());
    }

    @Test
    public void dynamicShootingModeIncreaseMissileTest() {
        this.generalMockSetup();

        GameModel model = new GameModel();
        GameObjectsFactory.setModel(model);
        PlayerManager playerManager = new PlayerManager(model, GameObjectsFactory.getInstance(), this.gameSoundPlayer, this.shootLimiter);

        playerManager.cannonShoot();
        Assert.assertEquals(1, playerManager.getMissiles().size());

        playerManager.toggleShootingMode();
        playerManager.toggleShootingMode();

        Assert.assertTrue(playerManager.getCannon().getShootingModeState() instanceof DynamicShootingMode);

        playerManager.increaseMissiles();
        playerManager.increaseMissiles();
        playerManager.increaseMissiles();
        playerManager.increaseMissiles();
        playerManager.increaseMissiles();
        playerManager.increaseMissiles();

        playerManager.cannonShoot();

        Assert.assertEquals(10, playerManager.getMissiles().size());
    }

    public void generalMockSetup() {
        new MockUp<GameSoundPlayer>() {
            @Mock
            public void visitCannon(AbstractCanon cannon) {
            }
        };

        new MockUp<ShootingLimit>() {
            @Mock
            public boolean canShoot() {
                return true;
            }
            @Mock
            public void updateLimit() {
            }
        };
    }

}
