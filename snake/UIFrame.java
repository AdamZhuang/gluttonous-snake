package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UIFrame extends JFrame {
    private static final int width = 30;
    private GamePanel gamePanel;
    private Game game;

    public UIFrame(Game game) {
        super("snake game");
        this.game = game;
        this.initUI();
    }

    public void initUI() {
        int x = game.getGameMap().getX();
        int y = game.getGameMap().getY();
        this.gamePanel = new GamePanel(game, x, y, width, game.getGameMap().getPoints());
        this.getContentPane().add(gamePanel);
        this.setSize(x * width, y * width + 25);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void updateUI() {
        int[][] points = game.getMapPoint();
        this.gamePanel.updateUI(points);
        this.gamePanel.repaint();
        this.repaint();
    }
}


class GamePanel extends Panel {
    private Game game;
    private int x;
    private int y;
    private int width;
    private int[][] points;

    public GamePanel(Game game, int x, int y, int width, int[][] points) {
        super();
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.points = points;
        this.setBackground(Color.GRAY);
        this.setSize(x * width, y * width);
        this.customKeyListener();
    }

    public void updateUI(int[][] points) {
        this.points = points;
        this.repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        // 绘制边框
        g.setColor(Color.WHITE);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                g.drawRect(i * width - 1, j * width, width, width);
            }
        }
        // 根据地图信息填充格子
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (this.points[i][j] == 1) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(i * width, j * width, width - 1, width - 1);
                } else if (this.points[i][j] == 2) {
                    g.setColor(Color.orange);
                    g.fillRect(i * width, j * width, width - 1, width - 1);
                }
            }
        }
    }

    public void customKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println("key pressed");
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if(game.isRunnable() == false) {
                        game.setStatue(true);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_P) {
                    if(game.isRunnable() == true) {
                        game.setStatue(false);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    game.up();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    game.down();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    game.left();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    game.right();
                }
            }
        });
    }

}
