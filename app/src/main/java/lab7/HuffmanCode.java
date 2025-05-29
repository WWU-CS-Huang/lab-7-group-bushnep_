/**
 * HuffmanCode handles frequency analysis, tree construction,
 * encoding, and decoding for Huffman compression.
 */
package lab7;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCode {
    private final Map<Character, String> encodeMap = new HashMap<>();
    private HuffmanNode root;
    
    // Count Frequencies
    public Map<Character, Integer> countFrequencies(String input) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    } 

    // Build Huffman Tree
    public HuffmanNode buildTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (var entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        root = pq.poll();
        buildEncodeMap(root, "");
        return root;
    }

    // Build encode map
    private void buildEncodeMap(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.isLeaf()) {
            encodeMap.put(node.character, code);
            return;
        }
        buildEncodeMap(node.left, code + "0");
        buildEncodeMap(node.right, code + "1");
    }

    // Encode
    public String encode(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append(encodeMap.get(c));
        }
        return sb.toString();
    }
    
    // Decode
    public String decode(String input) {
        StringBuilder sb = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : input.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.isLeaf()) {
                sb.append(current.character);
                current = root;
            }
        }
        return sb.toString();
    }
}

