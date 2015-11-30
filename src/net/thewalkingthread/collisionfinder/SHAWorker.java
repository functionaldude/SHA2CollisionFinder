package net.thewalkingthread.collisionfinder;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHAWorker {

    private static byte[][] cache = new byte[8][4];
    private static byte[] output = new byte[8];
    private static String prefix;
    private static MessageDigest SHAEncoder;
    private static Random randomGenerator;

    private static String hash;
    private static String input;

    public static void init(String prefix) throws NoSuchAlgorithmException {
        SHAEncoder = MessageDigest.getInstance("SHA-256");
        SHAWorker.prefix = prefix;
        randomGenerator = new Random();
    }

    public static void computeHash(){
        Integer random = randomGenerator.nextInt(1999999999);
        input = prefix + random.toString();
        byte[] hash_raw = hash(input);
        hash = Hex.encodeHexString(hash_raw);
    }

    private static byte[] hash(String input) {
        byte[] bytes = SHAEncoder.digest(input.getBytes());

        System.arraycopy(bytes, 0 * 4, cache[0], 0, 4);
        System.arraycopy(bytes, 1 * 4, cache[1], 0, 4);
        System.arraycopy(bytes, 2 * 4, cache[2], 0, 4);
        System.arraycopy(bytes, 3 * 4, cache[3], 0, 4);
        System.arraycopy(bytes, 4 * 4, cache[4], 0, 4);
        System.arraycopy(bytes, 5 * 4, cache[5], 0, 4);
        System.arraycopy(bytes, 6 * 4, cache[6], 0, 4);
        System.arraycopy(bytes, 7 * 4, cache[7], 0, 4);

        byte[] xor_cache = cache[1];
        xor_cache = XOR(xor_cache, cache[3]);
        xor_cache = XOR(xor_cache, cache[5]);
        xor_cache = XOR(xor_cache, cache[7]);
        System.arraycopy(xor_cache, 0, output, 0, 4);

        xor_cache = cache[0];
        xor_cache = XOR(xor_cache, cache[2]);
        xor_cache = XOR(xor_cache, cache[4]);
        xor_cache = XOR(xor_cache, cache[6]);
        System.arraycopy(xor_cache, 0, output, 4, 4);

        return output;
    }

    private static byte[] XOR(byte[] buffer1, byte[] buffer2){
        byte[] result = new byte[buffer1.length];

        short n = (short) 0;
        while(n<buffer1.length){
            result[n] = (byte) (buffer1[n] ^ buffer2[n]);
            n++;
        }

        return result;
    }

    public static String getHash() {
        return hash;
    }

    public static String getInput() {
        return input;
    }
}
