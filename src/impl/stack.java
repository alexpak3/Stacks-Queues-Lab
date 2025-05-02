package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

public class stack implements IStack {
    private Object[] value;
    private int top;     // Current top index (-1 when empty)
    private int capacity; // Maximum capacity of the stack

    public stack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.value = new Object[capacity];
        this.top = -1;   // Stack starts empty
    }

    @Override
    public void push(Object element) throws StackOverflowException {
        if (isFull()) {  // Fixed: Check if stack is full, not element
            throw new StackOverflowException();
        }
        top++;
        value[top] = element;
    }

    @Override
    public Object pop() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        Object temp = value[top];
        value[top] = null; // Help garbage collection
        top--;
        return temp;
    }

    @Override
    public Object top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        return value[top];
    }

    @Override
    public int size() {
        return top + 1; // Current number of elements
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }


    @Override
    public void clear() {
        while (top >= 0) {
            value[top] = null;
            top--;
        }
    }
}