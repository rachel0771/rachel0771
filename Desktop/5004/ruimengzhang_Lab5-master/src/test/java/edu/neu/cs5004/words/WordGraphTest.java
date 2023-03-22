package edu.neu.cs5004.words;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordGraphTest extends TestCase {

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testWordInGraph() {
        WordGraph graph = new WordGraph();


        assertTrue(graph.wordInGraph("evan"));
        assertFalse(graph.wordInGraph("eat"));
        assertFalse(graph.wordInGraph("tear"));
        assertFalse(graph.wordInGraph("equal"));
    }

    @Test
    public void testRandomWord() {
        WordGraph graph = new WordGraph();
        String randomWord = graph.randomWord();
        assertNotNull(randomWord);
        assertTrue(graph.wordInGraph(randomWord));
    }

    /*
    *         //Traverse Your graph here
       Node currNode = root;
       // check input String word. length == 4
        if(word.length()!= 4)return false;
        // check that word[0] = root
        if(word.charAt(0)!= root.getData())return false;

        // for loop start at 1:
       for(int i = 1; i<word.length(); i++){
           char currChar = word.charAt(i);
           List<Node> nextNodes = currNode.getNextNodes();
           //boolean found = false; // e a r l
           if (! nextNodes.contains(currChar)){
               return false;
           }
           currNode = nextNodes.get(nextNodes.indexOf(currChar));
       }
       return true;
    * */
}