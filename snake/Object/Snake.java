package snake.Object;

import java.util.ArrayList;
import java.util.HashSet;

public class Snake {
    private int x;
    private int y;
    private Point remove;
    private ArrayList<Point> points;
    private Direction currentDirection = Direction.Up;    // 记录当前的方向，跟用户当前的决策无关
    private Direction nextDirection = Direction.Up;   // 记录用户的决策，是下一步的移动方向

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        this.points = new ArrayList();
        this.points.add(0, new Point(x / 2, y / 2));
        this.points.add(1, new Point(x / 2, y / 2 + 1));
        this.points.add(2, new Point(x / 2, y / 2 + 2));
    }


    public boolean left() {
        if (this.currentDirection == Direction.Up || this.currentDirection == Direction.Down) {
            this.nextDirection = Direction.Left;
        }
        return true;
    }

    public boolean right() {
        if (this.currentDirection == Direction.Up || this.currentDirection == Direction.Down) {
            this.nextDirection = Direction.Right;
        }
        return true;
    }

    public boolean up() {
        if (this.currentDirection == Direction.Left || this.currentDirection == Direction.Right) {
            this.nextDirection = Direction.Up;
        }
        return true;
    }

    public boolean down() {
        if (this.currentDirection == Direction.Left || this.currentDirection == Direction.Right) {
            this.nextDirection = Direction.Down;
        }
        return true;
    }


    public ArrayList<Point> getSnake() {
        return this.points;
    }

    public boolean move() {
        int headPointX = points.get(0).getX();
        int headPointY = points.get(0).getY();
//        System.out.println("move before:" + headPointX + "" + headPointY);
        switch (this.nextDirection) {
            case Left:
                this.currentDirection = this.nextDirection;
                points.add(0, new Point(headPointX - 1, headPointY));
                remove = points.remove(points.size() - 1);
                return true;
            case Right:
                this.currentDirection = this.nextDirection;
                points.add(0, new Point(headPointX + 1, headPointY));
                remove = points.remove(points.size() - 1);
                return true;
            case Up:
                this.currentDirection = this.nextDirection;
                points.add(0, new Point(headPointX, headPointY - 1));
                remove = points.remove(points.size() - 1);
                return true;
            case Down:
                this.currentDirection = this.nextDirection;
                points.add(0, new Point(headPointX, headPointY + 1));
                remove = points.remove(points.size() - 1);
                return true;
            default:
                System.out.println("移动出现异常");
                return false;
        }
    }

    public boolean eat() {
        points.add(remove);
        System.out.println("size:" + points.size());
        return true;
    }

    public ArrayList<Point> getPoints(){
        return this.points;
    }

    public boolean isDead() {
        // 蛇先移动再判断是否死亡
        // 判断是否撞到自己
        boolean hasSamePoint = (points.size() != new HashSet(points).size());
        if (hasSamePoint == true) {
//            System.out.println("dead:self");
            return true;
        }
        // 判断是否撞墙
        Point headPoint = points.get(0);
        int headPointX;
        int headPointY;
        switch (currentDirection) {
            case Right:
                headPointX = headPoint.getX();
                if (headPointX >= x) {
//                    System.out.println("dead:east");
                    return true;
                } else {
                    return false;
                }
            case Left:
                headPointX = headPoint.getX();
                if (headPointX < 0) {
//                    System.out.println("dead:west");
                    return true;
                } else {
                    return false;
                }
            case Up:
                headPointY = headPoint.getY();
                if (headPointY < 0) {
//                    System.out.println("dead:north");
                    return true;
                } else {
                    return false;
                }
            case Down:
                headPointY = headPoint.getY();
                if (headPointY >= y) {
//                    System.out.println("dead:south");
                    return true;
                } else {
                    return false;
                }
            default:
                System.out.println("判断死亡出错");
                return false;
        }
    }
}