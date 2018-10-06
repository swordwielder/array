/**
 * Qi Fen Chen
 * PA2
 * 3/28/12
 * 
 * 
 */

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
public class CircularDoubleLinkedList<E> implements Iterable<E>{
    protected CDLLNode<E> head,current,current2;
    protected int size;

    public static class CDLLNode<E>
    {
        protected E data;
        protected CDLLNode<E> next, prev;
        //constructor
        public CDLLNode(CDLLNode<E> previous, E data, CDLLNode<E> next)
        {
            this.data = data;
            this.next = next;
            this.prev = previous;
        }
        //overloaded constructor
        public CDLLNode()
        {
            this.data = null;
            this.next= null;
            this.prev= null;
        }
    }

    protected class MyIterator implements ListIterator<E>
    {
        protected CDLLNode<E> nextNode;
        protected CDLLNode<E> prevNode;
        protected CDLLNode<E> lastNodeReturned;
        protected int index=0;
        //constructor
        protected MyIterator()
        {
            nextNode = head;
            lastNodeReturned = null;
        }
        //add method

        public void add(E e)
        {
            CDLLNode<E> newNode;
            if (size == 0)
            {
                newNode = new CDLLNode(head, e, head);
                head = newNode;
                size++; index=0;
            }
            if (size !=0 && index ==0) 
            {
                newNode = new CDLLNode(head.prev, e, head);
                head.prev.next = newNode;
                head.prev = newNode;
                head = newNode;
                index++; size++;
            }
            if (index != 0)
            {
                newNode = new CDLLNode(nextNode.prev, e, nextNode);
                nextNode.prev.next = newNode;
                nextNode.prev = newNode;
                size++;
            }
            lastNodeReturned = null;
        }
        //method has next that returns a boolean
        public boolean hasNext()
        {
            return (nextNode != null);
        }
        //method has previous that returns a boolean
        public boolean hasPrevious()
        {
            return (nextNode.prev != null);
        }
        //next method
        public E next()
        {
            index++;
            nextNode = nextNode.next;
            lastNodeReturned = nextNode;
            return lastNodeReturned.data;
        }
        //next index method
        public int nextIndex(){
            if (hasNext()){
                return index;
            } else {
                throw new NoSuchElementException();
            }
        }
        //previous index method
        public int previousIndex(){
            if (hasPrevious()) {
                if (index ==0)
                    return (size-1);
                return (index-1);
            } else {
                throw new NoSuchElementException();
            }
        }
        //previous method
        public E previous(){
            index--;
            nextNode = nextNode.prev;
            lastNodeReturned = nextNode;
            return lastNodeReturned.data;
        }
        //remove method for iterator
        public void remove(){
            if (lastNodeReturned != null){
                if (lastNodeReturned == head)
                    removeFromHead();
                else
                {
                    lastNodeReturned.prev.next = lastNodeReturned.next;
                    lastNodeReturned.next.prev = lastNodeReturned.prev;
                    size--;
                }
                lastNodeReturned = null;
                index--;
            } else {
                throw new IllegalStateException();
            }
        }
        //set method
        public void set(E e)
        {
            lastNodeReturned.data = e;
        }

        public String toString()
        {
            String s = "LinkedList (size = " + size + "): head <-> ";
            CDLLNode<E> current = head;
            while (current.next != head) {
                s += current.data + " <-> ";
                current = current.next;
            }
            s += "null";
            return s;
        }
    }
    //clear method
    public void clear()
    {
        head = null;
        size =0;
    }
    //iterator method so iterable can be used
    public ListIterator<E> iterator(){
        return new MyIterator();
    }
    //get method
    public E get(int i)
    {
        return nodeAt(i).data;
    }

    // Changes the data at node i in the list to the specified newData.
    // Big-O: O(n)
    public void set(int i, E newData)
    {
        nodeAt(i).data = newData;
    }

    // Adds a new node to the tail of the list containing the specified newData.
    // Big-O: O(1) if adding to head, O(n) if adding to tail
    public void add(E newData)
    {
        if (size == 0) {            // if the list is empty, add to the head
            addToHead(newData);
        } else {                    // otherwise, add after the tail node of the list
            addAfter(newData, nodeAt(size-1));
        }
    }

    // Removes the node at position i from the list.
    // Big-O: O(1) if removing from head, O(n) if removing from any other position
    public void remove(int i)
    {
        if (i == 0) {               
            removeFromHead();
        } else {
            removeAfter(nodeAt(i-1));
        }
    }

    // Adds a new node containing the specified newData to the head of the list.
    protected void addToHead(E newData)
    {
        // make a new node containing newData... the new node will point
        //  to the head node
        CDLLNode<E> newNode = new CDLLNode<E>(head, newData, head);

        // now set the head to the new node
        head = newNode;
        head.next = head;
        head.prev = head;
        size++;
    }

    // Removes the head node from the list.  Does nothing if called on an empty list.
    protected void removeFromHead()
    {
        if (head != null) {         // if the list is not empty...
            head = head.next;       // make "head" point to the node after "head"
            head.prev.next = head.next;
            head.next.prev = head.prev;
            size--;
        }
    }

    // Adds a new node containing the specified newData, immediately after the "where" node.
    protected void addAfter(E newData, CDLLNode<E> where)
    {
        // make a new node containing newData... the new node will point to
        //  the node after "where"
        CDLLNode<E> newNode = new CDLLNode<E>(where, newData, where.next);

        // now set the next of "where" to the new node
        where.next = newNode;
        newNode.prev = where;
        newNode.next = head;
        head.prev = newNode;
        size++;
    }

    // Removes the node immediately after the "where" node.
    protected void removeAfter(CDLLNode<E> where)
    {
        // set the next of "where" to point to the node two places ahead of "where"
        where.next = where.next.next;
        where.next.prev = where;
        size--;
    }

    // Returns a reference to node i of the list (where node 0 is the head node).
    protected CDLLNode<E> nodeAt(int i)
    {
        if (i >= 0 && i < size) {
            // traverse the list starting from the head node
            CDLLNode<E> current = head;
            for ( ; i > 0; i--) {
                current = current.next;
            }
            return current;
        } else {
            throw new NoSuchElementException();
        }
    }

    //test method
    public static void main(String[] args)
    {
        CircularDoubleLinkedList<Integer> list1 = new CircularDoubleLinkedList<Integer>();
        ListIterator<Integer> iter;

        iter = list1.iterator();

        list1.add(1);
        iter = list1.iterator();
        System.out.println(list1);
        System.out.println(" iter.next() returned " + iter.next());
        System.out.println("  calling iter.remove()");
        iter.remove();
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with two nodes - first node) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 2; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            if (i == 0) {
                System.out.println("  calling iter.remove()");
                iter.remove();
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with two nodes - second node) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 2; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            if (i == 1) {
                System.out.println("  calling iter.remove()");
                iter.remove();
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with two nodes - both nodes) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 2; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            System.out.println("  calling iter.remove()");
            iter.remove();
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with three nodes - first node) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 3; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            if (i == 0) {
                System.out.println("  calling iter.remove()");
                iter.remove();
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with three nodes - second node) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 3; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            if (i == 1) {
                System.out.println("  calling iter.remove()");
                iter.remove();
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with three nodes - third node) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 3; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            if (i == 2) {
                System.out.println("  calling iter.remove()");
                iter.remove();
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with three nodes - first two nodes) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 3; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            if (i < 2) {
                System.out.println("  calling iter.remove()");
                iter.remove();
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with three nodes - last two nodes) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 3; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            if (i > 0) {
                System.out.println("  calling iter.remove()");
                iter.remove();
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with three nodes - first and last nodes) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 3; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            if (i == 0 || i == 2) {
                System.out.println("  calling iter.remove()");
                iter.remove();
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing from list with three nodes - all nodes) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        iter = list1.iterator();
        System.out.println(list1);
        for (int i = 0; i < 3; i++) {
            System.out.println(" iter.next() returned " + iter.next());
            System.out.println("  calling iter.remove()");
            iter.remove();
        }
        System.out.println(list1);

        for (int k = 0; k < 5; k++) {
            System.out.println("\n*** TESTING (removing from list with ten nodes - scattered throughout) ***");
            list1 = new CircularDoubleLinkedList<Integer>();
            for (int i = 1; i <= 10; i++)
                list1.add(i);
            iter = list1.iterator();
            System.out.println(list1);
            for (int i = 0; i < 10; i++) {
                System.out.println(" iter.next() returned " + iter.next());
                if (Math.random() < 0.5) {
                    System.out.println("  calling iter.remove()");
                    iter.remove();
                }
            }
            System.out.println(list1);
        }

        System.out.println("\n*** TESTING (removing before calling next() at all) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        iter = list1.iterator();
        System.out.println(list1);
        try {
            System.out.println("  calling iter.remove()");
            iter.remove();
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException was thrown!");
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing more than once after a call to next()) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        iter = list1.iterator();
        System.out.println(list1);
        System.out.println(" iter.next() returned " + iter.next());
        for (int i = 0; i < 2; i++) {
            try {
                System.out.println("  calling iter.remove()");
                iter.remove();
            } catch (IllegalStateException e) {
                System.out.println("IllegalStateException was thrown!");
            }
        }
        System.out.println(list1);

        System.out.println("\n*** TESTING (removing more than once after a call to next()) ***");
        list1 = new CircularDoubleLinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        iter = list1.iterator();
        System.out.println(list1);
        System.out.println(" iter.next() returned " + iter.next());
        System.out.println(" iter.next() returned " + iter.next());
        System.out.println(" iter.next() returned " + iter.next());
        System.out.println(" iter.next() returned " + iter.next());
        for (int i = 0; i < 2; i++) {
            try {
                System.out.println("  calling iter.remove()");
                iter.remove();
            } catch (IllegalStateException e) {
                System.out.println("IllegalStateException was thrown!");
            }
        }
        System.out.println(list1);
    }
}
