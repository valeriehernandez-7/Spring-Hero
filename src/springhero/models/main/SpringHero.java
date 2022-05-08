package springhero.models.main;

import springhero.models.main.Constants.screens;

/**
 * Spring Hero Game model class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
public class SpringHero {

    private screens screenview;

    public SpringHero() {
        this.screenview = screens.WELCOME;
    }

    public screens getScreenview() {
        return screenview;
    }

    public void setScreenview(screens screenview) {
        this.screenview = screenview;
    }
}