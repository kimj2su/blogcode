package org.example.dfs.부분집합구하기;

public class SubSet {
    static int n;
    static int[] ch;
    public void DFS(int L) {
        if (L == n + 1) {
            String tmp = "";
            for (int i = 1; i <= n; i++) {
                if (ch[i] == 1) tmp += (i + "");
            }
            if (tmp.length() > 0) System.out.println(tmp);
        } else {
            ch[L] = 1;
            DFS(L + 1);
            ch[L] = 0;
            DFS(L + 1);
        }
    }
    public static void main(String[] args) {
        SubSet subSet = new SubSet();
        n = 3;
        ch = new int[n+1];
        subSet.DFS(1);
    }
}
