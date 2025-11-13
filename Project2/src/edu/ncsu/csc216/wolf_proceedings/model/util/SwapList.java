package edu.ncsu.csc216.wolf_proceedings.model.util;

import java.util.Iterator;

/**
 * A list that lets you rearrange elements by swapping them around.
 * Uses an array underneath, which makes random access fast.
 * Unlike SortedList, this one allows duplicates.
 * 
 * @param <E> type of elements to store
 */
public class SwapList<E> implements ISwapList<E> {
    
    /** Starting size for the internal array */
    private static final int INITIAL_CAPACITY = 10;
    
    /** The array holding our elements */
    private E[] list;
    
    /** How many elements are actually in the list right now */
    private int size;
    
    /**
     * Creates a new empty swap list with initial capacity.
     */
    @SuppressWarnings("unchecked")
    public SwapList() {
        list = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }
    
    /**
     * Adds an element to the end of the list. Grows array if needed.
     * 
     * @param element what to add
     * @throws NullPointerException if element is null
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element.");
        }
        checkCapacity(size + 1);
        list[size] = element;
        size++;
    }
    
    /**
     * Makes sure the array is big enough. Grows it if we're running out of space.
     * Doubles the capacity when growing.
     * 
     * @param newSize the size we need to accommodate
     */
    @SuppressWarnings("unchecked")
    private void checkCapacity(int newSize) {
        if (newSize > list.length) {
            E[] newList = (E[]) new Object[list.length * 2];
            for (int i = 0; i < size; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
    }
    
    /**
     * Removes an element from the list and shifts everything after it down.
     * 
     * @param idx where to remove from
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    @Override
    public E remove(int idx) {
        checkIndex(idx);
        E removed = list[idx];
        // TODO: shift elements down to fill the gap
        size--;
        return removed;
    }
    
    /**
     * Validates that an index is in bounds.
     * 
     * @param idx the index to check
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    private void checkIndex(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }
    
    /**
     * Swaps an element with the one before it. Does nothing if already at front.
     * 
     * @param idx the element to move up
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    @Override
    public void moveUp(int idx) {
        checkIndex(idx);
        if (idx > 0) {
            // TODO: swap with previous element
        }
    }
    
    /**
     * Swaps an element with the one after it. Does nothing if already at end.
     * 
     * @param idx the element to move down
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    @Override
    public void moveDown(int idx) {
        checkIndex(idx);
        if (idx < size - 1) {
            // TODO: swap with next element
        }
    }
    
    /**
     * Shifts an element all the way to the front by moving it repeatedly.
     * 
     * @param idx the element to move
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    @Override
    public void moveToFront(int idx) {
        checkIndex(idx);
        // TODO: could use moveUp repeatedly or implement directly
    }
    
    /**
     * Shifts an element all the way to the back by moving it repeatedly.
     * 
     * @param idx the element to move
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    @Override
    public void moveToBack(int idx) {
        checkIndex(idx);
        // TODO: could use moveDown repeatedly or implement directly
    }
    
    /**
     * Gets the element at a position. Nice and fast with array access.
     * 
     * @param idx where to get from
     * @return the element there
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    @Override
    public E get(int idx) {
        checkIndex(idx);
        return list[idx];
    }
    
    /**
     * Returns how many elements are in the list.
     * 
     * @return the size
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Creates an iterator so we can loop through the list with for-each.
     * 
     * @return a new iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new SwapListIterator();
    }
    
    /**
     * Iterator for going through the swap list one element at a time.
     * Lets us use enhanced for loops and stuff.
     */
    private class SwapListIterator implements Iterator<E> {
        
        /** Where we are in the iteration */
        private int current;
        
        /** Index of the last element we returned, used for remove */
        private int lastReturnedIndex;
        
        /**
         * Sets up a new iterator starting at the beginning.
         */
        public SwapListIterator() {
            current = 0;
            lastReturnedIndex = -1;
        }
        
        /**
         * Checks if there are more elements to iterate through.
         * 
         * @return true if more elements exist
         */
        @Override
        public boolean hasNext() {
            return current < size;
        }
        
        /**
         * Gets the next element in the iteration and advances the position.
         * 
         * @return the next element
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E element = list[current];
            lastReturnedIndex = current;
            current++;
            return element;
        }
        
        /**
         * Removes the last element that was returned by next().
         * Not supporting this for now - just throw exception.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
