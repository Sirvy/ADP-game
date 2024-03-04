package adpgame.mvcgame.visitor;

import adpgame.mvcgame.model.gameObjects.AbstractCanon;
import adpgame.mvcgame.model.gameObjects.AbstractEnemy;
import adpgame.mvcgame.model.gameObjects.AbstractMissile;
import adpgame.mvcgame.model.gameObjects.AbstractText;
import adpgame.mvcgame.model.gameObjects.GameStatus;
import adpgame.mvcgame.model.gameObjects.Item;


public interface IGameObjectsVisitor {
    void visitCannon(AbstractCanon cannon);
    void visitMissile(AbstractMissile missile);
    void visitInfoPanel(AbstractText text);
    void visitEnemy(AbstractEnemy enemy);
    void visitStatus(GameStatus status);
    void visitItem(Item item);
}
