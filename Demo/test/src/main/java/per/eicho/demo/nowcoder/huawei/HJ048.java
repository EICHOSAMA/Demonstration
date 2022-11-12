package per.eicho.demo.nowcoder.huawei;

import java.util.Arrays;
import java.util.Scanner;

public final class HJ048 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = -1;
        int[] arguments = null;
        int p = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            if (n != -1) {
                arguments[p++] = in.nextInt();
                if (p == n * 2) {
                    processInput(n, arguments[0], Arrays.copyOfRange(arguments, 1, p - 1), arguments[p - 1]);

                    arguments = null;
                    n = -1;
                    p = 0;
                }
            } else {
                n = in.nextInt();
                arguments = new int[n * 2];
            }
        }

        in.close();
    }

    private static void processInput(int n, int headNodeVal, int[] ops, int delNodeVal) {
        // 0 <= node val <= 10000 
        final Node vituralHead = new Node(-1);
        vituralHead.insert(headNodeVal);

        for (int i = 0, p = 0, bound = n - 1; i < bound; i++) {
            final int insertVal = ops[p++];
            final int targetVal = ops[p++];
            vituralHead.find(targetVal).insert(insertVal);
        }

        vituralHead.delete(delNodeVal);

        if (vituralHead.next == null) {
            System.out.println();
        } else {
            vituralHead.next.print();
        }
    }

    private static final class Node {
        final int val;
        Node next;
        
        Node(int val) { this.val = val; }

        Node find(int val) {
            if (this.val == val) return this;
            if (this.next == null) return null;
            return this.next.find(val);
        }

        void delete(int val) {
            if (this.next == null) return;
            if (this.next.val != val) {
                this.next.delete(val);
            }  else {
                final Node node2Delete = this.next;
                this.next = node2Delete.next;
                node2Delete.next = null;
            }
        }

        void insert(int val) {
            Node node = new Node(val);
            node.next = this.next;
            this.next = node;
        }

        void print() {
            System.out.print(this.val);
            if (this.next == null) {
                System.out.println();
            } else {
                System.out.print(' ');
                this.next.print();
            }
        }
    }
}
