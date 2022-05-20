package springhero.models.npc;

import springhero.models.hero.Hero;
import springhero.models.main.Cell;
import springhero.models.main.Constants;

public class NPCFactory implements Constants {

    public NPC getNPC(NPCType type, Cell cell, Hero hero) {
        NPC npc = null;
        switch (type) {
            case ALLY -> npc = new Ally(cell, hero);
            case ENEMY -> npc = new Enemy(cell, hero);
        }
        return npc;
    }
}