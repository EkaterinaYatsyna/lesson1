package com.yatsynaekateryna.lessons.lesson10;

public class BinaryTreeRecursive implements IStringSet, ISortedStringSet {

    private static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;
    }

    TreeNode root;
    int size;

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        toString(root, res);
        return res.toString();
    }

    private void toString(TreeNode node, StringBuilder output) {
        if (node != null) {
            toString(node.left, output);
            output.append(node.val).append(' ');
            toString(node.right, output);
        }
    }

    private int compare(String first, String second) {
        return String.CASE_INSENSITIVE_ORDER.compare(first, second);
    }

    private boolean containsRecursive(TreeNode current, String value) {
        if (current == null) {
            return false;
        }
        int compare = compare(value, current.val);
        if (compare == 0) {
            return true;
        }
        return compare < 0
                ? containsRecursive(current.left, value)
                : containsRecursive(current.right, value);
    }

    @Override
    public boolean contains(String val) {
        return containsRecursive(root, val);
    }

    private TreeNode addRecursive(TreeNode current, String value) {
        if (current == null) {
            TreeNode toAdd = new TreeNode();
            toAdd.val = value;
            size++;
            return toAdd;
        }
        int compare = compare(value, current.val);
        if (compare < 0) {
            current.left = addRecursive(current.left, value);
        } else {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    @Override
    public boolean add(String val) {
        if (contains(val)) {
            return false;
        } else {
            root = addRecursive(root, val);
            return true;
        }
    }

    private TreeNode removeRecursive(TreeNode current, String value) {

        int compare = compare(value, current.val);

        if (compare == 0) {
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                String first = first(current.right);
                current.val = first;
                current.right = removeRecursive(current.right, first);
                return current;
            }

        } else if (compare < 0) {
            current.left = removeRecursive(current.left, value);
            return current;
        } else {
            current.right = removeRecursive(current.right, value);
            return current;
        }
    }

    @Override
    public boolean remove(String val) {
        if (!contains(val)) {
            return false;
        } else {
            root = removeRecursive(root, val);
            size--;
            return true;
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public ISortedStringSet subset(String from, String upTo) {
        int compare = compare(from, upTo);
        if (compare == 0 || compare < 0) {
            System.out.println("error");
            return null;
        }
        BinaryTreeRecursive cur = new BinaryTreeRecursive();
        cur.root.val = subsetRecursive(root, from, upTo).val;

        return cur;
    }

    private TreeNode subsetRecursive(TreeNode current, String from, String upTo) {
        int compareUpTo = compare(upTo, current.val);
        int compareFrom = compare(from, current.val);
        if (compareFrom < 0 && compareUpTo > 0) {
            return current;
        } else if (compareUpTo <= 0) {

        } else {

        }
        return current;

    }

    @Override
    public String leftmost() {
        return root.left == null ? root.val : first(root.left);
    }

    private String first(TreeNode node) {

        return node.left == null ? node.val : first(node.left);
    }

    @Override
    public String rightmost() {
        return root.right == null ? root.val : first(root.right);
    }

    private String last(TreeNode node) {

        return node.right == null ? node.val : first(node.right);
    }

}
