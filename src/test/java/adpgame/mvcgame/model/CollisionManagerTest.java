package adpgame.mvcgame.model;

import adpgame.mvcgame.strategy.SimpleMovingStrategy;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.Enemy;
import adpgame.mvcgame.model.gameObjects.Missile;
import adpgame.mvcgame.model.managers.CollisionManager;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class CollisionManagerTest {

    @Mocked
    private GameModel model;

    private int collisionCounter = 0;


    @Test
    public void detectCollisionTest() {
        this.generalMockSetup();

        CollisionManager collisionManager = new CollisionManager(model);

        AbstractMissile missile = new Missile(new Position(0, 0), 0, 10, new SimpleMovingStrategy());

        AbstractEnemy enemy = new Enemy(new Position(100, 0));

        collisionManager.detectCollisions(List.of(enemy), List.of(missile), List.of());

        Assert.assertEquals(0, collisionCounter);

        missile.move();
        collisionManager.detectCollisions(List.of(enemy), List.of(missile), List.of());

        Assert.assertEquals(0, collisionCounter);

        missile.move();
        collisionManager.detectCollisions(List.of(enemy), List.of(missile), List.of());

        Assert.assertEquals(1, collisionCounter);
    }

    public void generalMockSetup() {
        new MockUp<GameModel>() {
            @Mock
            public void triggerMissileEnemyCollision(AbstractMissile missile, AbstractEnemy enemy) {
                collisionCounter++;
            }
        };
        new MockUp<SimpleMovingStrategy>() {
            @Mock
            public void updatePosition(AbstractMissile missile) {
                missile.getPosition().setX(missile.getPosition().getX() + 50);
            }
        };
    }
}
