package org.example.dfs.이진트리순회;

class Node{
    int data;
    Node lt, rt;

    public Node(int data) {
        this.data = data;
        lt=rt=null;
    }
}
public class BinaryTreeCircuit {
    Node root;
    public void DFS(Node root) {
        if (root == null) {
            return;
        } else {
            //전위 순회
            System.out.println(root.data + " ");
            DFS(root.lt);

            //중위 순회
//            System.out.println(root.data + " ");
            DFS(root.rt);

            //후위 순회
//            System.out.println(root.data + " ");
        }

    }

    public static void main(String[] args) {
        BinaryTreeCircuit tree = new BinaryTreeCircuit();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.root.rt.lt = new Node(6);
        tree.root.rt.rt = new Node(7);
        tree.DFS(tree.root);
    }
}
