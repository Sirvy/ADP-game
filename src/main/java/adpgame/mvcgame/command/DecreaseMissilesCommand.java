package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class DecreaseMissilesCommand extends AbstractGameCommand {

    public DecreaseMissilesCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.decreaseMissiles();
    }
}
