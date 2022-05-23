package springhero.models.main;

import java.awt.Color;
import java.awt.Point;

/**
 * Constants interface.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 */
public interface Constants {

    // game info
    String GAME_NAME = "Spring Hero";

    // paths
    String RESOURCES_SRC = "src/springhero/resources/";
    String FONTS_SRC = RESOURCES_SRC + "fonts/";
    String BACKGROUNDS_SRC = RESOURCES_SRC + "assets/backgrounds/";
    String HERO_SRC = RESOURCES_SRC + "assets/hero/";
    String ALLIES_SRC = RESOURCES_SRC + "assets/npc/allies/";
    String ENEMIES_SRC = RESOURCES_SRC + "assets/npc/enemies/";

    // colors
    Color NO_COLOR = new Color(0, 0, 0, 0);
    Color LEVEL_TXT_COLOR = new Color(244, 137, 246);
    Color HERO_HEALTH_BAR_COLOR = new Color(243, 136, 245);
    Color ALLIES_RESCUED_BAR_COLOR = new Color(251, 253, 253);
    Color ENEMIES_DESTROYED_BAR_COLOR = new Color(15, 238, 250);

    // calc
    int HERO_MAX_HEALTH = 15;
    int ALLIES_MAX_AMOUNT = HERO_MAX_HEALTH / 2;
    int ENEMIES_MAX_AMOUNT = HERO_MAX_HEALTH / 3;
    int ALLY_RANGE = 3;
    int ALLY_GENERATOR_SIGNAL = 3;
    int WINDOW_HEIGHT = 440;
    int WINDOW_WIDTH = 780;
    int MAP_XPOS = 0;
    int MAP_YPOS = 120;
    int MAP_GRID_ROWS = 7;
    int MAP_GRID_COLS = 19;
    int CELL_HEIGHT = (WINDOW_HEIGHT - MAP_YPOS) / MAP_GRID_ROWS;
    int CELL_WIDTH = (WINDOW_WIDTH - MAP_XPOS) / MAP_GRID_COLS;
    Point MAP_GRID_CENTER = new Point((int) (Math.floor((MAP_GRID_ROWS) / 2)), (int) (Math.floor((MAP_GRID_COLS) / 2)));

    // enums
    enum screens {
        WELCOME,
        CONTROLS,
        GAME
    }

    enum view {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    enum NPCType {
        ALLY,
        ENEMY
    }
}