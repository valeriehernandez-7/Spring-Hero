package springhero.models.main;

import javax.swing.*;

public interface Constants {

    // paths
    String HERO_SRC = "src/springhero/resources/assets/hero/";
    String HUD_SRC = "src/springhero/resources/assets/hud/";
    String ALLIES_SRC = "src/springhero/resources/assets/npc/allies/";
    String ENEMIES_SRC = "src/springhero/resources/assets/npc/enemies/";
    String TEXTURES_SRC = "src/springhero/resources/assets/textures/";

    // images
    ImageIcon BACKGROUND_IMG = new ImageIcon(TEXTURES_SRC + "background.png");
    ImageIcon INDICATOR_IMG = new ImageIcon(HUD_SRC + "indicator.png");

    // gui
    int WINDOW_HEIGHT = BACKGROUND_IMG.getIconHeight();
    int WINDOW_WIDTH = BACKGROUND_IMG.getIconWidth();

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
