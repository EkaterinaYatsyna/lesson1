package com.yatsynaekateryna.lessons.lesson10;

import java.util.*;

public class BinaryTree implements IStringSet, ISortedStringSet, Iterable<String> {

    private static class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;
    }

    TreeNode root;
    int size;

    public BinaryTree() {
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

    @Override
    public boolean contains(String val) {
        if (isEmpty()) {
            return false;
        } else {
            TreeNode cur = root;
            while (true) {
                int compare = compare(val, cur.val);
                if (compare == 0) {
                    return true;
                } else if (compare < 0) {
                    if (cur.left == null) {
                        return false;
                    } else {
                        cur = cur.left;
                    }
                } else {
                    if (cur.right == null) {
                        return false;
                    } else {
                        cur = cur.right;
                    }
                }
            }

        }

    }

    @Override
    public boolean add(String val) {
        TreeNode toAdd = new TreeNode();
        toAdd.val = val;

        if (isEmpty()) {
            root = toAdd;
            size++;
            return true;
        } else {
            TreeNode cur = root;
            while (true) {
                int compare = compare(toAdd.val, cur.val);
                if (compare == 0) {
                    return false;
                } else if (compare < 0) {
                    if (cur.left == null) {
                        cur.left = toAdd;
                        size++;
                        return true;
                    } else {
                        cur = cur.left;
                    }
                } else {
                    if (cur.right == null) {
                        cur.right = toAdd;
                        size++;
                        return true;
                    } else {
                        cur = cur.right;
                    }
                }
            }
        }
    }

    @Override
    public boolean remove(String val) {

        TreeNode cur = root;
        TreeNode curParent = root;
        int compare;

        //находим узел(cur) и его родителя(curParent)
        while (!Objects.equals(cur.val, val)) {
            curParent = cur;
            compare = compare(val, cur.val);
            if (compare < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            if (cur == null) {     //узел не найден
                return false;
            }
        }

        //если в узла нет потомков
        if (cur.left == null && cur.right == null) {
            if (cur == root) {
                root = null;
            } else if (curParent.left == cur) {
                curParent.left = null;
            } else {
                curParent.right = null;
            }
        }

        //если в узла есть только правый потомок, левого нет
        else if (cur.left == null) {
            if (cur == root) {
                root = root.right;
            } else if (curParent.left == cur) {
                curParent.left = cur.right;
            } else {
                curParent.right = cur.right;
            }
        }

        //если в узла есть только левый потомок, правого нет
        else if (cur.right == null) {
            if (cur == root) {
                root = root.left;
            } else if (curParent.left == cur) {
                curParent.left = cur.left;
            } else {
                curParent.right = cur.left;
            }
        }

        //и самое сложное. если есть два потомка в узле
        else {
            String first = first(cur.right);
            //Запишем в удаляемый узел самое левое значение
            if (cur == root) {
                root.val = first;
            } else if (curParent.left == cur) {
                curParent.left.val = first;
            } else {
                curParent.right.val = first;
            }//записали

            //нужно удалить самый нижний левый узел
            if (Objects.equals(cur.right.val, first)) {
                if (cur.right.right == null) { //если у самого левого узла нет правого потомка
                    cur.right = null;
                } else { //если у самого левого узла есть правый потомок
                    cur.right = cur.right.right;
                }
                size--;
                return true;
            }
            cur = cur.right;
            while (true) {
                compare = compare(first, cur.left.val);
                if (compare == 0) {
                    if (cur.left.right == null) { //если у самого левого узла нет правого потомка
                        cur.left = null;
                    } else { //если у самого левого узла есть правый потомок
                        cur.left = cur.left.right;
                    }
                    size--;
                    return true;
                } else {
                    cur = cur.left;
                }
            }
        }

        size--;
        return true;
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
    private BinaryTree primaryTree;

    private BinaryTree(BinaryTree primaryTree, String from, String upTo) {
        this.primaryTree = primaryTree;
        this.from = from;
        this.upTo = upTo;
    }

    @Override
    public ISortedStringSet subset(String from, String upTo) {
        BinaryTree cur = new BinaryTree(this, from, upTo);
        subset(cur.primaryTree.root, cur);
        return cur;
    }

    private void subset(TreeNode cur, BinaryTree tree) {

        if (cur != null) {

            int compareFrom = compare(tree.from, cur.val);
            int compareUpTo = compare(tree.upTo, cur.val);

            if (compareFrom <= 0 && compareUpTo > 0) {
                tree.add(cur.val);
            }

            subset(cur.left, tree);
            subset(cur.right, tree);

        }
    }

    @Override
    public String leftmost() {
        return first(root);
    }

    private String first(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    @Override
    public String rightmost() {
        return last(root);
    }

    private String last(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
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
        return new BinaryTreeIterator(root);
    }

    private static class BinaryTreeIterator implements Iterator<String> {

        TreeNode root;
        TreeNode parent;
        TreeNode next;


        public BinaryTreeIterator(TreeNode root) {
            this.root = root;
            prepareNext();
        }

        private int compare(String first, String second) {
            return String.CASE_INSENSITIVE_ORDER.compare(first, second);
        }

        private void prepareNext() {
            if (next == null) {   //нашли самый левый
                next = root;
                while (next.left != null) {
                    next = next.left;
                }
                return;
            }

            if (next.right != null) { //есть правый потомок
                next = next.right;
                while (next.left != null) {
                    next = next.left;
                }
            } else {
                TreeNode cur = root;
                while (true) {
                    int compare = compare(next.val, cur.val);
                    if (compare == 0) {
                        next = null;
                        break;
                    } else if (compare < 0) {
                        if (cur.left == next) {
                            next = cur;
                            break;
                        } else {
                            cur = cur.left;
                        }
                    } else {
                        if (cur.right == next) {
                            next = cur;
                            cur = root;
                        } else {
                            cur = cur.right;
                        }
                    }
                }
            }
        }


        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String next() {
            if (this.hasNext()) {
                String val = next.val;
                prepareNext();
                return val;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}

