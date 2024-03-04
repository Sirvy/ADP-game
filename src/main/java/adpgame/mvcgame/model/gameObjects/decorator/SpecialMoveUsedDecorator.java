package adpgame.mvcgame.model.gameObjects.decorator;

import adpgame.mvcgame.model.gameObjects.IHit;


public class SpecialMoveUsedDecorator extends AbstractHitDecorator {

    public SpecialMoveUsedDecorator(IHit decoratedHit) {
        super(decoratedHit);
    }


    @Override
    public int getScore() {
        return super.getScore() + 5;
    }
}
