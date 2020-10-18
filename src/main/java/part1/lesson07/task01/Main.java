package part1.lesson07.task01;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        /*
        Let's keep words always sorted alphabetically
         */
        Set<String> treeText = new TreeSet<>((s1, s2) -> s1.trim().compareToIgnoreCase(s2.trim()));

        /*
        Reading from a file
         */
        System.out.println("File reading...");
        try (FileInputStream in = new FileInputStream("C://Temp//text_input.txt");
             BufferedInputStream bis = new BufferedInputStream(in)
        ) {
            int c;
            StringBuffer sb = new StringBuffer();
            while ((c = bis.read()) != -1) {
                if (Character.isLetterOrDigit((char) c)) {
                    sb.append((char) c);
                } else {
                    treeText.add(sb.toString().toLowerCase());
                    sb.delete(0, sb.length());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*
        Writing to a file
         */
        System.out.println("File writing...");
        try (FileOutputStream out = new FileOutputStream("C://Temp//text_output.txt");
             BufferedOutputStream bos = new BufferedOutputStream(out)) {

            for (Iterator<String> iterator = treeText.iterator(); iterator.hasNext(); ) {
                String word = iterator.next();
                if (word.length() > 0) {
                    bos.write(word.getBytes());
                    if(iterator.hasNext()) {
                        bos.write("\n".getBytes());
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Done");
    }
}
