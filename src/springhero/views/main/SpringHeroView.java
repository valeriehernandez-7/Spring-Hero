package springhero.views.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Spring Hero Game view class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
public class SpringHeroView extends JFrame implements ActionListener {
    public SpringHeroView(){
        setTitle("Spring-Hero");
        setSize(788,459);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getUiComponents();
        setVisible(true);
    }

    private void getUiComponents(){
        JLabel windowBackgroundLbl = new JLabel(new ImageIcon("src/springhero/resources/assets/backgrounds/main.png"));
        windowBackgroundLbl.setBounds(0, 0, windowBackgroundLbl.getIcon().getIconWidth(), windowBackgroundLbl.getIcon().getIconHeight());
        getContentPane().add(windowBackgroundLbl);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }
}