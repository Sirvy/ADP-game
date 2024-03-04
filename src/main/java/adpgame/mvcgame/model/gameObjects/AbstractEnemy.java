package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.state.EnemyAliveState;
import adpgame.mvcgame.state.IEnemyState;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;


public abstract class AbstractEnemy extends GameObject {

    protected IEnemyState state = EnemyAliveState.getInstance();

    public AbstractEnemy(Position position) {
        super(position);
    }


    public abstract void move();


    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitEnemy(this);
    }

    public IEnemyState getState() {
        return this.state;
    }

    public void setState(IEnemyState state) {
        this.state = state;
    }
}
