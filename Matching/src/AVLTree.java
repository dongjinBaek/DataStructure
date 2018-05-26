import java.util.LinkedList;

//T : Key type of LinkedList Node
//S : Key type of Tree Node
public class AVLTree<T extends Comparable<T>, S extends Comparable<S>> {
    private TreeNode<T, S> treeRoot;

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

    private void printPreorder(TreeNode<T, S> root) {
        if (root == null)
            return;
        System.out.print(" ");
        System.out.print(root.getKey());
        printPreorder(root.getLeft());
        printPreorder(root.getRight());

    }

    private TreeNode<T, S> lRotate(TreeNode<T,S> root) {
        TreeNode<T, S> newRoot = root.getRight();
        root.setRight(root.getRight().getLeft());
        newRoot.setLeft(root);

        root.updateHeight();
        newRoot.updateHeight();

        return newRoot;
    }

    private TreeNode<T, S> rRotate(TreeNode<T, S> root) {
        TreeNode<T, S> newRoot = root.getLeft();
        root.setLeft(root.getLeft().getRight());
        newRoot.setRight(root);

        root.updateHeight();
        newRoot.updateHeight();

        return newRoot;
    }

    //insert new node(key, loc)
    public void insert(S key, T loc) {
        if (treeRoot == null) {
            treeRoot = new TreeNode<>(key , loc);
        } else {
            treeRoot = insert(treeRoot, key, loc);
        }
    }

    //insert node with key and return new root
    private TreeNode<T, S> insert(TreeNode<T, S> root, S key, T loc) {
        if (root == null)
            return new TreeNode<>(key, loc);
        else if (root.getKey().equals(key)) {
            root.insert(loc);
        } else if (root.getKey().compareTo(key) > 0) {
            root.setLeft(insert(root.getLeft(), key, loc));
            root.updateHeight();
        } else {
            root.setRight(insert(root.getRight(), key, loc));
            root.updateHeight();
        }

        if (root.getHeightDiff() > 1) {
            if (root.getLeft().getHeightDiff() > 0) {
                root = rRotate(root);
            } else {
                root.setLeft(lRotate(root.getLeft()));
                root = rRotate(root);
            }
        } else if (root.getHeightDiff() < -1) {
            if (root.getRight().getHeightDiff() < 0) {
                root = lRotate(root);
            } else {
                root.setRight(rRotate(root.getRight()));
                root = lRotate(root);
            }
        }

        root.updateHeight();
        return root;
    }

    public TreeNode<T, S> find(S key) {
        return find(treeRoot, key);
    }

    private TreeNode<T, S> find(TreeNode<T, S> root, S key) {
        if (root == null)
            return new TreeNode<>(key);
        if (root.getKey().compareTo(key) == 0)
            return root;
        else if (root.getKey().compareTo(key) > 0)
            return find(root.getLeft(), key);
        else
            return find(root.getRight(), key);
    }
}

//T : Key type of LinkedList Node
//S : Key type of Tree Node
class TreeNode<T extends Comparable<T>, S> {
    private LinkedList<T> linkedList;
    private S key;
    private TreeNode<T, S> left;
    private TreeNode<T, S> right;
    private int height;

    public TreeNode(S key) {
        linkedList = new LinkedList<>();
        this.key = key;
        this.height = 1;
    }

    public TreeNode(S key, T loc) {
        this(key);
        insert(loc);
    }

    public TreeNode<T, S> getLeft() {
        return left;
    }

    public TreeNode<T, S> getRight() {
        return right;
    }

    public int getHeight() {
        return height;
    }

    public S getKey() {
        return key;
    }

    public void setLeft(TreeNode<T, S> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T, S> right) {
        this.right = right;
    }

    public LinkedList<T> getList() {
        return linkedList;
    }

    //returns leftHeight - rightHeight
    public int getHeightDiff() {
        int lHeight = (left == null ? 0 : left.getHeight());
        int rHeight = (right == null ? 0 : right.getHeight());
        return lHeight - rHeight;
    }

    public void updateHeight() {
        int lHeight = (left == null ? 0 : left.getHeight());
        int rHeight = (right == null ? 0 : right.getHeight());
        height = 1 + Math.max(lHeight, rHeight);
    }

    public void insert(T loc) {
        int idx = 0;
        for (T curr : linkedList) {
            if (curr.compareTo(loc) < 0)
                idx++;
        }
        linkedList.add(idx, loc);
    }
}

