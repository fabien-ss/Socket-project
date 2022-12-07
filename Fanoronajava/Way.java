package objet;

import java.io.Serializable;

public class Way implements Serializable{
    int x;
    int y;
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
    public Way(int x, int y){
        this.setX((x*390)/2);
        this.setY((y*390)/2);
    }
    public Way(){
        
    }
}
