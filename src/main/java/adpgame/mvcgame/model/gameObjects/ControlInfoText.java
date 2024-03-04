package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.config.MvcGameConfig;
import adpgame.mvcgame.model.Position;


public class ControlInfoText extends AbstractText {

    public ControlInfoText(Position position) {
        super(position);
    }

    @Override
    public String getText() {
        return "How to play:\n"
                + "Up: [" + MvcGameConfig.UP_KEY + "]\n"
                + "Down: [" + MvcGameConfig.DOWN_KEY + "]\n"
                + "Shoot: [" + MvcGameConfig.SHOOT_KEY + "]\n"
                + "Aim Up: [" + MvcGameConfig.AIM_UP_KEY + "]\n"
                + "Aim Down: [" + MvcGameConfig.AIM_DOWN_KEY + "]\n"
                + "Power Up: [" + MvcGameConfig.POWER_UP_KEY + "]\n"
                + "Power Down: [" + MvcGameConfig.POWER_DOWN_KEY + "]\n"
                + "Change Moving Strategy: [" + MvcGameConfig.MOVING_STRATEGY_KEY + "]\n"
                + "Change Shooting Mode: [" + MvcGameConfig.SHOOTING_MODE_KEY + "]\n"
                + "More Missiles: [" + MvcGameConfig.MORE_MISSILES + "]\n"
                + "Less Missiles: [" + MvcGameConfig.LESS_MISSILES + "]\n"
                + "Undo: [" + MvcGameConfig.UNDO_LAST_COMMAND_KEY + "]\n";
    }

}
