package adpgame.mvcgame.model.gameObjects;

import adpgame.mvcgame.model.Position;
import adpgame.mvcgame.visitor.IGameObjectsVisitor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class LifetimeLimitedGameObject extends GameObject {

    private final LocalDateTime bornAt;

    public LifetimeLimitedGameObject(Position position) {
        super(position);
        this.bornAt = LocalDateTime.now();

    }

    public long getAge() {
        return ChronoUnit.MILLIS.between(this.bornAt, LocalDateTime.now());
    }


    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {

    }
}
