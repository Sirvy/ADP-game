package adpgame.mvcgame.model;

import adpgame.mvcgame.command.AimCannonDownCommand;
import adpgame.mvcgame.command.MoveCannonDownCommand;
import adpgame.mvcgame.command.MoveCannonUpCommand;
import adpgame.mvcgame.command.PowerCannonDownCommand;
import adpgame.mvcgame.command.ToggleMovingStrategyCommand;
import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.strategy.SimpleMovingStrategy;
import adpgame.mvcgame.command.AimCannonUpCommand;
import adpgame.mvcgame.command.PowerCannonUpCommand;
import org.junit.Assert;
import org.junit.Test;


public class GameModelCommandTest {

    private final double delta = 0.01;

    @Test
    public void basicCommandsTest() {
        GameModel model = new GameModel();
        Assert.assertEquals(MvcGameConfig.INIT_POWER, model.getPlayerManager().getCannon().getPower());
        Assert.assertEquals(MvcGameConfig.INIT_ANGLE, model.getPlayerManager().getCannon().getAngle(), delta);
        Assert.assertTrue(model.getPlayerManager().getMovingStrategy() instanceof SimpleMovingStrategy);
        Assert.assertEquals(MvcGameConfig.CANNON_POS_Y, model.getPlayerManager().getCannon().getPosition().getY());

        model.registerCommand(new MoveCannonUpCommand(model));
        model.registerCommand(new MoveCannonUpCommand(model));
        model.registerCommand(new MoveCannonUpCommand(model));
        model.registerCommand(new MoveCannonDownCommand(model));

        model.registerCommand(new AimCannonUpCommand(model));
        model.registerCommand(new AimCannonUpCommand(model));
        model.registerCommand(new AimCannonUpCommand(model));
        model.registerCommand(new AimCannonDownCommand(model));

        model.registerCommand(new PowerCannonUpCommand(model));
        model.registerCommand(new PowerCannonUpCommand(model));
        model.registerCommand(new PowerCannonUpCommand(model));
        model.registerCommand(new PowerCannonDownCommand(model));

        model.registerCommand(new ToggleMovingStrategyCommand(model));

        model.update();

        Assert.assertEquals(MvcGameConfig.CANNON_POS_Y - 2 * MvcGameConfig.MOVE_STEP, model.getPlayerManager().getCannon().getPosition().getY());
        Assert.assertEquals(MvcGameConfig.INIT_ANGLE - 2 * MvcGameConfig.ANGLE_STEP, model.getPlayerManager().getCannon().getAngle(), delta);
        Assert.assertEquals(MvcGameConfig.INIT_POWER + 2 * MvcGameConfig.POWER_STEP, model.getPlayerManager().getCannon().getPower());
        Assert.assertFalse(model.getPlayerManager().getMovingStrategy() instanceof SimpleMovingStrategy);
    }


    @Test
    public void undoCommandTest() {
        GameModel model = new GameModel();
        Assert.assertEquals(MvcGameConfig.INIT_POWER, model.getPlayerManager().getCannon().getPower());
        Assert.assertEquals(MvcGameConfig.INIT_ANGLE, model.getPlayerManager().getCannon().getAngle(), delta);
        Assert.assertTrue(model.getPlayerManager().getMovingStrategy() instanceof SimpleMovingStrategy);
        Assert.assertEquals(MvcGameConfig.CANNON_POS_Y, model.getPlayerManager().getCannon().getPosition().getY());

        model.registerCommand(new MoveCannonUpCommand(model));
        model.registerCommand(new MoveCannonUpCommand(model));
        model.registerCommand(new MoveCannonUpCommand(model));
        model.registerCommand(new MoveCannonDownCommand(model));

        model.registerCommand(new AimCannonUpCommand(model));
        model.registerCommand(new AimCannonUpCommand(model));
        model.registerCommand(new AimCannonUpCommand(model));
        model.registerCommand(new AimCannonDownCommand(model));

        model.registerCommand(new PowerCannonUpCommand(model));
        model.registerCommand(new PowerCannonUpCommand(model));
        model.registerCommand(new PowerCannonUpCommand(model));
        model.registerCommand(new PowerCannonDownCommand(model));

        model.registerCommand(new ToggleMovingStrategyCommand(model));

        model.update();

        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();
        model.undoLastCommand();

        model.update();

        Assert.assertEquals(MvcGameConfig.INIT_POWER, model.getPlayerManager().getCannon().getPower());
        Assert.assertEquals(MvcGameConfig.INIT_ANGLE, model.getPlayerManager().getCannon().getAngle(), delta);
        Assert.assertTrue(model.getPlayerManager().getMovingStrategy() instanceof SimpleMovingStrategy);
        Assert.assertEquals(MvcGameConfig.CANNON_POS_Y, model.getPlayerManager().getCannon().getPosition().getY());
    }

}
