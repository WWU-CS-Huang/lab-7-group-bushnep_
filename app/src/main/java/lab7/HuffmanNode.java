package lab7;

class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode left, right;

    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public boolean isLeaf(){
        return (left == null && right == null);
    }
    
    @Override
    public int compareTo(HuffmanNode o) {
        return this.frequency - o.frequency;
    }
}