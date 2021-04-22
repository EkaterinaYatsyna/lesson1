package com.yatsynaekateryna.lessons.lesson10;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinaryTreeRecursive implements IStringSet, ISortedStringSet,Iterable<String> {

    private static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;
    }

    TreeNode root;
    int size;

    public BinaryTreeRecursive() {
    }

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

    private String from;
    private String upTo;
    private BinaryTreeRecursive primaryTree;

    private BinaryTreeRecursive(BinaryTreeRecursive primaryTree, String from, String upTo) {
        this.primaryTree = primaryTree;
        this.from = from;
        this.upTo = upTo;
    }

    @Override
    public ISortedStringSet subset(String from, String upTo) {
        BinaryTreeRecursive cur = new BinaryTreeRecursive(this, from, upTo);
        subsetRecursive(cur.primaryTree.root, cur);
        return cur;
    }

    private void subsetRecursive(TreeNode cur, BinaryTreeRecursive tree) {
        if (cur != null) {

            int compareFrom = compare(tree.from, cur.val);
            int compareUpTo = compare(tree.upTo, cur.val);

            if (compareFrom <= 0 && compareUpTo > 0) {
                tree.add(cur.val);
            }

            subsetRecursive(cur.left, tree);
            subsetRecursive(cur.right, tree);

        }
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
        return root.right == null ? root.val : last(root.right);
    }

    private String last(TreeNode node) {

        return node.right == null ? node.val : first(node.right);
    }

    public int maxDepth() {
        return maxDepthNode(root);
    }

    private int maxDepthNode(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        int left = (maxDepthNode(cur.left));
        int right = (maxDepthNode(cur.right));

        return 1 + Math.max(left, right);

    }

    @Override
    public Iterator<String> iterator() {
        return new BinaryTreeRecursiveIterator(root);
    }

    private static class BinaryTreeRecursiveIterator implements Iterator<String> {

        String next;
        Queue<String> queue;

        public BinaryTreeRecursiveIterator(TreeNode root) {
            queue = new LinkedList<>();
            fillStack(root);
            prepareNext();

        }

        private void fillStack(TreeNode node){
            if (node != null) {
                fillStack(node.left);
                queue.add(node.val);
                fillStack(node.right);
            }
        }

        private void prepareNext() {

            if(!queue.isEmpty()){
                next = queue.remove();
            }else {
                next = null;
            }
        }


        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String next() {
            if (this.hasNext()) {
                String val = next;
                prepareNext();
                return val;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
