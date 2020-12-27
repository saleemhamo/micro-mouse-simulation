package microMouse;

;public class Mouse {
    public enum DIRECTION{
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
    int x = 0; //x-axis position 
    int y = 0; //-axis position
    DIRECTION direction = DIRECTION.UP;

    public DIRECTION getMouseDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMouseDirection(DIRECTION mouseDirection) {
        this.direction = mouseDirection;
    }



    
} 
