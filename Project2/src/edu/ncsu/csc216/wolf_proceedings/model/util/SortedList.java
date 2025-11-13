package edu.ncsu.csc216.wolf_proceedings.model.util;

/**
 * A list implementation that keeps everything in sorted order using linked nodes.
 * Doesn't allow duplicates since that would mess up the sorting logic.
 * 
 * @param <E> type of elements, must be comparable so we can sort them
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
    
    /** Points to the first node in the list */
    private ListNode front;
    
    /** Tracks how many elements we have */
    private int size;
    
    /**
     * Creates an empty sorted list, ready to use.
     */
    public SortedList() {
        front = null;
        size = 0;
    }
    
    /**
     * Adds an element in sorted order. Won't add nulls or duplicates.
     * Throws exceptions if you try to do either of those things.
     * 
     * @param element the element to add
     * @throws NullPointerException if element is null
     * @throws IllegalArgumentException if element cannot be added 
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element");
        }
        
        // TODO: Check for duplicates and insert in sorted order
        // Remember to increment size after adding
    }
    
    /**
     * Removes the element at the specified index and returns it.
     * 
     * @param idx the index to remove from
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    @Override
    public E remove(int idx) {
        checkIndex(idx);
        // TODO: implement removal - need to handle front case separately
        return null;
    }
    
    /**
     * Makes sure the index is valid for the current list.
     * Throws an exception if it's not within bounds.
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
     * Checks if an element exists in the list.
     * 
     * @param element the element to look for
     * @return true if it's in the list
     */
    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }
    
    /**
     * Gets the element at a given index.
     * 
     * @param idx the index to retrieve from
     * @return the element at that position
     * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
     */
    @Override
    public E get(int idx) {
        checkIndex(idx);
        // TODO: walk through the list to find the right node
        return null;
    }
    
    /**
     * Returns the current size of the list.
     * 
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Finds where an element is in the list by walking through.
     * 
     * @param element the element to find
     * @return the index, or -1 if not found
     */
    @Override
    public int indexOf(E element) {
        // TODO: walk through and compare each node's data
        return -1;
    }
    
    /**
     * A node in the linked list. Holds data and points to the next node.
     * Pretty standard linked list node implementation.
     */
    private class ListNode {
        /** The data stored in this node */
        public E data;
        
        /** Reference to the next node in the chain */
        public ListNode next;
        
        /**
         * Creates a new node with data and a link to the next node.
         * 
         * @param data the data to store
         * @param next the next node in the chain (or null if this is the last one)
         */
        public ListNode(E data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}
