package edu.neu.cs5004.maze;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Maze {
    public final static int POINTS_REQUIRED_IN_CREATE_MAP = 10;
    private ArrayList<Point> points;
    private Point startingPoint;
    public Maze(String fileURL) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL(fileURL);
        points = objectMapper.convertValue(objectMapper.readTree(url), new TypeReference<>(){});
        for(Point item: points) {
            if (item.isStart()) {
                startingPoint = item;
            }
        }
        if(startingPoint == null){
            new Exception("NOT START POINT");
        }
    }


    public static Maze randomMaze() throws IOException {
        // Create a set of 10 points
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "D");
        map.put(4, "E");
        map.put(5, "F");
        map.put(6, "G");
        map.put(7, "H");
        map.put(8, "I");
        map.put(9, "K");

        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < POINTS_REQUIRED_IN_CREATE_MAP; i++) {
            Point point = new Point(map.get(i));
            points.add(point);
        }

        // Mark a random point as the start
        Random random = new Random();
        Point start = points.get(random.nextInt(points.size()));
        start.setStart(true);

        Set<Point> visited = new HashSet<>();

        Map<Integer, String> directions = new HashMap<>();
        directions.put(0, "NORTH");
        directions.put(1, "SOUTH");
        directions.put(2, "WEST");
        directions.put(3, "EAST");


        String randomDirection;
        while (true) {
            Point point1 = points.get(random.nextInt(points.size()));
            Point point2 = points.get(random.nextInt(points.size()));
            if (visited.contains(point1) && visited.contains(point2)) {
                break;
            }
            visited.add(point1);
            visited.add(point2);


            randomDirection = directions.get(random.nextInt(directions.size()));
            Direction direction1 = new Direction(randomDirection, point2.getName());

            Direction[] directions1 = new Direction[4];
            for (int i = 0; i < directions1.length; i++) {
                if (directions1[i] != null) {
                    continue;
                }
                directions1[i] = direction1;
            }
            Direction direction2 = new Direction(getOppositeDirection(randomDirection));
            for (int i = 0; i < directions1.length; i++) {
                if (directions1[i] != null) {
                    continue;
                }
                directions1[i] = direction1;
            }
        }

        // Mark a different random point as the end
        Point end = points.get(random.nextInt(points.size()));
        while (end.isStart()) {
            end = points.get(random.nextInt(points.size()));
        }
        end.setEnd(true);

        Maze maze = new Maze(points);
        return maze;
    }

    private static String getOppositeDirection (String randomDirection){
            switch (randomDirection) {
                case "NORTH":
                    return "SOUTH";
                case "SOUTH":
                    return "NORTH";
                case "WEST":
                    return "EAST";
                case "EAST":
                    return "WEST";
                default:
                    return "";
            }
        }


        public String findPath () {
            if (startingPoint == null || startingPoint.isEnd()) {
                return null;
            }

            Stack<Point> navigator = new Stack<>();
            Set<Point> visited = new HashSet<>();
            Set<Point> deadEnds = new HashSet<>();

            navigator.push(startingPoint);
            visited.add(startingPoint);

            while (!navigator.isEmpty()) {
                Point current = navigator.peek();

                if (current.isEnd()) {
                    StringBuilder pathBuilder = new StringBuilder();
                    while (!navigator.isEmpty()) {
                        pathBuilder.append(navigator.pop().getName());
                    }
                    return pathBuilder.reverse().toString();
                }

                boolean foundNextPoint = false;
                for (Direction direction : current.getDirections()) {
                    String nextPointName = direction.getLocation();
                    Point nextPoint = getPointByName(nextPointName);

                    if (nextPoint != null && !visited.contains(nextPoint) && !deadEnds.contains(nextPoint)) {
                        navigator.push(nextPoint);
                        visited.add(nextPoint);
                        foundNextPoint = true;
                        break;
                    }
                }

                if (!foundNextPoint) {
                    deadEnds.add(current);
                    navigator.pop();
                }
            }
            return "ERROR: NO PATH FOUND";
        }

    public Maze(ArrayList < Point > points) {
            this.points = points;
        }

    public Maze(ArrayList < Point > points, Point startingPoint) {
            this.points = points;
            this.startingPoint = startingPoint;
        }

        public ArrayList<Point> getPoints () {
            return points;
        }

        private Point getPointByName (String name){
            for (Point point : this.points) {
                if (point.getName().equals(name)) {
                    return point;
                }
            }
            return null;
        }


    public Maze() {
        }

//        public Point getStartingPoint () {
//            return startingPoint;
//        }

    }
