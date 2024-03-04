package adpgame.mvcgame.model;

import adpgame.mvcgame.abstractfactory.GameObjectsFactory;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.Enemy;
import adpgame.mvcgame.model.managers.EnemyManager;
import adpgame.mvcgame.model.utils.EnemyCreationLimit;
import adpgame.mvcgame.state.EnemyAliveState;
import adpgame.mvcgame.state.EnemyCollidedState;
import adpgame.mvcgame.state.EnemyDeadState;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.IntStream;


public class EnemyManagerTest {

    @Mocked
    private EnemyCreationLimit enemyCreationLimit;

    private static final int ITERATION_START_CONST = 1;
    private static final int ENEMY_COUNT = 50;
    private static final int MOVE_COUNT = 100000;
    private static final int EXPECTED_ENEMY_COUNT = 0;
    private static final int DIFFICULTY = 1;

    @Test
    public void enemyMovingTest() throws NoSuchMethodException {
        this.generalMockSetup();

        GameModel model = new GameModel();
        GameObjectsFactory.setModel(model);
        EnemyManager enemyManager = new EnemyManager(model, GameObjectsFactory.getInstance(), this.enemyCreationLimit);

        Method createEnemy = enemyManager.getClass().getDeclaredMethod("createEnemy", int.class);
        createEnemy.setAccessible(true);

        IntStream.rangeClosed(ITERATION_START_CONST, ENEMY_COUNT).forEach(i-> {
            try {
                createEnemy.invoke(enemyManager, DIFFICULTY);
            } catch (IllegalAccessException | InvocationTargetException ignored) {}
        });

        createEnemy.setAccessible(false);
        Assert.assertEquals(ENEMY_COUNT, enemyManager.getEnemies().size());

        Method moveEnemy = enemyManager.getClass().getDeclaredMethod("moveEnemies");
        moveEnemy.setAccessible(true);

        IntStream.rangeClosed(ITERATION_START_CONST, MOVE_COUNT).forEach(i-> {
            try {
                moveEnemy.invoke(enemyManager);
            } catch (IllegalAccessException | InvocationTargetException ignored) {}
        });

        moveEnemy.setAccessible(false);
        Assert.assertEquals(EXPECTED_ENEMY_COUNT, enemyManager.getEnemies().size());
    }

    @Test
    public void enemyStateToggleTest() {
        GameModel model = new GameModel();
        Enemy enemy = new Enemy(new Position(0, 0));
        AbstractMissile missile = GameObjectsFactory.getInstance().createMissile(0, 0);

        Assert.assertTrue(enemy.getState() instanceof EnemyAliveState);

        model.triggerMissileEnemyCollision(missile, enemy);

        Assert.assertTrue(enemy.getState() instanceof EnemyCollidedState);

        model.triggerMissileEnemyCollision(missile, enemy);

        Assert.assertTrue(enemy.getState() instanceof EnemyDeadState);
    }


    public void generalMockSetup() {
        new MockUp<EnemyCreationLimit>() {
            @Mock
            public boolean canPerform(int difficulty) {
                return true;
            }
            @Mock
            public void updateLimit() {
            }
        };
    }

}
