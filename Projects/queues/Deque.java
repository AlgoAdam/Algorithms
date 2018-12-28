/* *****************************************************************************
 *  Name: Adam Butterworth
 *  Date: 28th December 2018
 *  Description: Deque - generalization of a stack and a queue that
 *  supports adding and removing items from either the front or the
 *  back of the data structure. Use linked-list.
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;
    private Node before;

    private class Node {
        Item item;
        Node next;
        Node before;
    }

    public Deque() {
        // Construct first and last Node
        first = null;
        last = null;
        // construct an empty deque
        size = 0;
    }

    public boolean isEmpty() {
        // is the deque empty?
        return size == 0;
    }

    public int size() {
        // return the number of items on the deque
        return size;
    }


    public void addFirst(Item item) {
        // add the item to the front
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Invalid entry!");
        }

        Node oldfirst = (!isEmpty()) ? first : null;

        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) {
            oldfirst.before = first;
        }
        // Increment size
        size++;
        if (size == 1) {
            last = first;
        }
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Invalid entry!");
        }
        Node oldlast = (!isEmpty()) ? last : null;
        last = new Node();
        last.item = item;
        if (oldlast != null) {
            oldlast.next = last;
            last.before = oldlast;
        }
        // Increment size
        size++;
        if (size == 1) {
            first = last;
        }
    }

    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty!");
        }

        // Decrement size
        size--;
        // Remove first by setting to null
        Item oldfirst = first.item;
        first = first.next;
        // Prevent loitering
        if (first != null) {
            first.before = null;
        }

        return oldfirst;

    }

    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty!");
        }
        // Decrement size
        size--;
        // Remove last item by setting to null

        Item oldlast = last.item;
        last = last.before;
        if (last != null) {
            last.next = null;
        }
        return oldlast;
    }

    @Override
    public Iterator<Item> iterator() {
        Iterator<Item> it = new Iterator<Item>() {
            private int currentIndex = 0;
            private Node firstN = first;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("End of deque!");
                }
                Item i = firstN.item;
                firstN = firstN.next;
                currentIndex++;
                return i;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        // return an iterator over items in order from front to end
        return it;
    }

    public static void main(String[] args) {
        // unit testing (optional)
        Deque<String> d = new Deque<>();
        System.out.println(d.isEmpty());
        d.addFirst("hello");
        d.addLast("there");
        d.addLast("John");
        d.addFirst("first");
        // System.out.println(d.isEmpty());
        d.removeFirst();
        // System.out.println(d.removeLast());
        for (String a : d) {
            System.out.println(a);
        }
    }


}

