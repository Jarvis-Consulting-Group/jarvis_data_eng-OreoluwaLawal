package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayJLists<E> implements JList<E> {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    transient Object[] elementData; // non-private to simplify nested class access
    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public ArrayJLists(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ArrayJLists() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * Double elementData size if elementData is full.
     */
    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Null");
        }
        if (elementData.length == this.size) {
            int newCapacity = size*2;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public Object[] toArray() {

        return Arrays.stream(elementData).toArray();
    }

    @Override
    public int size() {

        return elementData.length;
    }

    @Override
    public boolean isEmpty() {

        return elementData.length == 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }
}
