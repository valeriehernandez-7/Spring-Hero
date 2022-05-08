package springhero.models.main;

import springhero.models.hero.Hero;
import springhero.models.npc.Ally;
import springhero.models.npc.Enemy;
import springhero.models.npc.NPCFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Hero Game model class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
public class SpringHero implements Constants {

    private screens screenview;
    private boolean gameOver;
    private Map map;
    private Hero hero;
    private NPCFactory npcFactory;
    private List<Ally> allies;
    private List<Enemy> enemies;

    public SpringHero() {
        this.screenview = screens.WELCOME;
        this.gameOver = false;
        this.map = new Map();
        this.hero = new Hero(this.map.getCell(MAP_GRID_CENTER));
        this.npcFactory = new NPCFactory();
        this.allies = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public screens getScreenview() {
        return screenview;
    }

    public void setScreenview(screens screenview) {
        this.screenview = screenview;
    }
}