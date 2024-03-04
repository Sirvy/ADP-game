package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;


public class PanelInfoText extends AbstractText {

    private String power;
    private double aimAngle;
    private String movingStrategy;
    private String shootingMode;

    public PanelInfoText(Position position) {
        super(position);
    }

    public void updatePanel(String power, double aimAngle, String movingStrategy, String shootingMode) {
        this.power = power;
        this.aimAngle = aimAngle;
        this.movingStrategy = movingStrategy;
        this.shootingMode = shootingMode;
    }

    @Override
    public String getText() {
        return "Game info:\n"
                + "Power: [" + power + "]\n"
                + "Aim angle: [" + String.format("%.2f", aimAngle) + "]\n"
                + "Moving strategy: [" + movingStrategy + "]\n"
                + "Shooting mode: [" + shootingMode + "]\n";
    }

}
