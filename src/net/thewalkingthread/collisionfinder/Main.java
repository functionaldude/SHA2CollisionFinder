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
            FileHandler.init("/media/zoli/RuggedHDD/Dokumente/hash.txt");
            //noinspection InfiniteLoopStatement
            while (true){
                SHAWorker.computeHash();
                hash = SHAWorker.getHash();
                input = SHAWorker.getInput();
                FileHandler.addHash(hash, input);
                hash_counter++;
                if (hash_counter == 5000000){
                    hash_counter = 0;
                    FileHandler.writeHash();
                }
            }
        }
        if (args[0].equals("-c")){
            BufferedReader scanner = new BufferedReader(new FileReader("/media/zoli/RuggedHDD/Dokumente/hash_sorted.txt"));
            String[] prev = scanner.readLine().split("-"), line = scanner.readLine().split("-");
            while (true){
                if (prev[0].equals(line[0]) && !prev[1].equals(line[1])){
                    System.out.println("Match found");
                    System.out.println("Hash: "+prev[0]);
                    System.out.println("In 1: "+prev[1]);
                    System.out.println("In 2: "+line[1]);
                }
                prev = line;
                String line_raw = scanner.readLine();
                if (line_raw == null) break;
                line = line_raw.split("-");
            }
            System.out.println("No match");
        }
    }
}
