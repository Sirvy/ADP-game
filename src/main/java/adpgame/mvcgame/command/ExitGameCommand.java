package adpgame.mvcgame.command;


public class ExitGameCommand extends AbstractGameCommand {

    @Override
    protected void execute() {
        System.exit(0);
    }
}
