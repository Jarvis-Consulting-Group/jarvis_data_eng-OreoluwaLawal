package ca.jrvs.practice.dataStructure.stackQueue;

import ca.jrvs.practice.dataStructure.list.LinkedJList;

public class LinkedJListJDeque<E> implements JDeque<E> {

    Node head;
    public class Node{
        public E data;
        public Node next;
        public Node(E e){
            this.data = e;
        }
    }
    @Override
    public boolean add(E e) {
        Node newNode = new Node(e);
        if(head == null ){
            head = newNode;
        }else{
            Node node = head;
            while (node.next != null){
                node = node.next;
            }
            node.next = newNode;
        }
        return true;
    }

    @Override
    public E remove() {
        Node prev = null;
        Node node = head;
        if(head == null){
            return null;
        }
        if(head.next == null){
            Node re = head;
            head = null;
            return re.data;
        }
        while(node.next != null){
            prev = node;
            node = node.next;
        }
        if(prev != null) {
            prev.next = null;
        }
     
       return node.data;
    }

    @Override
    public E pop() {
        if(head == null){
            return null;
        }
        Node node = head;
        head = head.next;
        return node.data;
    }

    @Override
    public void push(E e) {
        Node newNode = new Node(e);
        newNode.next = head;
        head = newNode;
    }

    @Override
    public E peek() {
        if(head == null){
            return null;
        }
        return head.data;
    }
}