package com.yatsynaekateryna.lessons.lesson11;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;

public class HashTable implements IMap {
    double loadFactor;
    int cap;
    int size;
    Node[] nodes;

    public HashTable() {
        loadFactor = 0.75;
        cap = 16;
        nodes = new Node[cap];
    }

    private static class Node {
        String key;
        int val;
        Node next;
    }

    private int ind(String key) {
        return (key.hashCode() % cap + cap) % cap;
    }

    private boolean equals(String first, String second) {
        return first.equalsIgnoreCase(second);
    }

    private void ensureCapacity() {

        if (cap < loadFactor * size) {
            cap += cap / 2;
            Node[] newArr = new Node[cap];
            for (Node node : nodes) {
                Node curNode = node;
                while (curNode != null) {
                    int ind = ind(curNode.key);
                    Node toAdd = new Node();
                    toAdd.key = curNode.key;
                    toAdd.val = curNode.val;
                    toAdd.next = newArr[ind];
                    newArr[ind] = toAdd;
                    curNode = curNode.next;
                }
            }
            nodes = newArr;
        }
    }

    @Override
    public int get(String key) {

        if (contains(key)) {
            Node curNode = nodes[ind(key)];

            while (curNode != null) {
                if (equals(curNode.key, key)) {
                    return curNode.val;
                }
                curNode = curNode.next;
            }
            throw new RuntimeException();
        }

        return 0;
    }


    @Override
    public boolean contains(String key) {
        Node curNode = nodes[ind(key)];
        while (curNode != null) {
            if (equals(curNode.key, key)) {
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    @Override
    public boolean put(String key, int val) {
        ensureCapacity();
        int ind = ind(key);

        if (contains(key)) {
            Node curNode = nodes[ind];
            while (curNode != null) {
                if (equals(curNode.key, key)) {
                    curNode.val = val;
                    return false;
                }
                curNode = curNode.next;
            }
            throw new RuntimeException();
        } else {
            Node toAdd = new Node();
            toAdd.key = key;
            toAdd.val = val;
            toAdd.next = nodes[ind];
            nodes[ind] = toAdd;
            size++;
            return true;
        }

    }

    @Override
    public boolean remove(String key) {
        if (contains(key)) {
            int ind = ind(key);
            Node node = nodes[ind];
            if (node.next == null) {
                nodes[ind] = null;
                size--;
                return true;
            } else {

                Node prevNode = nodes[ind];
                Node curNode = prevNode.next;
                if (equals(prevNode.key, key)) {
                    nodes[ind] = curNode;
                    size--;
                    return true;
                }

                while (curNode.next != null) {
                    if (equals(curNode.key, key)) {
                        prevNode.next = curNode.next;
                        size--;
                        return true;
                    }
                }

            }
            throw new RuntimeException();
        }


        return false;

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
    public String toString() {
        StringBuilder ans = new StringBuilder();
        //{
        //for nodes
        //    col
        //        [key, value]
        //}
        ans.append("{\n");
        for (Node node : nodes) {

            Node curNode = node;
            while (curNode != null) {

                ans.append(
                        String.format("[key = %s, value = %d]\n",
                                curNode.key, curNode.val)
                );

                curNode = curNode.next;
            }

        }
        ans.append("}");


        return ans.toString();
    }
}
