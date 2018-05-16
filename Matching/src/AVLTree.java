import java.util.LinkedList;

public class AVLTree {
    private TreeNode treeRoot;

    public AVLTree() {
        treeRoot = null;
    }

    //print AVL tree keys in preorder traversal
    public void printPreorder() {
        if (treeRoot == null)
            System.out.println("EMPTY");
        else {
            System.out.print(treeRoot.getKey());
            printPreorder(treeRoot.getLeft());
            printPreorder(treeRoot.getRight());
            System.out.println();
        }
    }

    private void printPreorder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(" ");
        System.out.print(root.getKey());
        printPreorder(root.getLeft());
        printPreorder(root.getRight());

    }

    public void insert(String key, Location loc) {
        if (treeRoot == null) {
            treeRoot = new TreeNode(key , loc);
        } else {
            treeRoot = insert(treeRoot, key, loc);
        }
    }

    //insert and return root
    private TreeNode insert(TreeNode root, String key, Location loc) {
        if (root == null)
            return new TreeNode(key, loc);
        else if (root.getKey().equals(key)) {
            root.insert(loc);
        } else if (root.getKey().compareTo(key) > 0) {
            root.setLeft(insert(root.getLeft(), key, loc));
        } else {
            root.setRight(insert(root.getRight(), key, loc));
        }
        return root;
    }

    public TreeNode find(String key) {
        return find(treeRoot, key);
    }

    private TreeNode find(TreeNode root, String key) {
        if (root == null)
            return new TreeNode(key, new Location(0, 0));
        if (root.getKey().compareTo(key) == 0)
            return root;
        else if (root.getKey().compareTo(key) > 0)
            return find(root.getLeft(), key);
        else
            return find(root.getRight(), key);
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
        locations = new LinkedList<>();
        this.key = key;
    }

    public TreeNode(String key, Location loc) {
        this(key);
        insert(loc);
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

    public String getKey() {
        return key;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public LinkedList<Location> getLocations() {
        return locations;
    }

    public void insert(Location loc) {
        int idx = 0;
        for (Location curr : locations) {
            if (curr.compareTo(loc) < 0)
                idx++;
        }
        locations.add(idx, loc);
    }
}

