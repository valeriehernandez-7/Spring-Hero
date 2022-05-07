package springhero.models.main;

import javax.swing.*;

public interface Constants {

    // paths
    String BACKGROUNDS_SRC = "src/springhero/resources/assets/backgrounds/";
    String HERO_SRC = "src/springhero/resources/assets/hero/";
    String ALLIES_SRC = "src/springhero/resources/assets/npc/allies/";
    String ENEMIES_SRC = "src/springhero/resources/assets/npc/enemies/";

    // images
    // window
    ImageIcon ICON_IMG = new ImageIcon("src/springhero/resources/logo/icon.png");
    ImageIcon WELCOME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "welcome.png");
    ImageIcon CONTROLS_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "controls.png");
    ImageIcon GAME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "game.png");

    // calc
    int WINDOW_HEIGHT = GAME_BKG_IMG.getIconHeight();
    int WINDOW_WIDTH = GAME_BKG_IMG.getIconWidth();
    int MAP_XPOS = 0;
    int MAP_YPOS = 120;
    int MAP_GRID_ROWS = 7;
    int MAP_GRID_COLS = 19;
    int CELL_HEIGHT = (WINDOW_HEIGHT - MAP_YPOS) / MAP_GRID_ROWS;
    int CELL_WIDTH = (WINDOW_WIDTH - MAP_XPOS) / MAP_GRID_COLS;

    // enums
    enum screens {
        WELCOME,
        CONTROLS,
        GAME
    }

    enum NPCType {
        ALLY,
        ENEMY
    }
}
