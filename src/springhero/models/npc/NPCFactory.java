package springhero.models.npc;

import springhero.models.hero.Hero;
import springhero.models.main.Cell;
import springhero.models.main.Constants;

/**
 * NPCFactory model class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 */
public class NPCFactory implements Constants {

    /**
     * Instance 
     * @param type
     * @param cell
     * @param hero
     * @return NPC
     */
    public NPC getNPC(NPCType type, Cell cell, Hero hero) {
        NPC npc = null;
        switch (type) {
            case ALLY -> npc = new Ally(cell, hero);
            case ENEMY -> npc = new Enemy(cell, hero);
        }
        return npc;
    }
}