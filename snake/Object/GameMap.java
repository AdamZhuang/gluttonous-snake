package snake.Object;

import java.util.ArrayList;

public class GameMap {
    private int x;
    private int y;
    int[][] points;

    public GameMap(int x, int y) {
        this.x = x;
        this.y = y;
        this.initMap();
    }

    private void initMap() {
        this.points = new int[x][];
        for (int i = 0; i < x; i++) {
            points[i] = new int[y];
            for (int j = 0; j < y; j++) {
                points[i][j] = 0;
            }
        }
    }

    public void updateMap(Snake snake, Food food) {
        // 重新初始化地图为0
        this.initMap();
        // 更新蛇
        ArrayList<Point> points = snake.getPoints();
        for (Point tmp : points) {
            int x = tmp.getX();
            int y = tmp.getY();
            if (x >= 0 && x < this.x && y >= 0 && y < this.y) {
                this.points[x][y] = 1;
            }
        }
        // 更新食物
        int x = food.getX();
        int y = food.getY();
        this.points[x][y] = 2;
    }

    public int[][] getPoints() {
        return this.points;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
