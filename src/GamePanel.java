import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int snakeX[] = new int[500];
    int snakeY[] = new int[500];
    int foodX;
    int foodY;
    int length;
    String direction;
    int score;
    Random random = new Random();
    Timer timer = new Timer(100, this);
    boolean isStart;
    boolean isEnd;
    ScoreFile scoreFile = new ScoreFile();

    public GamePanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    private void init() {
        length = 3;
        score = 0;
        for (int i = 0; i < 3; i++) {
            snakeX[i] = 100 - 25 * i;
            snakeY[i] = 100;
        }
        direction = "R";
        eat(foodX, foodY);
        isStart = false;
        isEnd = false;
    }

    private void eat(int x, int y) {
        x = 25 + 25 * random.nextInt(34);
        y = 75 + 25 * random.nextInt(24);
        for (int i = 0; i < length; i++) {
            if (snakeX[i] == x && snakeY[i] == y) {
                x = 25 + 25 * random.nextInt(34);
                y = 75 + 25 * random.nextInt(24);
            }
        }
        foodX = x;
        foodY = y;
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(Color.white);
        graphics.setColor(Color.gray);
        graphics.fillRect(25, 75, 850, 600);
        if (direction == "R") {
            Data.right.paintIcon(this, graphics, snakeX[0], snakeY[0]);
        }
        if (direction == "L") {
            Data.left.paintIcon(this, graphics, snakeX[0], snakeY[0]);
        }
        if (direction == "U") {
            Data.up.paintIcon(this, graphics, snakeX[0], snakeY[0]);
        }
        if (direction == "D") {
            Data.down.paintIcon(this, graphics, snakeX[0], snakeY[0]);
        }
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, graphics, snakeX[i], snakeY[i]);
        }
        Data.food.paintIcon(this, graphics, foodX, foodY);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("雅黑", Font.BOLD, 20));
        graphics.drawString("历史最高分：" + scoreFile.getScore(), 400, 35);
        graphics.setColor(Color.black);
        graphics.setFont(new Font("雅黑", Font.BOLD, 20));
        graphics.drawString("分数：" + score, 400, 60);
        if (isStart == false) {
            graphics.setColor(Color.black);
            graphics.setFont(new Font("雅黑", Font.BOLD, 40));
            graphics.drawString("按空格键开始游戏", 300, 300);
        }
        if (isEnd == true) {
            graphics.setColor(Color.red);
            graphics.setFont(new Font("雅黑", Font.BOLD, 40));
            graphics.drawString("游戏结束,按空格键重新开始", 300, 300);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && !isEnd) {
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            if (direction == "R") {
                snakeX[0] += 25;
            }
            if (direction == "L") {
                snakeX[0] -= 25;
            }
            if (direction == "U") {
                snakeY[0] -= 25;
            }
            if (direction == "D") {
                snakeY[0] += 25;
            }
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++;
                score += 10;
                eat(foodX, foodY);
            }
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isEnd = true;
                } else if (snakeX[0] > 850 || snakeX[0] < 25 || snakeY[0] > 650 || snakeY[0] < 75) {
                    isEnd = true;
                }
            }
            repaint();
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isEnd) {
                scoreFile.maxScore(score);
                isEnd = false;
                init();
            } else if (!isStart) {
                isStart = !isStart;
            }
            repaint();
        }
        if (keyCode == KeyEvent.VK_RIGHT && direction != "L") {
            direction = "R";
        } else if (keyCode == KeyEvent.VK_LEFT && direction != "R") {
            direction = "L";
        } else if (keyCode == KeyEvent.VK_UP && direction != "D") {
            direction = "U";
        } else if (keyCode == KeyEvent.VK_DOWN && direction != "U") {
            direction = "D";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
