package part1.lesson10.task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * This program demonstrates how to create and use a custom class loader
 */
public class Main {
    public static void main(String[] args) {

        /*
        Let's set a file where we plan to add some new lines
         */
        String dir = System.getProperty("user.dir");
        String pathname = dir + "\\SomeClass.java";

        /*
        Reading console lines we plan to add into the file
         */
        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<String>();
        String lineNew;

        while (input.hasNextLine()) {
            lineNew = input.nextLine();
            if (lineNew.isEmpty()) { // start adding lines into the file in case of user enters an empty string
                break;
            }
            lines.add(lineNew);
        }

        /*
        Buffer of lines for new content of the file
         */
        List<String> linesBuffer = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(new File(pathname));
            String previousLine = "";
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (previousLine.contains("void doWork() {")) { // let's put the console entered code right into the doWork() method
                    for (String string : lines) {
                        linesBuffer.add(string);
                    }
                }
                previousLine = nextLine;
                linesBuffer.add(nextLine);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
        Replacing the file with the new content
         */
        try (FileOutputStream out = new FileOutputStream(pathname);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {

            for (String string : linesBuffer) {
                bos.write(string.getBytes());
                bos.write("\n".getBytes());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        /*
        Create a SomeClass.class
         */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(System.in, System.out, System.err, pathname);

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
        Let's load SomeClass and create an instance of the class
         */
        ClassLoader loader;
        loader = new DynamicClassLoader(dir);
        Class newClass = null;
        try {
            newClass = Class.forName("SomeClass", true,
                    loader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Worker testWorker = null;
        try {
            testWorker = (Worker) newClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        /*
        Run the doWork method with the console entered code
         */
        testWorker.doWork();

    }
}
