package ghirardini.filippo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Snake{

    private List<Block> snake = new ArrayList<Block>();
    private int direction;

    Snake() {
        snake.add(new Block(500, 500));
    }

    int getSize(){   return snake.size();    }

    int getX(int i){ return snake.get(i).getX();    }
    int getY(int i){ return snake.get(i).getY();    }

    boolean isInSnake(int X, int Y){
        for (int i=0; i<snake.size(); i++)
            if(snake.get(i).getX() == X && snake.get(i).getY() == Y)
                return true;
        return false;
    }

    boolean isInSnake(int X, int Y, int k){
        for (int i=k+1; i<snake.size(); i++)
            if(snake.get(i).getX() == X && snake.get(i).getY() == Y)
                return true;
        return false;
    }

    void draw(Graphics g) {
        snake.get(0).draw(g, true);
        for(int i=1; i<snake.size(); i++)
            snake.get(i).draw(g, false);
    }

    void setDirection(char direction) {
        if(direction=='A' || direction=='a' && this.direction!=3)
            this.direction = 2;
        else if(direction=='S' || direction=='s' && this.direction!=0)
            this.direction = 1;
        else if(direction=='D' || direction=='d' && this.direction!=2)
            this.direction = 3;
        else if(direction=='W' || direction=='w' && this.direction!=1)
            this.direction = 0;
    }

    void grow(){

        int x=0, y=0, counter=snake.size()-1;

        if(snake.size() > 1) {
            if (snake.get(counter - 1).getX() < snake.get(counter).getX()) {
                x = snake.get(counter).getX() + Block.dim;
                y = snake.get(counter).getY();
            } else if (snake.get(counter - 1).getX() > snake.get(counter).getX()) {
                x = snake.get(counter).getX() - Block.dim;
                y = snake.get(counter).getY();
            } else if (snake.get(counter - 1).getY() > snake.get(counter).getY()) {
                y = snake.get(counter).getY() - Block.dim;
                x = snake.get(counter).getX();
            } else if (snake.get(counter - 1).getY() < snake.get(counter).getY()) {
                y = snake.get(counter).getY() + Block.dim;
                x = snake.get(counter).getX();
            }
        }else{
            switch (direction){
                case 0: {
                    y = snake.get(counter).getY() + Block.dim;
                    x = snake.get(counter).getX();

                }
                case 1: {
                    y = snake.get(counter).getY() - Block.dim;
                    x = snake.get(counter).getX();

                }
                case 2: {
                    x = snake.get(counter).getX() + Block.dim;
                    y = snake.get(counter).getY();
                }
                case 3: {
                    x = snake.get(counter).getX() - Block.dim;
                    y = snake.get(counter).getY();
                }
            }
        }

        snake.add(new Block(x, y));
    }

    void move(){    snake.get(0).move(direction);   }
    void move(int i, int xPrec, int yPrec){    snake.get(i).move(xPrec, yPrec);   }
}
