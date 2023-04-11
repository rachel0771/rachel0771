package edu.neu.cs5004.maze;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    private final  int POINTS_REQUIRED_IN_CREATE_MAP = 10;


    @Test
    public void testFindPath() throws IOException {
        Maze maze = new Maze("file:src/main/resources/Maze1.json");
        String path = maze.findPath();
        Assertions.assertEquals("ABE", path);
    }

    @Test
    public void testFindPathNoStart() throws IOException {
        Maze maze = new Maze("file:src/main/resources/Maze2.json");
        String path = maze.findPath();
        Assertions.assertEquals(null, path);
    }


    @Test
    public void testFindPathNoEnd() throws IOException {
        Maze maze = new Maze("file:src/main/resources/Maze3.json");
        String path = maze.findPath();
        Assertions.assertEquals("ERROR: NO PATH FOUND", path);
    }



    @Test
    public void randomMaze() throws IOException {
        Maze maze = Maze.randomMaze();
        assertNotNull(maze);

        int expectedNumPoints = Maze.POINTS_REQUIRED_IN_CREATE_MAP;
        assertEquals(expectedNumPoints, maze.getPoints().size());

        boolean hasStartPoint = false;
        boolean hasEndPoint = false;
        for (Point point : maze.getPoints()) {
            if (point.isStart()) {
                hasStartPoint = true;
            }
            if (point.isEnd()) {
                hasEndPoint = true;
            }
        }
        assertTrue(hasStartPoint);
        assertTrue(hasEndPoint);
        assertNotEquals("ERROR: NO PATH FOUND", maze.findPath());

    }
}