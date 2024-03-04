package adpgame.mvcgame.bridge.gameGraphics;

import adpgame.mvcgame.model.Position;
import javafx.scene.image.Image;

public interface IGameGraphicsImplementor {
    void drawImage(String path, Position position);
    void drawExistingImage(Image image, Position position);
    void drawText(String text, Position position);
    void drawLine(Position beginPosition, Position endPosition);
    void clear();
}
