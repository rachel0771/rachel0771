package edu.neu.cs5004.circularlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CircularListTest {

    //private CircularListTest circularList;
    private Random random = new Random();

    @Test
    void add() {
        int randomValue1 = random.nextInt();
        CircularList<Integer> numbers = new CircularList<>(2);
        numbers.add(randomValue1);
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains(randomValue1));
    }

    @Test
    void contains() {
        int randomValue1 = random.nextInt();
        CircularList<Integer> numbers = new CircularList<>(5);
        numbers.add(randomValue1);
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains(randomValue1));
    }

    @Test
    void size() {
        CircularList<Integer> numbers = new CircularList<>(5);
        numbers.add(11);
        numbers.add(12);
        numbers.add(13);
        numbers.add(14);
        numbers.add(15);
        assertEquals(5, numbers.size());
    }

    @Test
    void remove() {
        CircularList<Integer> numbers = new CircularList<>(5);
        numbers.add(11);
        numbers.add(12);
        numbers.add(13);
        numbers.add(14);
        numbers.add(15);
        numbers.iterator().remove();
        assertEquals(4, numbers.size());
        //assertFalse(numbers.contains(11));
    }

    @Test
    void hasNext() {
        CircularList<Integer> numbers = new CircularList<>(5);
        numbers.add(11);
        numbers.add(12);
        numbers.add(13);
        numbers.add(14);
        numbers.add(15);
        Iterator<Integer> iterator = numbers.iterator();
        assertEquals(5, numbers.size());
        assertTrue(iterator.hasNext());
    }

    @Test
    void iterator2() {
        CircularList<Integer> numbers = new CircularList<>(2);

        Iterator circularIterator = numbers.iterator();
        System.out.println("The original circular list is: " + numbers);
        System.out.println(circularIterator.hasNext());
        if (circularIterator.hasNext()) {
            System.out.println("The first printed is: " + circularIterator.next());
        } else {
            Assertions.assertThrows(NoSuchElementException.class, () -> circularIterator.next());
        }
        while (circularIterator.hasNext()) {
            System.out.println("The next printed is: " + circularIterator.next());
            System.out.println("Circular list after iteration: " + numbers);
        }
    }
}
