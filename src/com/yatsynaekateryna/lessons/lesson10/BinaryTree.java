package com.yatsynaekateryna.lessons.lesson10;

public class BinaryTree implements IStringSet, ISortedStringSet {

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
        while (cur.val != val) {
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
            if (cur == root) {
                root.val = first;
            } else if (curParent.left == cur) {
                curParent.left.val = first;
            } else {
                curParent.right.val = first;
            }

            if (cur.right.val == first) {
                cur.right = null;
                size--;
                return true;
            }
            cur = cur.right;
            while (true) {
                compare = compare(first, cur.left.val);
                if (compare == 0) {
                    cur.left = null;
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

    @Override
    public ISortedStringSet subset(String from, String upTo) {
        //???
        return this;
    }

    @Override
    public String leftmost() {
        TreeNode cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.val;
    }

    private String first(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    @Override
    public String rightmost() {
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.val;
    }
}

