package edu.neu.cs5004.words;

import java.util.List;
import java.util.Optional;

public class WordGraph {
    private Node root;
    public WordGraph() {
       //Build your graph here
        root = new Node('e');
        List<String> words = WordParser.getWordList();
        for(String word: words){
            if(word.length() == 4){
                addWord(word);
            }
        }
    }

    private void addWord(String word) {
        Node currentNode = root;
        for (int i = 1; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            List<Node> nextNodes = currentNode.getNextNodes();
            Optional<Node> nextNodeOpt = nextNodes.stream()
                    .filter(n -> n.getData() == currentChar)
                    .findFirst();
            if (nextNodeOpt.isPresent()) {
                currentNode = nextNodeOpt.get();
            } else {
                Node newNode = new Node(currentChar);
                currentNode.addNode(newNode);
                currentNode = newNode;
            }
        }
    }

    public boolean wordInGraph(String word) {
        //Traverse Your graph here
       Node currNode = root;
       // check input String word. length == 4
        if(word.length()!= 4)return false;
        // check that word[0] = root
        if(word.charAt(0)!= root.getData())return false;

        // for loop start at 1:
       for(int i = 1; i<word.length(); i++){
           Node search = new Node(word.charAt(i));
           List<Node> nextNodes = currNode.getNextNodes();
           //boolean found = false; // e a r l
           if (! nextNodes.contains(search)){
               return false;
           }
           currNode = nextNodes.get(nextNodes.indexOf(search));
       }
       return true;
    }

    public String randomWord() {
        // Return a random word from your graph
        StringBuilder sb = new StringBuilder();
        Node currNode = root;
        while(currNode.getNextNodes().size() > 0){
            List<Node> nextNodes = currNode.getNextNodes();
            Node nextNode = nextNodes.get((int)(Math.random() * nextNodes.size()));
            sb.append(nextNode.getData());
            currNode = nextNode;
        }
        return sb.toString();
    }
}
