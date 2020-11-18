package part1.lesson13.part02;

import javassist.CannotCompileException;

public class Main {

    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String args[]) throws CannotCompileException {

        for (int i = 0; ; i++) {
            Class c = cp.makeClass("generatedClass" + i).toClass();
        }

    }

}
