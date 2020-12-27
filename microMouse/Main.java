package microMouse;

import microMouse.Mouse.DIRECTION;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Main {

    public enum PATH {
        FRONT, RIGHT, LEFT
    }

    public static boolean solved = false;
    public static MazeCell[][] maze = new MazeCell[16][16];
    public static Mouse mouse = new Mouse();
    public static int temp = 0;

    private static void log(String text) {
        System.err.println(text);
    }

    public static void main(String[] args) {
        Main.log("Running...");
        API.setColor(0, 0, 'G');
        API.setText(0, 0, "abc");

        //Initiate the maze
        for(int i = 0 ; i < 16 ; i++){
            for (int j = 0 ; j < 16 ; j++) {
                maze[i][j] = new MazeCell(i, j);
            }
        }

        // Investigation
    
        // rightHandAlgorithm();

        algorithm();



        // Solving
        // TODO 
    }
    static void algorithm() {
        while (!solved) {

            double left = 100.0;
            double right = 100.0;
            double up = 100.0;
            double down = 100.0;

            
            fillMazeCell();

            if (!API.wallRight()) {
                rotateRight();
                if (isSolved()) {
                    break;
                }
            } else if (!API.wallFront()) {
                moveForward();
                if (isSolved()) {
                    break;
                }
                continue;
            } else  if (!API.wallLeft()) {
                rotateLeft();
                if (isSolved()) {
                    break;
                }
                continue;
            } else  {
                turnAround();
            }

            moveForward();

            // if (locked()) {
            // }
        }
    }
    public double enclideanDistance(int x, int y) {       
    return Math.sqrt((7 - y) * (7 - y) + (7 - x) * (7 - x));
    }       

    static void rightHandAlgorithm () {
        while (!solved) {

            fillMazeCell();

            if (!API.wallRight()) {
                rotateRight();
                if (isSolved()) {
                    break;
                }
            } else if (!API.wallFront()) {
                moveForward();
                if (isSolved()) {
                    break;
                }
                continue;
            } else  if (!API.wallLeft()) {
                rotateLeft();
                if (isSolved()) {
                    break;
                }
                continue;
            } else  {
                turnAround();
            }

            moveForward();

            // if (locked()) {
            // }
        }
    }

    static void printMaze() {
        // FileWriter myWriter= new FileWriter("./maze.txt");
        // try {
            
        //   } catch (IOException e) {
        //     Main.log("An error occurred.");
        //   }
    //     for(int i = 15 ; i > 0 ; i--){
    //         for (int j = 0 ; j < 16 ; j++) {
    //             if (maze[i][j].isWallLeft()){
    //                 myWriter.write("|");
    //             }
    //             if (maze[i][j].isWallUp() && maze[i][j].isWallDown()){
    //                 myWriter.write("-_");
    //             } else if (maze[i][j].isWallUp()){
    //                 myWriter.write("--");
    //             } else if (maze[i][j].isWallDown()){
    //                 myWriter.write("__");
    //             } else {
    //                 myWriter.write("  ");
    //             }

    //             if (maze[i][j].isWallRight()){
    //                 myWriter.write("|");
    //             }
    //         }
    //         myWriter.write("\n");
    //     }
    //     myWriter.close();
    }

    static void fillMazeCell() {

        
        if (mouse.direction == DIRECTION.UP) {
            if (API.wallRight()) {
                maze[mouse.x][mouse.y].setWallRight(true);
            } else {
                maze[mouse.x][mouse.y].setWallRight(false);
            }
            if (API.wallFront()) {
                maze[mouse.x][mouse.y].setWallUp(true);
            } else {
                maze[mouse.x][mouse.y].setWallRight(false);
            }
            if (API.wallLeft()) {
                maze[mouse.x][mouse.y].setWallLeft(true);
            } else {
                maze[mouse.x][mouse.y].setWallRight(false);
            }
        }

        if (mouse.direction == DIRECTION.DOWN) {

            if (API.wallRight()) {
                maze[mouse.x][mouse.y].setWallLeft(true);
            } else {
                maze[mouse.x][mouse.y].setWallLeft(false);
            }
            if (API.wallFront()) {
                maze[mouse.x][mouse.y].setWallDown(true);
            } else {
                maze[mouse.x][mouse.y].setWallDown(false);
            }
            if (API.wallLeft()) {
                maze[mouse.x][mouse.y].setWallRight(true);
            } else {
                maze[mouse.x][mouse.y].setWallRight(false);
            }

        }

        if (mouse.direction == DIRECTION.RIGHT) {
            if (API.wallRight()) {
                maze[mouse.x][mouse.y].setWallDown(true);
            } else {
                maze[mouse.x][mouse.y].setWallDown(false);
            }
            if (API.wallFront()) {
                maze[mouse.x][mouse.y].setWallRight(true);
            } else {
                maze[mouse.x][mouse.y].setWallRight(false);
            }
            if (API.wallLeft()) {
                maze[mouse.x][mouse.y].setWallUp(true);
            } else {
                maze[mouse.x][mouse.y].setWallUp(false);
            }
        }

        if (mouse.direction == DIRECTION.LEFT) {
            if (API.wallRight()) {
                maze[mouse.x][mouse.y].setWallUp(true);
            } else {
                maze[mouse.x][mouse.y].setWallUp(false);
            }
            if (API.wallFront()) {
                maze[mouse.x][mouse.y].setWallLeft(true);
            } else {
                maze[mouse.x][mouse.y].setWallLeft(false);
            }
            if (API.wallLeft()) {
                maze[mouse.x][mouse.y].setWallDown(true);
            } else {
                maze[mouse.x][mouse.y].setWallDown(false);
            }
        }

    }


    static void moveForward() {
        API.moveForward();
        updateMousePosition();
    }

    static void rotateRight() {
        API.turnRight();
        updateMouseDirection(PATH.RIGHT);
    }

    static void rotateLeft() {
        API.turnLeft();
        updateMouseDirection(PATH.LEFT);
    }

    static void turnAround() {
        rotateLeft();
        rotateLeft();
    }

    static boolean locked() {
        return (API.wallFront() && API.wallLeft() && API.wallRight());
    }

    static boolean isSolved() {
        if ((mouse.x == 9 || mouse.x == 8) && (mouse.y == 9 || mouse.y == 8)) {
            solved = true;
            return true;
        }
        return false;

    }

    static void updateMouseDirection(PATH path) {

        if (path == PATH.RIGHT) {
            if (mouse.direction == DIRECTION.UP) {
                mouse.direction = DIRECTION.RIGHT;
                return;
            }
            if (mouse.direction == DIRECTION.DOWN) {
                mouse.direction = DIRECTION.LEFT;
                return;
            }
            if (mouse.direction == DIRECTION.RIGHT) {
                mouse.direction = DIRECTION.DOWN;
                return;
            }
            if (mouse.direction == DIRECTION.LEFT) {
                mouse.direction = DIRECTION.UP;
                return;
            }
        } else if (path == PATH.LEFT) {
            if (mouse.direction == DIRECTION.UP) {
                mouse.direction = DIRECTION.LEFT;
                return;
            }
            if (mouse.direction == DIRECTION.DOWN) {
                mouse.direction = DIRECTION.RIGHT;
                return;
            }
            if (mouse.direction == DIRECTION.RIGHT) {
                mouse.direction = DIRECTION.UP;
                return;
            }
            if (mouse.direction == DIRECTION.LEFT) {
                mouse.direction = DIRECTION.DOWN;
                return;
            }
        }

    }

    static void updateMousePosition() {
        if (mouse.direction == DIRECTION.UP)
            mouse.y++;
        if (mouse.direction == DIRECTION.DOWN)
            mouse.y--;
        if (mouse.direction == DIRECTION.RIGHT)
            mouse.x++;
        if (mouse.direction == DIRECTION.LEFT)
            mouse.x--;

        printMousePosition();

    }

    public static void printMousePosition() {
        Main.log("Mouse Position: (" + mouse.x + ", " + mouse.y + ") " + "DIRECTION " + mouse.direction);

    }

}
