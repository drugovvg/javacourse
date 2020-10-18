package part1.lesson07.task02;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("File writing...");

        getFiles("C://Temp//", 3, 3, getWords(), 20);

        System.out.println("Done");
    }

    /**
     * Generate n files with random generated text
     *
     * @param path        folder for generated files
     * @param n           amount of files to generate
     * @param size        amount of paragraphs to generate for each of the files
     * @param words       words array
     * @param probability probability of using of a certain word in the words array
     */
    public static void getFiles(String path, int n, int size, String[] words, int probability) {

        for (int i = 0; i < n; i++) {
            String fullPath = path + "generated_file_" + i + ".txt";
            getFile(fullPath, size, words, probability);
            System.out.println(fullPath + " is generated.");
        }

    }

    /**
     * Generate an array of random generated words. Amount of words is random. Maximum amount of words is 1000.
     *
     * @return an array of random generated words
     */
    private static String[] getWords() {

        String[] words = new String[new Random().nextInt(1000) + 1];

        for (int i = 0; i < words.length; i++) {
            words[i] = getRandomWord();
        }

        return words;
    }

    /**
     * Generate a random alphabetical string. Maximum amount of letters is 15.
     *
     * @return random alphabetical string
     */
    private static String getRandomWord() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = new Random().nextInt(15) + 1;
        Random random = new Random();

        String s = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return s;
    }

    /**
     * Create a file with random text based on an array of words.
     *
     * @param path        folder for generated files
     * @param size        amount of paragraphs to generate
     * @param words       words array
     * @param probability probability of using of a certain word in the words array
     */
    private static void getFile(String path, int size, String[] words, int probability) {
        int np = size; // Amount of Paragraphs
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < np; i++) {
            text.append(getParagraph(words, probability));
            if (i < np - 1) {
                text.append("\r\n");
            }
        }

        /*
        Writing to a file
         */
        try (FileOutputStream out = new FileOutputStream(path);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {

            bos.write(text.toString().getBytes());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Generate a paragraph based on a words array.
     *
     * @param words       words array
     * @param probability probability of using of a certain word in the words array
     * @return paragraph based on a words array.
     */
    private static String getParagraph(String[] words, int probability) {
        int n3 = new Random().nextInt(20) + 1; // Paragraph's length

        StringBuilder paragraph = new StringBuilder();

        for (int i = 0; i < n3; i++) {
            paragraph.append(getSentence(words, probability));
            if (i < n3 - 1) {
                paragraph.append(" ");
            }
        }

        return paragraph.toString();
    }

    /**
     * Generate a sentence based on a words array. At the end of the sentence we randomly put ".", "!" or "?". Also,
     * we add "," after each word with 6% probability.
     *
     * @param words       words array
     * @param probability probability of using of a certain word in the words array
     * @return sentence based on a words array.
     */
    private static String getSentence(String[] words, int probability) {
        int n1 = new Random().nextInt(15) + 1; // Sentence's length

        StringBuilder sentence = new StringBuilder();

        int wordsInSentenceCounter = 0;
        for (int i = 0, wordsLength = words.length; i < wordsLength; i++) {

            if (new Random().nextInt(100) <= probability) {
                wordsInSentenceCounter++;

                String word;
                if (wordsInSentenceCounter == 1) {
                    word = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
                } else word = words[i].toLowerCase();

                sentence.append(word);
                if (wordsInSentenceCounter >= n1) {
                    String[] endOfSentenceOptions = new String[]{".", "!", "?"};
                    sentence.append(endOfSentenceOptions[new Random().nextInt(endOfSentenceOptions.length)]);
                    return sentence.toString();
                } else {
                    if (new Random().nextInt(100) <= 6) {
                        sentence.append(",");
                    }
                }
                sentence.append(" ");
            }
        }

        return sentence.toString();

    }


}
