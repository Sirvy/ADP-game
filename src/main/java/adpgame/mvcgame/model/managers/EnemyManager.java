package adpgame.mvcgame.model.managers;

import adpgame.mvcgame.abstractfactory.IGameObjectsFactory;
import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.utils.IEnemyCreationLimit;

import java.util.ArrayList;
import java.util.List;


public class EnemyManager extends AbstractManager {

    private final IGameObjectsFactory gameObjectsFactory;
    private final List<AbstractEnemy> enemies;
    private final IEnemyCreationLimit enemyCreationLimit;

    public EnemyManager(IGameModel mediator, IGameObjectsFactory gameObjectsFactory, IEnemyCreationLimit enemyCreationLimit) {
        super(mediator);
        this.enemies = new ArrayList<>();
        this.gameObjectsFactory = gameObjectsFactory;
        this.enemyCreationLimit = enemyCreationLimit;
    }

    public void update(int difficulty) {
        this.createEnemy(difficulty);
        this.moveEnemies();
    }

    public List<AbstractEnemy> getEnemies() {
        return this.enemies;
    }

    public void removeEnemy(AbstractEnemy enemy) {
        this.enemies.remove(enemy);
    }

    private void createEnemy(int difficulty) {
        if (!this.enemyCreationLimit.canPerform(difficulty)) {
            return;
        }
        this.enemyCreationLimit.updateLimit();
        this.mediator.increaseDifficulty();
        this.enemies.add(this.gameObjectsFactory.createEnemy());
    }

    private void moveEnemies() {
        this.enemies.forEach(AbstractEnemy::move);
        this.destroyEnemies();
    }

    private void destroyEnemies() {
        this.enemies.stream().filter(enemy ->
                enemy.getPosition().getX() < 0
                        || enemy.getPosition().getY() > MvcGameConfig.MAX_Y
                        || enemy.getPosition().getY() < 0).forEach(enemy -> {
            this.enemies.remove(enemy);
            this.mediator.reduceHP();
        });
    }
}
