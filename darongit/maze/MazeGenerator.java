package darongit.maze;

import java.util.ArrayList;
import java.util.Arrays;

public class MazeGenerator {
    private static int[][] maze;

    public static int[][] createMaze(int size) {
        maze = fillEmptyMaze(size);

        int height = 1;
        int width = 1;
        maze[height][width] = 2; // user start position
        maze[size - 2][size - 2] = 4; // end position
        ArrayList<int[]> finalWay = new ArrayList<>(); // list of coordinates correct way

        // creating main path
        mainPathLoop:
        while (true) {

            if (maze[height - 1][width] == 4 || maze[height + 1][width] == 4 ||
                    maze[height][width - 1] == 4 || maze[height][width + 1] == 4) {
                for (int i = 0; i < maze.length; i++) {
                    for (int j = 0; j < maze[i].length; j++) {
                        if (maze[i][j] == 1) {
                            int[] tmp = {i, j};
                            finalWay.add(tmp);
                        }
                    }
                }
                break mainPathLoop;
            }


            int[][] possibleWays = new int[4][2];
            int possibleWaysLength = 0;

            if (maze[height - 1][width] == 5 && maze[height - 1][width - 1] != 1 && maze[height - 1][width + 1] != 1) {
                possibleWays[possibleWaysLength][0] = height - 1;
                possibleWays[possibleWaysLength][1] = width;
                possibleWaysLength++;
            }
            if (maze[height + 1][width] == 5 && maze[height + 1][width - 1] != 1 && maze[height + 1][width + 1] != 1) {
                possibleWays[possibleWaysLength][0] = height + 1;
                possibleWays[possibleWaysLength][1] = width;
                possibleWaysLength++;
            }
            if (maze[height][width - 1] == 5 && maze[height - 1][width - 1] != 1 && maze[height + 1][width - 1] != 1) {
                possibleWays[possibleWaysLength][0] = height;
                possibleWays[possibleWaysLength][1] = width - 1;
                possibleWaysLength++;
            }
            if (maze[height][width + 1] == 5 && maze[height - 1][width + 1] != 1 && maze[height + 1][width + 1] != 1) {
                possibleWays[possibleWaysLength][0] = height;
                possibleWays[possibleWaysLength][1] = width + 1;
                possibleWaysLength++;
            }

            if (possibleWaysLength == 0) {
                height = 1;
                width = 1;
                maze = fillEmptyMaze(size);
                continue mainPathLoop;
            }

            int[] nextMove = randWay(possibleWays);
            while (nextMove[0] == 0 && nextMove[1] == 0) {
                nextMove = randWay(possibleWays);
            }
            height = nextMove[0];
            width = nextMove[1];
            maze[height][width] = 1;
        }

        int stock = 0;
        // create more tunnels
        while (true) {
            ArrayList<int[]> emptyPlaces = new ArrayList<>();
            for (int i = 1; i < maze.length - 1; i++) {
                for (int j = 1; j < maze[i].length - 1; j++) {
                    if (maze[i][j] == 5) {
                        int[] tmp = {i, j};
                        emptyPlaces.add(tmp);
                    }
                }
            }
            if (emptyPlaces.size() < 5) {
                break;
            }
            int[] newPos = emptyPlaces.get(randIdx(emptyPlaces.size()));
            height = newPos[0];
            width = newPos[1];

            int[][] possibleWays = new int[4][2];
            int possibleWaysLength = 0;

            if (maze[height - 1][width] == 5 && maze[height - 1][width - 1] != 1 && maze[height - 1][width + 1] != 1) {
                possibleWays[possibleWaysLength][0] = height - 1;
                possibleWays[possibleWaysLength][1] = width;
                possibleWaysLength++;
            }
            if (maze[height + 1][width] == 5 && maze[height + 1][width - 1] != 1 && maze[height + 1][width + 1] != 1) {
                possibleWays[possibleWaysLength][0] = height + 1;
                possibleWays[possibleWaysLength][1] = width;
                possibleWaysLength++;
            }
            if (maze[height][width - 1] == 5 && maze[height - 1][width - 1] != 1 && maze[height + 1][width - 1] != 1) {
                possibleWays[possibleWaysLength][0] = height;
                possibleWays[possibleWaysLength][1] = width - 1;
                possibleWaysLength++;
            }
            if (maze[height][width + 1] == 5 && maze[height - 1][width + 1] != 1 && maze[height + 1][width + 1] != 1) {
                possibleWays[possibleWaysLength][0] = height;
                possibleWays[possibleWaysLength][1] = width + 1;
                possibleWaysLength++;
            }

            if (stock > 10) {
                break;
            }

            if (possibleWaysLength == 0) {
                stock++;
                continue;
            }
            stock = 0;


            int[] newPosition = possibleWays[randIdx(possibleWaysLength)];
            maze[height][width] = 1;
            height = newPosition[0];
            width = newPosition[1];


        }

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 5) {
                    maze[i][j] = 0;
                }
            }
        }
        return maze;
    }

    private static boolean randBoolean() {
        int number = (int) (Math.random() * 1000);
        return number % 2 == 0;
    }

    private static int[] randWay(int[][] list) {
        int idx = (int) (Math.random() * list.length);
        return list[idx];
    }

    private static int randIdx(int number) {
        return (int) (Math.random() * number);
    }

    private static int[][] fillEmptyMaze(int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (i == 0 || i == result.length - 1 || j == 0 || j == result[i].length - 1) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = 5;
                }
            }
        }
        result[1][1] = 3; // user start position
        result[size - 2][size - 2] = 4; // end position
        return result;
    }

}
