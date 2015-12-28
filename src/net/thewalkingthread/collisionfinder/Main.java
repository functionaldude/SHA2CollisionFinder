package net.thewalkingthread.collisionfinder;

public class Main {

    public static void main(String[] args) throws Exception{
        SHAWorker.init("13304351232056");
        int hash_counter = 0;
        String hash, input;
        FileHandler.init("/home/zoli/Desktop/test.txt");
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
}
