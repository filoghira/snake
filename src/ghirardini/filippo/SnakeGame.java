package ghirardini.filippo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class SnakeGame{

    private Snake snake;
    private Apple apple;
    private WindowPanel panel;
    private int state;  //0 Menu, 1 Game, 2 Lost
    public static final int WINDOW_SIZE = 1000;
    public static final String DEFAULT_PATH = System.getProperty("user.dir") + "/saves/best.dat";
    private int best;

    public SnakeGame(JFrame window){

        File f = new File(DEFAULT_PATH);
        if(!f.exists() || f.isDirectory()) {
            try {
                DataOutputStream file = new DataOutputStream(new FileOutputStream(DEFAULT_PATH));
                file.writeInt(0);
                file.close();
            }catch(IOException e){
                System.out.println("Error in file\n"+e.getStackTrace());
            }
        }

        panel = new WindowPanel(this);
        panel.setBounds(0, 0, WINDOW_SIZE, WINDOW_SIZE);
        panel.setBackground(Color.BLACK);
        panel.addKeyListener(panel);
        panel.setFocusable(true);
        panel.setLayout(null);
        window.add(panel);

        init();

    }

    void init(){

        try {
            DataInputStream file = new DataInputStream(new FileInputStream(DEFAULT_PATH));
            best = file.readInt();
            file.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        state = 0;
        snake = new Snake();
        apple = new Apple(snake);
    }

    public void run() throws InterruptedException {
        while(true) {
            init();
            while (state == 0) {
                Thread.sleep(15);
                panel.repaint();
            }

            while (!isLost()) {
                Thread.sleep(300);
                panel.repaint();
            }
            state = 2;

            checkScore();
            while (state == 2) {
                Thread.sleep(15);
                panel.repaint();
            }
        }
    }

    void drawScore(Graphics g){
        g.setColor(Color.green);
        g.setFont(new Font("Calibri", Font.BOLD, 30));
        g.drawString(Integer.toString(snake.getSize()), WINDOW_SIZE-100, 40);
    }

    void drawMenu(Graphics g){
        g.setColor(Color.green);
        g.setFont(new Font("Arial", Font.BOLD, 200));
        g.drawString("SNAKE", 140, 400);

        g.fillRoundRect(400, 500, 200, 100, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("PREMI INVIO", 425, 540);
        g.drawString("PER INIZIARE", 420, 580);

        /*
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Ghirardini Filippo", 330, WINDOW_SIZE-100);
        */
    }

    void drawLost(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 150));
        g.drawString("HAI PERSO", 100, 400);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("PUNTEGGIO: " + snake.getSize(), 320, 540);
        g.drawString("BEST SCORE: " + Integer.toString(best), 300, 640);

        g.fillRoundRect(400, 700, 200, 100, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("PREMI INVIO PER", 415, 740);
        g.drawString("RINCOMINCIARE", 420, 780);
    }

    void checkScore(){
        if (best< snake.getSize()){
            try {
                DataOutputStream file = new DataOutputStream(new FileOutputStream(DEFAULT_PATH));
                file.writeInt(snake.getSize());
                file.close();
            }catch(IOException e){
                System.out.println("Error in file\n"+e.getStackTrace());
            }
            best = snake.getSize();
        }
    }

    boolean isLost() {
        for (int i=0; i<snake.getSize();i++)
            if (snake.isInSnake(snake.getX(i), snake.getY(i), i)) {
                state = 2;
                return true;
            }

        return false;
    }

    void setDirection(char direction) {    snake.setDirection(direction);    }
    int getState(){ return state;   }
    void start(){   state = 1;  }
    void reStart(){   state = 0;  }

    void refresh(Graphics g){
        int Xprec = 0, Yprec = 0;

        if(!isLost()) {
            apple.draw(g);
            for (int i = 0; i < snake.getSize(); i++) {
                if (i == 0) {
                    Xprec = snake.getX(i);
                    Yprec = snake.getY(i);
                    snake.move();
                    if(snake.isInSnake(apple.getX(), apple.getY())){
                        snake.grow();
                        apple.reGenerate();
                    }
                } else {
                    int xTemp = snake.getX(i), yTemp = snake.getY(i);
                    snake.move(i, Xprec, Yprec);
                    Xprec = xTemp;
                    Yprec = yTemp;
                }
            };
            snake.draw(g);
        }

    }
}




