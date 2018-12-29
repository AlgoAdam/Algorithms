/* *****************************************************************************
 *  Name: Adam Butterworth
 *  Date: 28th December 2018
 *  Description: Randomized Queue ADT
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] arr;

    public RandomizedQueue() {
        // construct an empty randomized queue
        arr = (Item[]) new Object[2];
        size = 0;

    }

    public boolean isEmpty() {
        // is the randomized queue empty?
        return size == 0;
    }

    public int size() {
        // return the number of items on the randomized queue
        return size;
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null) {
            throw new IllegalArgumentException("Item must be non-null!");
        }
        // Check is size reaching max capacity, if so resize
        if (size == arr.length) {
            arr = resize(arr.length * 2);
        }
        // Add new item to end of array and update size
        arr[size++] = item;
    }

    public Item dequeue() {
        // remove and return a random item
        validate();
        // Find random number between 0 and size
        int rand = StdRandom.uniform(size);
        Item i = arr[rand];
        // Replace index with item in last position if size>1
        if (size > 1) {
            arr[rand] = arr[size - 1];
        }
        // Set final position to null - prevent loitering
        arr[size - 1] = null;
        // Decrement size
        size--;
        // When array one-quarter full, halve array
        if (arr.length > 0 && size == arr.length / 4) {
            arr = resize(arr.length / 2);
        }
        // Return item
        return i;
    }

    public Item sample() {
        // return a random item (but do not remove it)
        validate();
        // Find random number between 0 and size
        int rand = StdRandom.uniform(size);
        Item i = arr[rand];
        return i;
    }

    public Iterator<Item> iterator() {
        // Initialise array with numbers from 0 to size-1
        int[] randomArr = new int[size];
        for (int j = 0; j < randomArr.length; j++) {
            randomArr[j] = j;
        }
        // Shuffle numbers to randomise order
        StdRandom.shuffle(randomArr);
        // return an independent iterator over items in random order
        Iterator<Item> it = new Iterator<Item>() {
            int curr = 0;

            @Override
            public boolean hasNext() {
                return (size > curr);
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("End of queue!");
                }
                return arr[randomArr[curr++]];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove is unsupported!");
            }
        };
        // Return iterator
        return it;
    }


    private void validate() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
    }

    private Item[] resize(int len) {
        Item[] arr1 = (Item[]) new Object[len];
        for (int i = 0; i < size; i++) {
            arr1[i] = arr[i];
        }
        return arr1;
    }

    public static void main(String[] args) {
        // unit testing (optional)
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("First");
        rq.enqueue("Second");
        rq.enqueue("Third");
        rq.enqueue("Fourth");
        rq.enqueue("Fifth");
        rq.enqueue("Sixth");
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.enqueue("First");
        rq.enqueue("Second");
        rq.enqueue("Third");
        rq.enqueue("Fourth");
        rq.enqueue("Fifth");
        rq.enqueue("Sixth");
        for (String a : rq) {
            System.out.println(a);
        }

    }
}


