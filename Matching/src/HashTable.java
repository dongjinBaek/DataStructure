import java.util.LinkedList;

public class HashTable {

    public static final int MOD = 100;
    private AVLTree[] hashSlots;

    public HashTable() {
        hashSlots = new AVLTree[MOD];
        for (int i = 0; i < MOD; i++) {
            hashSlots[i] = new AVLTree();
        }
    }

    public static int hash(String str) {
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            ret += (int)str.charAt(i);
        }
        return ret % MOD;
    }

    public void insert(String str, Location loc) {
        int h = hash(str);
        hashSlots[h].insert(str, loc);
    }

    public void print(int index) {
        hashSlots[index].printPreorder();
    }

    //assume lenght of str is always 6
    public LinkedList<Location> find(String str) {
        int h = hash(str);
        return hashSlots[h].find(str).getLocations();
    }
}
