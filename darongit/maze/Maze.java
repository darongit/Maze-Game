package darongit.maze;

public class Maze {
    private int[][] maze;
    private int size;
    /**
     * 0 = wall |
     * 1 = available path |
     * 2 = walked path |
     * 3 = user position |
     * 4 = exit
     * 5 = empty field
     */
    private static String[] graphic = {"X", " ", "+", "U", "E", "/"};

    public static void cleanScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Maze(int size) {
        this.size = size;
//        this.maze = new int[size][size];

    }

    public void printMaze() {
        for (int[] row : maze) {
            for (int field : row) {
                System.out.print(graphic[field] + " ");
            }
            System.out.println();
        }
    }

    public void setMaze(int[][] maze) {
        if (maze.length != maze[0].length) {
            System.out.println("Maze have to be a square.");
            return;
        }
        this.maze = maze;
        this.size = maze.length;
    }

    public int[][] getMaze() {
        return this.maze;
    }

    public void print() {
        printAllMaze(this.maze);
    }

    public static void printAllMaze(int[][] maze) {
        if (maze == null) {
            return;
        }
        System.out.println("\n");
        for (int[] row : maze) {
            System.out.print("\t");
            for (int field : row) {
                if (field == 0 || field == 5) {
                    for (int i = 0; i < 3; i++) {
                        System.out.print(graphic[field]);
                    }
                } else {
                    System.out.print(" " + graphic[field] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }

}
