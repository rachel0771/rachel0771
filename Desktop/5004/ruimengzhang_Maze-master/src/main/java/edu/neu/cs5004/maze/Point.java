package edu.neu.cs5004.maze;

import java.util.ArrayList;
import java.util.Objects;

public class Point {
    private String name;
    private Direction[] directions;
    private boolean isStart;
    private boolean isEnd;

    public Point(){}

    public Point(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Direction[] getDirections() {
        return directions;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Point(String name, Direction[] directions, boolean isStart, boolean isEnd) {
        this.name = name;
        this.directions = directions;
        this.isStart = isStart;
        this.isEnd = isEnd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirections(Direction[] directions) {
        this.directions = directions;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point point)) {
            return false;
        }
        return getName().equals(point.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }


    public void addDirection(Direction direction) {
        if (this.directions == null) {
            this.directions = new Direction[0];
        }
        int length = this.directions.length;
        Direction[] newDirections = new Direction[length + 1];
        for (int i = 0; i < length; i++) {
            newDirections[i] = this.directions[i];
        }
        newDirections[length] = direction;
        this.directions = newDirections;
    }

}