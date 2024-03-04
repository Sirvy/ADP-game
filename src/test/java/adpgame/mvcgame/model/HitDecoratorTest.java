package adpgame.mvcgame.model;

import adpgame.mvcgame.model.gameObjects.IHit;
import adpgame.mvcgame.model.gameObjects.decorator.EnemyDiedDecorator;
import adpgame.mvcgame.model.gameObjects.SimpleHit;
import adpgame.mvcgame.model.gameObjects.decorator.SpecialMoveUsedDecorator;
import org.junit.Assert;
import org.junit.Test;


public class HitDecoratorTest {
    @Test
    public void hitScoreTest() {
        IHit hit = new SimpleHit();

        Assert.assertEquals(1, hit.getScore());

        hit = new EnemyDiedDecorator(hit);

        Assert.assertEquals(1 + 10, hit.getScore());

        hit = new SpecialMoveUsedDecorator(hit);

        Assert.assertEquals(1 + 10 + 5, hit.getScore());
    }
}
