package snake;

import snake.Object.GameMap;
import snake.Object.Snake;

public class Main {
    private static final int x = 20;
    private static final int y = 20;

    public static void main(String args[]) {
        // 新建游戏
        Game game = new Game(x, y);
        Thread gameThread = new Thread(game);
        // 开始游戏
        gameThread.start();
        // 图形界面
        UIFrame uiFrame = new UIFrame(game);
        while (true) {
            try {
                // 间隔一定时间刷新一次
                Thread.sleep(10);
                uiFrame.updateUI();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
