package ghirardini.filippo;

import java.awt.*;

class Block {

    private int x, y;
    static final int dim = 25, arc = 8;

    public Block(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() { return x;   }
    public int getY() { return y;   }

    public void move(int direction){
        switch (direction){
            case 0: {
                if(y>=dim)
                    y-=dim;
                else
                    y=1000-dim;
                break;
            }
            case 1: {
                if(y<=1000-dim)
                    y+=dim;
                else
                    y=0;
                break;
            }
            case 2: {
                if(x>=dim)
                    x-=dim;
                else
                    x=1000-dim;
                break;
            }
            case 3: {
                if(x<=1000-dim)
                    x+=dim;
                else
                    x=0;
                break;
            }
        }
    }

    // 0 top, 1 bottom, 2 left, 3 right
    public void move(int Xprec, int Yprec){
        x = Xprec;
        y = Yprec;
    }

    public void draw(Graphics g, boolean isHead){
        g.setColor(Color.green);
        g.fillRoundRect(x, y, dim, dim, arc, arc);
    }
}
