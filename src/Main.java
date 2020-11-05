import ghirardini.filippo.SnakeGame;

import javax.swing.*;

public class Main {
    public static void main(String[] argv) throws InterruptedException {
        JFrame window = new JFrame("Snake");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(100, 100, SnakeGame.WINDOW_SIZE, SnakeGame.WINDOW_SIZE);
        window.setLayout(null);
        window.setUndecorated(true);
        window.setResizable(false);

        SnakeGame game = new SnakeGame(window);
        window.setVisible(true);

        game.run();
    }
}
