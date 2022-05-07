package springhero.models.main;

import javax.swing.*;

public interface Constants {

    // paths
    String BACKGROUNDS_SRC = "src/springhero/resources/assets/backgrounds/";
    String HERO_SRC = "src/springhero/resources/assets/hero/";
    String ALLIES_SRC = "src/springhero/resources/assets/npc/allies/";
    String ENEMIES_SRC = "src/springhero/resources/assets/npc/enemies/";

    // gui
    ImageIcon MAIN_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "main.png");
    int WINDOW_HEIGHT = MAIN_BKG_IMG.getIconHeight();
    int WINDOW_WIDTH = MAIN_BKG_IMG.getIconWidth();

    int MAP_XPOS = 0;
    int MAP_YPOS = 120;
    int MAP_GRID_ROWS = 7;
    int MAP_GRID_COLS = 19;
    int CELL_HEIGHT = (WINDOW_HEIGHT - MAP_YPOS) / MAP_GRID_ROWS;
    int CELL_WIDTH = (WINDOW_WIDTH - MAP_XPOS) / MAP_GRID_COLS;

    // enums
    enum NPCType {
        ALLY,
        ENEMY
    }
}
