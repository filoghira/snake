package ghirardini.filippo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class WindowPanel extends JPanel implements KeyListener {

    SnakeGame snake;

    WindowPanel(SnakeGame snake){
        this.snake = snake;
        MoveMouseListener mml = new MoveMouseListener(this);
        addMouseListener(mml);
        addMouseMotionListener(mml);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (snake.getState()){
            case 0:{
                snake.drawMenu(g);
                break;
            }
            case 1:{
                snake.refresh(g);
                snake.drawScore(g);
                break;
            }
            case 2:{
                snake.drawLost(g);
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==27)
            System.exit(0);
        else if (e.getKeyCode()==10 && snake.getState()==0)
            snake.start();
        else if (e.getKeyCode()==10 && snake.getState()==2)
            snake.reStart();
        else
            snake.setDirection(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
