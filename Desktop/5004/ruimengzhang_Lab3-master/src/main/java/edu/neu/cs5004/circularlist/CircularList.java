package edu.neu.cs5004.circularlist;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class CircularList<T> implements Iterable<T> {
    private class CircularIterator implements Iterator<T> {
        private int current;

        private CircularIterator(){
            current = start;
        }

        @Override
        public boolean hasNext() {
            if(start <= current && current < end){
                return true;
            } else if (start > end) {
                if(current <= end || current < array.size()){
                    return true;
                }else{
                    return false;
                }
            }
            return false;
        }

        @Override
        public T next() {
            if (current >= maxSize) {
                throw new NoSuchElementException("No such element!");
            } else if (hasNext()) {
                current += 1;
                return array.get(current - 1);
            } else {
                throw new NoSuchElementException("No such element!");
            }
        }


        @Override
        public void remove() {
            size = size - 1;
            if (canBeRemoved()) {
                if (start < end) {
                    array.remove(current);
                    end--;
                } else if (current < end && end < start) {
                    array.remove(current);
                    end--;
                    start--;
                } else if (current > end && current < start) {
                    array.remove(current);
                    start--;
                } else {
                    throw new NoSuchElementException("No such element!");
                }
            }
        }
        
        public boolean canBeRemoved(){
            if(current > maxSize || array.get(current) == null){
                return false;
            }else{
                return true;
            }
        }

        //Extra credit
        @Override
        public void forEachRemaining(Consumer<? super T> action) {}
    }

    private int start;
    private int end;
    private int maxSize;
    private int size;
    private ArrayList<T> array;
    //public CircularIterator sentinel;
    /**Constructor
     * Create an empty list **/
    public CircularList(int maxSize) {
        start = 0;
        end = -1;
        this.maxSize = maxSize;
        this.array = new ArrayList<>();
        this.size = 0;
    }

    public void add(T item) {
        size += 1;
        // 1.
        if (array.size() < maxSize){
            array.add(item);
            end++;
        }
        // 2.
        else if(array.size() == maxSize){
            array.set(start, item);
            if(start == array.size() - 1){
                start = 0;
            }
            else{
                start ++;
            }
            if(end == array.size() - 1){
                end = 0;
            }
            else{
                end ++;
            }
        }
    }


    public boolean contains(T item) {
        for (int i = 0; i < maxSize; ++i) {
            if (array.get(i).equals(item)) {
                return true;
            }
        }
        return false;
    }


    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new CircularIterator();
    }

    //Extra credit
    @Override
    public void forEach(Consumer<? super T> action) {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
