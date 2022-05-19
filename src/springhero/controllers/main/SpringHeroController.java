package springhero.controllers.main;

import springhero.models.hero.Hero;
import springhero.models.main.Constants;
import springhero.models.main.Map;
import springhero.models.main.SpringHero;
import springhero.models.npc.Ally;
import springhero.models.npc.Enemy;
import springhero.models.npc.NPCFactory;
import springhero.views.main.SpringHeroView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Spring Hero Game controller class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
public class SpringHeroController implements Constants, KeyListener {

    private final SpringHero springHero;
    private final SpringHeroView springHeroView;

    public SpringHeroController() {
        this.springHero = new SpringHero();
        this.springHeroView = new SpringHeroView(this);
    }

    public screens getSpringHeroScreenview() {
        return springHero.getScreenview();
    }

    public void setSpringHeroScreenview(screens screenview) {
        this.springHero.setScreenview(screenview);
    }

    public boolean isSpringHeroGameOver() {
        return springHero.isGameOver();
    }

    public void setSpringHeroGameOver(boolean gameOver) {
        this.springHero.setGameOver(gameOver);
    }

    public int getSpringHeroLevel() {
        return springHero.getLevel();
    }

    public void setSpringHeroLevel(int level) {
        this.springHero.setLevel(level);
    }

    public Map getSpringHeroMap() {
        return springHero.getMap();
    }

    public void setSpringHeroMap(Map map) {
        this.springHero.setMap(map);
    }

    public Hero getSpringHeroHero() {
        return springHero.getHero();
    }

    public void setSpringHeroHero(Hero hero) {
        this.springHero.setHero(hero);
    }

    public NPCFactory getSpringHeroNpcFactory() {
        return springHero.getNpcFactory();
    }

    public void setSpringHeroNpcFactory(NPCFactory npcFactory) {
        this.springHero.setNpcFactory(npcFactory);
    }

    public List<Ally> getSpringHeroAllies() {
        return springHero.getAllies();
    }

    public void setSpringHeroAllies(List<Ally> allies) {
        this.springHero.setAllies(allies);
    }

    public List<Enemy> getSpringHeroEnemies() {
        return springHero.getEnemies();
    }

    public void setSpringHeroEnemies(List<Enemy> enemies) {
        this.springHero.setEnemies(enemies);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (getSpringHeroScreenview() == screens.GAME) {
            boolean heroPositionChanged = false;
            if (key == KeyEvent.VK_W) {
                heroPositionChanged = getSpringHeroHero().move(view.UP, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_S) {
                heroPositionChanged = getSpringHeroHero().move(view.DOWN, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_A) {
                heroPositionChanged = getSpringHeroHero().move(view.LEFT, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_D) {
                heroPositionChanged = getSpringHeroHero().move(view.RIGHT, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_F) {
                System.out.println("ATTACK"); // TODO: DELETE LINE
                // TODO: getSpringHeroHero().attack();
            }
            if (heroPositionChanged) {
                play();
            }
        } else if (getSpringHeroScreenview() == screens.CONTROLS) {
            if (key == KeyEvent.VK_P) {
                setSpringHeroScreenview(screens.GAME);
                this.springHeroView.screenSetup(getSpringHeroScreenview());
                newGame();
            }
        } else if (getSpringHeroScreenview() == screens.WELCOME) {
            if (key == KeyEvent.VK_X) {
                setSpringHeroScreenview(screens.CONTROLS);
                this.springHeroView.screenSetup(getSpringHeroScreenview());
            }
        }
    }

    private void updateSpringHeroView() {
        getSpringHeroMap().update();
        this.springHeroView.update(isSpringHeroGameOver(), getSpringHeroLevel(), getSpringHeroHero(), getSpringHeroAllies(), getSpringHeroEnemies());
    }

    private void allyGenerator(int amount) {
        for (int id = 0; id < amount; id++) {
            Ally ally = (Ally) getSpringHeroNpcFactory().getNPC(NPCType.ALLY, getSpringHeroMap().findEmptyCell(), getSpringHeroHero());
            getSpringHeroAllies().add(ally);
        }
    }

    private void enemyGenerator(int amount) {
        for (int id = 0; id < amount; id++) {
            Enemy enemy = (Enemy) getSpringHeroNpcFactory().getNPC(NPCType.ENEMY, getSpringHeroMap().findEmptyCell(), getSpringHeroHero());
            getSpringHeroEnemies().add(enemy);
        }
    }

    private void newGame() {
        allyGenerator(ALLIES_MAX_AMOUNT);
        enemyGenerator(ENEMIES_MAX_AMOUNT);
        updateSpringHeroView();
    }

    private void removeRescuedAllies() {
        getSpringHeroAllies().removeIf(ally -> ally.isRescued());
    }

    private void moveEnemies() {
        for (Enemy enemy : getSpringHeroEnemies()) {
            enemy.move(getSpringHeroMap());
        }
    }

    private void removeDefeatedEnemies() {
        getSpringHeroEnemies().removeIf(enemy -> enemy.isDefeated());
    }

    private void round() {
        if (getSpringHeroHero().getHealth() == 0) {
            setSpringHeroGameOver(true);
        }
        if (!isSpringHeroGameOver()) {
            // TODO : game loop
            moveEnemies();
            removeDefeatedEnemies();
            if (getSpringHeroEnemies().isEmpty()) {
                setSpringHeroLevel(getSpringHeroLevel() + 1);
                enemyGenerator(ENEMIES_MAX_AMOUNT);
                updateSpringHeroView();
            }
        }
    }

    private void play() {
        round();
        updateSpringHeroView();
    }
}