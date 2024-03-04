package adpgame.mvcgame.model;
import adpgame.mvcgame.strategy.IMovingStrategy;
import adpgame.mvcgame.strategy.SimpleMovingStrategy;
import adpgame.mvcgame.abstractfactory.GameObjectsFactory;
import adpgame.mvcgame.abstractfactory.IGameObjectsFactory;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;
public class GameModelMockedTest {
    private static final int CANNON_TEST_POSITION_X = 555;
    private static final int CANNON_TEST_POSITION_Y = 666;
    private static final double INIT_TEST_ANGLE = 0;
    private static final int INIT_TEST_VELOCITY = 0;
    @Mocked
    private GameModel model;
    @Test
    public void createMissile() {
        this.generalMockSetup();
        IGameObjectsFactory gameObjectsFactory = GameObjectsFactory.getInstance();
        GameObjectsFactory.setModel(this.model);
        AbstractMissile missile = gameObjectsFactory.createMissile(INIT_TEST_ANGLE, INIT_TEST_VELOCITY);
        Assert.assertEquals(CANNON_TEST_POSITION_X, missile.getPosition().getX());
        Assert.assertEquals(CANNON_TEST_POSITION_Y, missile.getPosition().getY());
    }
    public void generalMockSetup() {
        new MockUp<GameModel>() {
            @Mock
            public Position getCannonPosition() {
                return new Position(CANNON_TEST_POSITION_X, CANNON_TEST_POSITION_Y);
            }
            @Mock
            public IMovingStrategy getMovingStrategy() {
                return new SimpleMovingStrategy();
            }
        };
    }
}
