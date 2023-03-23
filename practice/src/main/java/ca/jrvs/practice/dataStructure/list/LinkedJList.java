package ca.jrvs.practice.dataStructure.list;

public class LinkedJList<E> implements JList<E>{
    Node head;

    public class Node {
        public E data;
        public Node next;
        Node(E e){
            this.data = e;
            this.next = null;
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
    public Object[] toArray() {
        Object[] val = new Object[size()];
        int i = 0;
        Node node = head;
        while(node.next != null){
            val[i] = node.data;
            node = node.next;
            i++;
        }
        return val;
    }

    @Override
    public int size() {
        int i = 0;
        if(head == null){
            return 0;
        }
        Node node = head;
        while(node.next != null){
            node = node.next;
            i++;
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        if(head == null)
            return true;
        return false;
    }

    @Override
    public int indexOf(Object o) {
        int i = -1;
        Node node = head;
        while(node.next != null){
            if(node.data == o){
                return i;
            }
            i++;
            node = node.next;
        }
        return i;
    }

    @Override
    public boolean contains(Object o) {
        Node node = head;
        while(node.next != null){
            if(node.data == o){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        Node node = head;
        if(index > size() - 1 || index < 0){
            return null;
        }
        if(size() == 0){
            return null;
        }
        for(int i = 0; i < size(); i++){
            if(i == index){
                return node.data;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public E remove(int index) {
        Node node = head;
        if(index > size() - 1 || index < 0){
            return null;
        }
        if(size() == 0){
            return null;
        }
        if(index == 0){
            head = head.next;
            return node.data;
        }
        else {
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            Node removedNode = node.next;
            node = node.next.next;
            return removedNode.data;
        }
    }

    @Override
    public void clear() {
        head = null;
    }
}
