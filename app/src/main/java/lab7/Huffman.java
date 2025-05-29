/**
 * 
 * Main file for Huffman code. Reads file, and calls 
 * functions to generate map, encode, and decode string.
 * 
 * Usage: gradle run -q --args="input.txt"
 * 
 */
package lab7;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Huffman {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: gradle run -q --args (file name)");
            return;
        }
        String input = "";
        try {
            input = Files.readString(Paths.get(args[0]));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        HuffmanCode hc = new HuffmanCode();
        var freqMap = hc.countFrequencies(input);
        hc.buildTree(freqMap);

        String encoded = hc.encode(input);
        String decoded = hc.decode(encoded);

        if (input.length() < 100) {
        System.out.println("Original Text:\n" + input);
        System.out.println("Encoded Bitstring:\n" + encoded);
        System.out.println("\nDecoded Text:\n" + decoded);
        }

        double ratio = (double) encoded.length() / (input.length() * 8.0);
        System.out.println("Decoded equals input: " + input.equals(decoded));
        System.out.println("Compression ratio: " + ratio);

    }
}
