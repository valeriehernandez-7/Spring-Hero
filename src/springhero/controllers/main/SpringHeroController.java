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

    public SpringHeroController(){
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
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (getSpringHeroScreenview() == screens.GAME) {
            if (key == KeyEvent.VK_W) {
                getSpringHeroHero().move(view.UP, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_S) {
                getSpringHeroHero().move(view.DOWN, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_A) {
                getSpringHeroHero().move(view.LEFT, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_D) {
                getSpringHeroHero().move(view.RIGHT, getSpringHeroMap());
            }
            if (key == KeyEvent.VK_F) {
                System.out.println("ATTACK"); // TODO: DELETE LINE
                // TODO: getSpringHeroHero().attack();
            }
            // TODO: observer events
            getSpringHeroMap().update();
            updateSpringHeroView();
        } else if (getSpringHeroScreenview() == screens.CONTROLS) {
            if (key == KeyEvent.VK_P) {
                setSpringHeroScreenview(screens.GAME);
                this.springHeroView.screenSetup(getSpringHeroScreenview());
                // TODO: gameInit()
            }
        }  else if (getSpringHeroScreenview() == screens.WELCOME) {
            if (key == KeyEvent.VK_X) {
                setSpringHeroScreenview(screens.CONTROLS);
                this.springHeroView.screenSetup(getSpringHeroScreenview());
            }
        }
    }

    public void updateSpringHeroView() {
        this.springHeroView.update(isSpringHeroGameOver(), getSpringHeroLevel(), getSpringHeroMap(), getSpringHeroHero(), getSpringHeroAllies(), getSpringHeroEnemies());
    }
}