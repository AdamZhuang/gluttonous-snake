package snake.Object;


import java.util.ArrayList;
import java.util.HashSet;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int hashCode() {
        Integer x_copy = new Integer(x);
        Integer y_copy = new Integer(y);
        int prime = 31;
        int result = 1;
        result = prime * result + ((x_copy == null) ? 0 : x_copy.hashCode());
        result = prime * result + ((y_copy == null) ? 0 : y_copy.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Point) {
            Point out = (Point) obj;
            return (out.getX() == x && out.getY() == y);
        }
        return false;
    }

    public static void main(String args[]) {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(1, 1));
        points.add(new Point(1, 1));
        points.add(new Point(1, 2));
        boolean hasSameElm = (points.size() != new HashSet(points).size());
        System.out.println(hasSameElm);
    }
}

