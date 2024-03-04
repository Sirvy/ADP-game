package adpgame.mvcgame.model.managers;

import adpgame.mvcgame.model.IGameModel;


public abstract class AbstractManager {
    protected IGameModel mediator;

    public AbstractManager(IGameModel mediator) {
        this.mediator = mediator;
    }
}
