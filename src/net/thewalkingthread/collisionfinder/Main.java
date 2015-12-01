package net.thewalkingthread.collisionfinder;

public class Main {
    public static SQLConnector conn;

    public static void main(String[] args) throws Exception{
        SHAWorker.init("13304351232056");
        conn = new SQLConnector();
        int hash_counter = 0;
        String hash, input;

        //noinspection InfiniteLoopStatement
        while (true){
            SHAWorker.computeHash();
            hash = SHAWorker.getHash();
            input = SHAWorker.getInput();
            conn.addHash(input, hash);
            hash_counter++;
            if (hash_counter == 5000){
                hash_counter = 0;
                conn.flushCache();
                conn.resetCache();
            }
        }
    }
}
