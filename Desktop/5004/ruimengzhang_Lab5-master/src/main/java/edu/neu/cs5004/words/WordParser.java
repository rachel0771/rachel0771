package edu.neu.cs5004.words;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class WordParser {
    /**
     * This function will read the file located in the resources directory with a list of words.
     * @return A list of words read from the resources file called Words.txt
     */
    public static List<String> getWordList() {
        InputStream is = WordParser.class.getClassLoader().getResourceAsStream("Words.txt");
        try
        {
            String text = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            String [] words = text.split("\\n");
            return Arrays.stream(words).toList();

        } catch (IOException e)
        {
            System.out.println("Unable to read file");
            System.exit(1);
        }
        return null;
    }
}
