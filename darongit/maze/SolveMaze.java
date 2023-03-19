package darongit.maze;

import java.util.Scanner;

public class SolveMaze {
    private int[][] maze;
    private int size;

    public SolveMaze(int[][] maze) {
        this.maze = maze;
        this.size = this.maze.length;
    }

    public void play() {
        String direction;
        while (true) {
            Maze.cleanScreen();

            int height = 1;
            int width = 1;

            findStartLoop:
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    if (maze[i][j] == 3) {
                        height = i;
                        width = j;
                        break findStartLoop;
                    }
                }
            }

            if (maze[height - 1][width] == 4 || maze[height + 1][width] == 4 || maze[height][width - 1] == 4 ||
                    maze[height][width + 1] == 4) {
                Maze.printAllMaze(this.maze);
                System.out.println("You Win!\n");
                break;
            }

            int[][] possibleWays = new int[4][2];
            String[] possibleDirections = new String[4];
            int possibleLenght = 0;
            if (maze[height - 1][width] == 1) {
                possibleWays[possibleLenght][0] = height - 1;
                possibleWays[possibleLenght][1] = width;
                possibleDirections[possibleLenght] = "w";
                possibleLenght++;
            }
            if (maze[height + 1][width] == 1) {
                possibleWays[possibleLenght][0] = height + 1;
                possibleWays[possibleLenght][1] = width;
                possibleDirections[possibleLenght] = "s";
                possibleLenght++;
            }
            if (maze[height][width - 1] == 1) {
                possibleWays[possibleLenght][0] = height;
                possibleWays[possibleLenght][1] = width - 1;
                possibleDirections[possibleLenght] = "a";
                possibleLenght++;
            }
            if (maze[height][width + 1] == 1) {
                possibleWays[possibleLenght][0] = height;
                possibleWays[possibleLenght][1] = width + 1;
                possibleDirections[possibleLenght] = "d";
                possibleLenght++;
            }

            if (possibleLenght == 0) {
                Maze.printAllMaze(this.maze);
                System.out.println("You loose:(\n");
                break;
            }

            Maze.printAllMaze(this.maze);
            moveLoop:
            while (true) {
                direction = getDirection("Type direction(wsad): ");
                for (int i = 0; i < possibleLenght; i++) {
                    if (possibleDirections[i].equals(direction)) {
                        maze[height][width] = 2;
                        height = possibleWays[i][0];
                        width = possibleWays[i][1];
                        maze[height][width] = 3;
                        break moveLoop;
                    }
                }
            }


        }
    }

    private String getDirection(String message) {
        String result = "start";
        Scanner sc = new Scanner(System.in);
        while (!result.equals("w") && !result.equals("a") && !result.equals("s") && !result.equals("d")) {
            System.out.print(message);
            result = sc.next().toLowerCase();
        }
        return result;
    }
}
