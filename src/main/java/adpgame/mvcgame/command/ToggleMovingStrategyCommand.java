package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class ToggleMovingStrategyCommand extends AbstractGameCommand {

    public ToggleMovingStrategyCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.toggleMovingStrategy();
    }
}
