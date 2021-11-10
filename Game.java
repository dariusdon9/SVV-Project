import java.util.Random;

public class Game {
    public final byte alive = 1;
    public final byte dead = 1;

    private byte[][] taking_cell;
    private byte[][] putting_cell;

    int height;
    int width;

    public Game(int height, int width) {
        this.height = height;
        this.width = width;
        taking_cell = new byte[height][width];
        putting_cell = new byte[height][width];
    }

    //get the heightof the game table
    public int getHeight() {
        return height;
    }

    //public cell function getWidth, get the dimension of table
    public int getWidth() {
        return width;
    }

    //get cell function, not final form
    public byte getCell(int x, int y) {
        return taking_cell[x][y];
    }

    //set cell function, not final form
    public byte setCell(int x, int y) {
        return putting_cell[x][y];
    }

    //random function, for creating random board game
    public void randomGame(int rHeight, int rWidth) {
        height = rHeight;
        width = rWidth;
        taking_cell = new byte[rHeight][rWidth];
        putting_cell = new byte[rHeight][rWidth];
        Random random = new Random();
        int i;
        int j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                byte number = (byte) (Math.abs(random.nextInt()) % 2);
                taking_cell[i][j] = number;
            }
        }
    }

    //empty game function
    public void emptyGame(int eHeight, int eWidth) {
        height = eHeight;
        width = eWidth;
        taking_cell = new byte[eHeight][eWidth];
        putting_cell = new byte[eHeight][eWidth];
        Random random = new Random();
        int i;
        int j;
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                taking_cell[i][j] = 0;
            }
        }
    }

    //it will be used for database
    public boolean copyFromGame(Game game) {
        if (game.getHeight() != height || game.getWidth() != width)
            return false;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                taking_cell[i][j] = game.getCell(i, j);
            }
        }
        return true;
    }

    //using to database
    public void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (taking_cell[i][j] == 0)
                    System.out.print("0");
                else System.out.print("1");
            }
            System.out.println(" ");
        }
    }

    public int neighbour(int x, int y) {
        int counter = 0;
        int previous_column = x - 1;
        int next_column = x + 1;
        int previous_row = y - 1;
        int next_row = y + 1;
        //delete border column
        if (x == 0)
            previous_column = width - 1;
        //verify border column
        if (x == width - 1)
            next_column = 0;
        //delete border row
        if (y == 0)
            previous_row = height - 1;
        //verify border row
        if (y == height - 1)
            next_row = 0;
        /* possible positions for neighbours
           ._._._
           |_|_|_|
           |_|*|_|
           |_|_|_|
         */
        counter = counter + taking_cell[previous_column][previous_row];
        counter = counter + taking_cell[x][previous_row];
        counter = counter + taking_cell[next_column][previous_row];
        counter = counter + taking_cell[previous_column][y];
        counter = counter + taking_cell[next_column][y];
        counter = counter + taking_cell[previous_column][next_row];
        counter = counter + taking_cell[x][next_row];
        counter = counter + taking_cell[next_column][next_row];

        return counter;
    }

    public void step(int start, int end){
        for(int i = start; i< end; i++){
            for(int j = start; j < end; j++){
                boolean isAlive;
                if ((taking_cell[i][j] == 0) ? false : true) isAlive = true;
                else isAlive = false;
                int neighbours = neighbour(i,j);

                if(isAlive){
                    if(neighbours == 1 || neighbours == 0)
                        putting_cell[i][j] = 0;
                    if(neighbours >= 4)
                        putting_cell[i][j] = 0;
                    if(neighbours == 3 || neighbours == 2)
                        putting_cell[i][j] = 1;
                }
                else{
                    if(neighbours == 3)
                        putting_cell[i][j] = 1;
                        else putting_cell [i][j] = taking_cell[i][j];
                }
            }
        }
    }

    public void swapArray() {
        byte[][] swap = taking_cell;
        taking_cell = putting_cell;
        putting_cell = swap;
    }
    //swap taking with putting at every iteration
    public void auto(int iterations){
        for(int i = 0; i < iterations; i++){
            step(0,height);
            swapArray();
        }
    }
}

