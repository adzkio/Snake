import javax.swing.*;

public class StartGame extends JFrame {
    public void init() {
        setTitle("贪吃蛇");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(925, 800);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        add(new GamePanel());
    }

}
