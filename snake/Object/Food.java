package snake.Object;

public class Food {
    Point point;

    public Food(int x, int y) {
        this.point = new Point(x, y);
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }
}
