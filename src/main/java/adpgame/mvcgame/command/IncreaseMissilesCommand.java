package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class IncreaseMissilesCommand extends AbstractGameCommand {

    public IncreaseMissilesCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.increaseMissiles();
    }
}
