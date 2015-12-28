package net.thewalkingthread.collisionfinder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static Path path;
    private static List<String> buffer;

    public static void init(String path){
        FileHandler.path = Paths.get(path);
        buffer = new ArrayList<>();
    }

    public static void addHash(String hash, String input){
        buffer.add(hash + " - " + input);
    }

    public static void writeHash() throws IOException {
        Files.write(path, buffer, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        buffer.clear();
    }
}
