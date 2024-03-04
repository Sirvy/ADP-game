package adpgame.mvcgame.model.managers;

import adpgame.mvcgame.model.gameObjects.Item;
import adpgame.mvcgame.abstractfactory.IGameObjectsFactory;
import adpgame.mvcgame.model.IGameModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class ItemManager extends AbstractManager {

    private Date lastItem = new Date();
    private final List<Item> items;
    private final IGameObjectsFactory gameObjectsFactory;

    public ItemManager(IGameModel mediator, IGameObjectsFactory gameObjectsFactory) {
        super(mediator);
        this.items = new ArrayList<>();
        this.gameObjectsFactory = gameObjectsFactory;
    }

    public void update() {
        this.createItems();
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    private void createItems() {
        if (Math.abs(new Date().getTime() - lastItem.getTime()) < ThreadLocalRandom.current().nextInt(100000) + 10000) {
            return;
        }
        lastItem = new Date();
        this.items.clear();
        this.items.add(this.gameObjectsFactory.createItem());
    }
}
