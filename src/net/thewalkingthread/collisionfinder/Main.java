package net.thewalkingthread.collisionfinder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args[0].equals("-g")){
            SHAWorker.init("13304351232056");
            int hash_counter = 0;
            String hash, input;
            FileHandler.init("/Volumes/RuggedHDD/Dokumente/hash.txt");
            //noinspection InfiniteLoopStatement
            while (true){
                SHAWorker.computeHash();
                hash = SHAWorker.getHash();
                input = SHAWorker.getInput();
                FileHandler.addHash(hash, input);
                hash_counter++;
                if (hash_counter == 10000000){
                    hash_counter = 0;
                    FileHandler.writeHash();
                }
            }
        }
        if (args[0].equals("-c")){
            BufferedReader scanner = new BufferedReader(new FileReader("/Volumes/RuggedHDD/Dokumente/hash_sorted.txt"));
            String prev = scanner.readLine(), line = scanner.readLine();
            String prev_hash = prev.split("-")[0], line_hash = line.split("-")[0];
            System.out.println(prev_hash);
            while (true){
                if (prev_hash.equals(line_hash)){
                    System.out.println("Match found");
                    System.out.println("Hash 1: "+prev);
                    System.out.println("Hash 2: "+line);
                }
                prev = line;
                prev_hash = line_hash;
                line = scanner.readLine();
                if (line == null) break;
                line_hash = line.split("-")[0];
            }
            System.out.println("No match");
        }
    }
}
