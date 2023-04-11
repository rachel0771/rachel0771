package edu.neu.cs5004.maze;

public class Direction {
    private String direction;
    private String location;
    public Direction() {

    }
    public Direction(String direction, String location) {
        this.direction = direction;
        this.location = location;
    }

    public Direction(Object oppositeDirection) {
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public String getLocation() {
        return location;
    }
}
