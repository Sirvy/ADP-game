package adpgame.mvcgame.command;

import adpgame.mvcgame.model.IGameModel;


public class PowerCannonDownCommand extends AbstractGameCommand {

    public PowerCannonDownCommand(IGameModel model) {
        this.subject = model;
    }

    @Override
    protected void execute() {
        this.subject.cannonPowerDown();
    }
}
