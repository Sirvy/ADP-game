package adpgame.mvcgame.model.gameObjects.decorator;

import adpgame.mvcgame.model.gameObjects.IHit;


public class AbstractHitDecorator implements IHit {

    private final IHit decoratedHit;


    public AbstractHitDecorator(IHit decoratedHit) {
        this.decoratedHit = decoratedHit;
    }


    @Override
    public int getScore() {
        return decoratedHit.getScore();
    }
}
