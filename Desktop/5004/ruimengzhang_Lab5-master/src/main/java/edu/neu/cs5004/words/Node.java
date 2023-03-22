package edu.neu.cs5004.words;

import java.util.*;

public class Node {
    private char data;
    private List<Node> nextNodes;
    public Node(char data) {
        this.data = data;
        this.nextNodes = new ArrayList<>();
    }


    public void addNode(Node node) {
        this.nextNodes.add(node);
    }

    public List<Node> getNextNodes() {
        return nextNodes;
    }

    public char getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Node node))
        {
            return false;
        }
        return data == node.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
