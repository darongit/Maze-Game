import darongit.maze.Maze;
import darongit.maze.MazeGenerator;
import darongit.maze.SolveMaze;

public class Main {
    public static void main(String[] args) {
        int size;
        try {
            size = Integer.valueOf(args[0]);
            if (size<15) {
                size = 15;
            } else if (size>50) {
                size = 50;
            }
        } catch (NumberFormatException e) {
            size = 20;
        } catch (IndexOutOfBoundsException e) {
            size = 20;
        }
        Maze.cleanScreen();
        SolveMaze game = new SolveMaze(MazeGenerator.createMaze(size));
        game.play();

    }

}
