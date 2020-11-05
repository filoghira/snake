package ghirardini.filippo;

import java.awt.*;

class Apple{
    private int X, Y;
    private Snake snake;
    static final int dim = 25, arc = 8;

    Apple(Snake snake){
        this.snake = snake;
        do {
            reGenerate();
        }while (X==500 && Y==500);
    }

    int getX() { return X;   }
    int getY() { return Y;   }

    void reGenerate(){

        do{
            X = ((int) (Math.random()*(1000/dim)))*dim;
            Y = ((int) (Math.random()*(1000/dim)))*dim;
        }while(snake.isInSnake(X, Y));

    }

    void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRoundRect(X, Y, dim, dim, arc, arc);
    }

}
