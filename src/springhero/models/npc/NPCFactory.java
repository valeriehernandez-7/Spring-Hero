package springhero.models.npc;

import springhero.models.main.Cell;
import springhero.models.main.Constants;

public class NPCFactory implements Constants {
    public NPC getNPC(NPCType type, Cell cell) {
        NPC npc = null;
        switch (type) {
            case ALLY -> npc = new Ally(cell);
            case ENEMY -> npc = new Enemy(cell);
        }
        return npc;
    }
}