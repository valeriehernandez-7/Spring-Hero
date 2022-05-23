package springhero.controllers.main;

import springhero.models.hero.Hero;
import springhero.models.main.Constants;
import springhero.models.main.Map;
import springhero.models.main.SpringHero;
import springhero.models.npc.Ally;
import springhero.models.npc.Enemy;
import springhero.models.npc.NPCFactory;
import springhero.views.main.SpringHeroView;

import javax.swing.Timer;
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

    public Hero getSpringHeroHero() {
        return springHero.getHero();
    }

    public NPCFactory getSpringHeroNpcFactory() {
        return springHero.getNpcFactory();
    }

    public List<Ally> getSpringHeroAllies() {
        return springHero.getAllies();
    }

    public List<Enemy> getSpringHeroEnemies() {
        return springHero.getEnemies();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (getSpringHeroScreenview() == screens.GAME) {
            boolean heroStateChanged = false;
            if (key == KeyEvent.VK_W) {
                heroStateChanged = getSpringHeroHero().move(view.UP, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_S) {
                heroStateChanged = getSpringHeroHero().move(view.DOWN, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_A) {
                heroStateChanged = getSpringHeroHero().move(view.LEFT, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_D) {
                heroStateChanged = getSpringHeroHero().move(view.RIGHT, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_F) {
                heroStateChanged = getSpringHeroHero().attack(getSpringHeroMap(), getSpringHeroEnemies());
                new Timer(200, e -> {
                    getSpringHeroHero().updateSprite(null, getSpringHeroMap().getCell(getSpringHeroHero().getPosition()));
                    ((Timer) e.getSource()).stop();
                }).start();
            }
            if (heroStateChanged) {
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

    private void updateAllies() {
        for (Ally ally : getSpringHeroAllies()) {
            ally.checkStatus(getSpringHeroMap(), getSpringHeroHero());
        }
        updateSpringHeroView();
        removeRescuedAllies();
    }

    private void checkAllies() {
        if (getSpringHeroAllies().isEmpty() && (getSpringHeroLevel() % ALLY_GENERATOR_SIGNAL == 0)) {
            allyGenerator(ALLIES_MAX_AMOUNT);
        }
    }

    private void removeRescuedAllies() {
        for (Ally ally : getSpringHeroAllies()) {
            if (ally.isRescued()) {
                getSpringHeroHero().detachObserver(ally);
            }
        }
        getSpringHeroAllies().removeIf(Ally::isRescued);
        checkAllies();
        updateSpringHeroView();
    }

    private void moveEnemies() {
        for (Enemy enemy : getSpringHeroEnemies()) {
            enemy.move(getSpringHeroMap(), getSpringHeroHero());
        }
        updateSpringHeroView();
        removeDefeatedEnemies();
    }

    private void removeDefeatedEnemies() {
        for (Enemy enemy : getSpringHeroEnemies()) {
            if (enemy.isDefeated()) {
                getSpringHeroHero().detachObserver(enemy);
            }
        }
        getSpringHeroEnemies().removeIf(Enemy::isDefeated);
        checkLevel();
        updateSpringHeroView();
    }

    private void checkLevel() {
        if (getSpringHeroEnemies().isEmpty()) {
            setSpringHeroLevel(getSpringHeroLevel() + 1);
            enemyGenerator(ENEMIES_MAX_AMOUNT);
        }
    }

    private void round() {
        updateSpringHeroView();
        if (getSpringHeroHero().getHealth() == 0) {
            setSpringHeroGameOver(true);
        }
        if (!isSpringHeroGameOver()) {
            moveEnemies();
            updateAllies();
        }
    }

    private void play() {
        round();
        updateSpringHeroView();
    }
}