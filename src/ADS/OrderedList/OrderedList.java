package ADS.OrderedList;
//ordered list without explicit dummy cell. In the task strict constructor declaration

import java.util.*;


class Node<T> {
    public T value;
    public Node<T> next, prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }

    public Node() {

    }
}

public class OrderedList<T> {
    public Node<T> head, tail;
    private boolean _ascending;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2) {
        if ((int) v1 < (int) v2) {
            return -1;
        }
        if ((int) v1 == (int) v2) {
            return 0;
        } else {
            return 1;
        }
    }

    public void add(T value) {


        if (head == null) {
            addInTail(new Node(value));
            return;
        }
        Node<T> node = head.next;
        if (_ascending == true) {
            //возростающая

            while (node.next != tail && compare(value, node.value) >= 0) {
                node = node.next;
            }
            if (node.next == tail && compare(value, node.value) >= 0) {
                addInTail(new Node(value));

            } else {
                Node newNode = new Node(value);
                newNode.next = node;
                newNode.prev = node.prev;
                newNode.prev.next = newNode;
                node.prev = newNode;
            }
        } else {
            //убывающая
            while (node.next != tail && compare(value, node.value) <= 0) {
                node = node.next;
            }
            if (node.next == tail && compare(value, node.value) <= 0) {
                addInTail(new Node(value));
            } else {
                Node newNode = new Node(value);
                newNode.next = node;
                newNode.prev = node.prev;
                newNode.prev.next = newNode;
                node.prev = newNode;
            }
        }
        // автоматическая вставка value
        // в нужную позицию
    }

    public void addInTail(Node node) {
        if (head == null) {
            head = new Node<>();
            tail = new Node<>();
            head.next=tail;
            tail.prev=head;
        }
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        node.prev.next = node;

    }

    public Node<T> find(T val) {
        if (this.head==null){
            return null;
        }
        Node<T> node = this.head.next;
        if (_ascending == true) {
            while (compare(val, node.value) > 0 && node.next != tail) {
                node = node.next;
            }

        } else {
            while (compare(val, node.value) < 0 && node.next != tail) {
                node = node.next;
            }
        }
        if (node.value == val) {
            return node;
        }
        return null;    }

    public void delete(T val) {
        Node node=find(val);
        if (node!=null){

            node.prev.next=node.next;
            node.next.prev=node.prev;
        }
    }

    public void clear(boolean asc) {
        _ascending = asc;
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int count() {
        Node node=head;
        int count=0;
        while (node!=null && node.next!=tail){
            node=node.next;
            count++;
        }
        return count; // здесь будет ваш код подсчёта количества элементов в списке
    }

    ArrayList<Node<T>> getAll() // выдать все элементы упорядоченного
    // списка в виде стандартного списка
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        if (node==null){
            return r;
        }
        node=node.next;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}