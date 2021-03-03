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
            add_in_tail(new Node(value));
            return;
        }
        Node<T> node = head;
        if (_ascending == true) {
            //возростающая
            add_in_asc(new Node<>(value));
        } else {
            //убывающая
            add_in_desc(new Node<>(value));
        }
        return;
    }

    void add_in_asc(Node<T> item) {
        Node<T> node = head;
        //System.out.println(item.value + "  " + node.value + "  " + compare(item.value, node.value));
        while (node != null && compare(item.value, node.value) >= 0) {
            node = node.next;
        }
        if (node == null) {
            add_in_tail(item);
            return;
        }

        item.prev = node.prev;
        if (head != node) {
            node.prev.next = item;
        }else{
            head = item;
        }
        node.prev = item;
        item.next = node;

    }

    void add_in_desc(Node<T> item) {
        Node<T> node = head;

        while (node != null && compare(item.value, node.value) <= 0) {
            node = node.next;
        }

        if (node == null) {
            add_in_tail(item);
            return;
        }
        item.prev = node.prev;

        if (head != node) {
            node.prev.next = item;
        }else{
            head = item;
        }
        node.prev = item;
        item.next = node;
    }

    void add_in_tail(Node item) {
        if (head == null) {
            head = item;
            item.prev = null;
            item.next = null;
        } else {
            tail.next = item;
            item.prev = tail;
        }
        tail = item;
    }

    public Node<T> find(T val) {

        Node<T> node = head;
        while (node != null && compare(node.value, val) != 0) {
            node = node.next;
        }

        return node;
    }

    public void delete(T val) {
        Node node = find(val);
        if (node != null) {
            if (node.prev == null) {
                head = node.next;
                node.next.prev = null;
                return;
            }
            if (node.next == null) {
                node.prev.next = null;
                tail = node.prev;
                node.prev = null;
                return;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }
    }

    public void clear(boolean asc) {
        _ascending = asc;
        this.head=null;
        this.tail=null;
    }

    public int count() {
        Node node = head;
        int count = 0;
        while (node != null) {
            node = node.next;
            count++;
        }
        return count; // здесь будет ваш код подсчёта количества элементов в списке
    }

    ArrayList<Node<T>> getAll() // выдать все элементы упорядоченного
    // списка в виде стандартного списка
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while(node != null)
        {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}