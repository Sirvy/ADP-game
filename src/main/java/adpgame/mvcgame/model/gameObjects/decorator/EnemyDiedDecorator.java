package adpgame.mvcgame.model.gameObjects.decorator;

import adpgame.mvcgame.model.gameObjects.IHit;


public class EnemyDiedDecorator extends AbstractHitDecorator {

    public EnemyDiedDecorator(IHit decoratedHit) {
        super(decoratedHit);
    }

    @Override
    public int getScore() {
        return super.getScore() + 10;
    }
}
