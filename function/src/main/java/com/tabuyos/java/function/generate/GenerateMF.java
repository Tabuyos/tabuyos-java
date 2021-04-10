package com.tabuyos.java.function.generate;

import java.io.*;
import java.util.StringJoiner;

/**
 * @Author Tabuyos
 * @Time 2020/12/19 21:52
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class GenerateMF {

    private static final String charQueue = "ABCDEFGHIJKLMNOPQSTUVWXYZ";
    private static final String basePath = "F:\\Projects\\IDEAWorkspace\\tabuyos-java\\function\\src\\main\\java\\com\\tabuyos\\java\\function\\magic";
    private static final int count = 25;

    private static void genRMF() throws IOException {
        String RMFString = "package com.tabuyos.java.function.magic;\n" +
                "\n" +
                "/**\n" +
                " * @Author Tabuyos\n" +
                " * @Time 2020/12/19 21:48\n" +
                " * @Site www.tabuyos.com\n" +
                " * @Email tabuyos@outlook.com\n" +
                " * @Description\n" +
                " */\n" +
                "@FunctionalInterface\n" +
                "public interface RMF<TYPE> {\n" +
                "    R apply(ARG);\n" +
                "}\n";
        loopWriter(RMFString, "RMF", true);
    }

    private static void genNMF() throws IOException {
        String NMFString = "package com.tabuyos.java.function.magic;\n" +
                "\n" +
                "/**\n" +
                " * @Author Tabuyos\n" +
                " * @Time 2020/12/19 21:50\n" +
                " * @Site www.tabuyos.com\n" +
                " * @Email tabuyos@outlook.com\n" +
                " * @Description\n" +
                " */\n" +
                "@FunctionalInterface\n" +
                "public interface NMF<TYPE> {\n" +
                "    void apply(ARG);\n" +
                "}\n";
        loopWriter(NMFString, "NMF", false);
    }

    private static String generateArgs(int count) {
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < count; i++) {
            char c = charQueue.toCharArray()[i];
            String string = String.valueOf(c).toUpperCase() + " " + String.valueOf(c).toLowerCase();
            joiner.add(string);
        }
        return joiner.toString();
    }

    private static String generateTypes(int count, boolean rb) {
        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < count; i++) {
            char c = charQueue.toCharArray()[i];
            String string = String.valueOf(c).toUpperCase();
            joiner.add(string);
        }
        if (rb) {
            joiner.add("R");
        }
        return joiner.toString();
    }

    private static void loopWriter(String content, String flag, boolean rb) throws IOException {
        for (int i = 0; i < count; i++) {
            String types = generateTypes(i + 1, rb);
            String args = generateArgs(i + 1);
            String all = content.replaceAll("TYPE", types).replaceAll("ARG", args).replaceAll(flag, flag + (i + 1));
            File file = new File(basePath + File.separator + flag + (i + 1) + ".java");
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
                if (newFile) {
                    System.out.println(file.getPath() + " 创建成功!");
                    FileWriter fw = new FileWriter(file);
                    fw.write(all);
                    fw.flush();
                    fw.close();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        genNMF();
        genRMF();
    }
}
