package September4th.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class First {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*请完成下面这个函数，实现题目要求的功能
     ******************************开始写代码******************************/
    static ListNode partition(ListNode head,int m) {
        ListNode headHigh = null;
        ListNode headLow = null;
        ListNode tailHigh = null;
        ListNode tailLow = null;
        for (; head != null; head = head.next) {
            if (head.val > m) {
                if (headHigh == null) {
                    tailHigh = head;
                    headHigh = tailHigh;
                } else {
                    tailHigh.next = head;
                    tailHigh = head;
                }
            } else {
                if (headLow == null) {
                    tailLow = head;
                    headLow = tailLow;
                } else {
                    tailLow.next = head;
                    tailLow = head;
                }
            }
        }
        if (headLow == null) {
            return headHigh;
        }
        tailLow.next = headHigh; // 小链表接大连表
        tailHigh.next = null; // 大链表 结尾为空
        return headLow;
    }

    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ListNode head=null;
        ListNode node=null;
        int m=in.nextInt();
        while(in.hasNextInt()){
            int v=in.nextInt();
            if(head==null){
                node=new ListNode(v);
                head=node;
            }else{
                node.next=new ListNode(v);
                node=node.next;
            }
        }
        head= partition(head,m);
        if(head!=null){
            System.out.print(head.val);
            head=head.next;
            while(head!=null){
                System.out.print(",");
                System.out.print(head.val);
                head=head.next;
            }
        }
        System.out.println();
    }
}

