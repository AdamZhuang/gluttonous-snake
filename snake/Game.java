package snake;

import snake.Object.Food;
import snake.Object.GameMap;
import snake.Object.Point;
import snake.Object.Snake;

import javax.swing.*;
import java.util.ArrayList;

public class Game implements Runnable {
    private GameMap gameMap;
    private Snake snake;
    private Food food;
    private String control = "";
    private int x;
    private int y;
    private boolean runnable = false;

    public Game(int x, int y) {
        this.x = x;
        this.y = y;
        this.initGame();
    }

    public void initGame(){
        this.gameMap = new GameMap(x, y);
        this.snake = new Snake(x , y);
        this.genFood();
        this.gameMap.updateMap(this.snake, this.food);
    }

    public void genFood() {
        while (true) {
            boolean isExist = false;
            int x_gen = (int) (Math.random() * gameMap.getX());
            int y_gen = (int) (Math.random() * gameMap.getY());
            for (Point temp : this.snake.getPoints()) {
                int x_exist = temp.getX();
                int y_exist = temp.getY();
                if (x_gen == x_exist && y_gen == y_exist) {
                    isExist = true;
                }
            }
            if (isExist == false) {
                this.food = new Food(x_gen, y_gen);
                break;
            }
        }
    }

    public void up() {
        this.snake.up();
    }

    public void down() {
        this.snake.down();
    }

    public void left() {
        this.snake.left();
    }

    public void right() {
        this.snake.right();
    }

    public int[][] getMapPoint() {
        return gameMap.getPoints();
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setStatue(boolean runnable) {
        if (runnable) {
            synchronized (control) {
                control.notifyAll();
            }
        }
        this.runnable = runnable;
    }

    public boolean isRunnable() {
        return this.runnable;
    }

    public void printSnakeInfo() {
        ArrayList<Point> snake = this.snake.getSnake();
        System.out.println("######################");
        for (Point temp : snake) {
            System.out.println(temp.getX() + " - " + temp.getY());
        }
        System.out.println("######################");
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (control) {
                    if (runnable == false) {
                        control.wait();
                    }
                }
                Thread.sleep(100);
                this.snake.move();
                if (this.snake.isDead() == true) {
                    int statue = JOptionPane.showConfirmDialog(null, "游戏结束，是否重新开始", "提示", JOptionPane.YES_NO_OPTION);
                    System.out.println("死亡");
                    System.out.println(statue);
                    if(statue == 0){
                        this.initGame();
                    } else if (statue == 1){
                        System.exit(0);
                    }
                } else if (snake.getPoints().get(0).getX() == food.getX() && snake.getPoints().get(0).getY() == food.getY()) {
                    this.snake.eat();
                    this.genFood();
                }
                this.gameMap.updateMap(this.snake, this.food);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}