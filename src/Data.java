import javax.swing.*;
import java.net.URL;

public class Data {
    //头部
    public static URL upUrl = Data.class.getResource("/static/up.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static URL downUrl = Data.class.getResource("/static/down.png");
    public static ImageIcon down = new ImageIcon(downUrl);
    public static URL leftUrl = Data.class.getResource("/static/left.png");
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static URL rightUrl = Data.class.getResource("/static/right.png");
    public static ImageIcon right = new ImageIcon(rightUrl);
    //身体
    public static URL bodyUrl = Data.class.getResource("/static/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //食物
    public static URL foodUrl = Data.class.getResource("/static/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}
