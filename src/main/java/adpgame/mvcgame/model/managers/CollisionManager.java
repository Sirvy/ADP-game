package adpgame.mvcgame.model.managers;

import adpgame.mvcgame.model.IGameModel;
import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.GameObject;
import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.model.gameObjects.Missile;

import java.util.List;


public class CollisionManager extends AbstractManager {

    public CollisionManager(IGameModel mediator) {
        super(mediator);
    }

    public void detectCollisions(List<AbstractEnemy> enemies, List<AbstractMissile> missiles, List<Item> items) {
        missiles.forEach(missile -> {
            enemies.forEach(enemy -> {
                if (hasObjectMissileCollision(enemy, missile)) {
                    this.mediator.triggerMissileEnemyCollision(missile, enemy);
                }
            });

            items.forEach(item -> {
                if (hasObjectMissileCollision(item, missile)) {
                    this.mediator.triggerMissileItemCollision(missile, item);
                }
            });
        });
    }

    private boolean hasObjectMissileCollision(GameObject object, AbstractMissile missile) {
        return (calculateDistanceBetweenVectors(object.getPosition(), missile.getPosition()) < 16)
                || (calculateDistanceBetweenVectors(((Missile) missile).getPreviousPosition(), object.getPosition())
                + calculateDistanceBetweenVectors(object.getPosition(), missile.getPosition())
                - calculateDistanceBetweenVectors(((Missile) missile).getPreviousPosition(), missile.getPosition())
        ) < 16;
    }

    private double calculateDistanceBetweenVectors(Position x, Position y) {
        return Math.sqrt((x.getX() - y.getX()) * (x.getX() - y.getX()) + (x.getY() - y.getY()) * (x.getY() - y.getY()));
    }
}
