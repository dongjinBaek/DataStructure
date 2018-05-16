import java.util.LinkedList;

public class AVLtree {
    private TreeNode root;

    public AVLtree() {

    }

    //print AVL tree keys in preorder traversal
    public void preorder() {

    }

    public void insert(String key) {

    }

    public TreeNode find(String key) {

    }
}

class TreeNode {
    private LinkedList<Location> locations;
    private String key;
    private TreeNode left;
    private TreeNode right;
    private int lHeight;
    private int rHeight;

    public TreeNode(String key) {

    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public int getlHeight() {
        return lHeight;
    }

    public int getrHeight() {
        return rHeight;
    }

    public LinkedList<Location> getLocations() {
        return locations;
    }

}
