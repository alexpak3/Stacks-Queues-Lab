package impl;

import interfaces.IDoubleStack;
import interfaces.IStack;
import common.StackEmptyException;
import common.StackOverflowException;

public class doublestack implements IDoubleStack {
    private int[] elements; // Array to hold elements of both stacks
    private int firstStackTopIndex; // Index of the top element in the first stack
    private int secondStackTopIndex; // Index of the top element in the second stack
    private int capacity; // Maximum size of the array

    // Constructor to initialize the double stack with a given maximum size
    public doublestack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0!");
        }
        this.capacity = capacity;
        this.elements = new int[capacity];
        this.firstStackTopIndex = -1; // Initialize first stack as empty
        this.secondStackTopIndex = capacity; // Initialize second stack as empty
    }

    // Returns an implementation of the first stack
    @Override
    public IStack getFirstStack() {
        return new IStack() {
            @Override
            public void push(Object value) throws StackOverflowException {
                if (!(value instanceof Integer)) {
                    throw new IllegalArgumentException("Only integers are allowed");
                }
                if (firstStackTopIndex + 1 == secondStackTopIndex) {
                    throw new StackOverflowException(); // Check for overflow
                }
                elements[++firstStackTopIndex] = (Integer) value; // Push value onto the first stack
            }

            @Override
            public Object pop() throws StackEmptyException {
                if (firstStackTopIndex == -1) {
                    throw new StackEmptyException(); // Check for underflow
                }
                return elements[firstStackTopIndex--]; // Pop value from the first stack
            }

            @Override
            public Object top() throws StackEmptyException {
                if (firstStackTopIndex == -1) {
                    throw new StackEmptyException(); // Check if stack is empty
                }
                return elements[firstStackTopIndex]; // Return top value of the first stack
            }

            @Override
            public int size() {
                return firstStackTopIndex + 1; // Return the size of the first stack
            }

            @Override
            public boolean isEmpty() {
                return firstStackTopIndex == -1; // Check if the first stack is empty
            }

            @Override
            public void clear() {
                firstStackTopIndex = -1; // Clear the first stack
            }
        };
    }

    // Returns an implementation of the second stack
    @Override
    public IStack getSecondStack() {
        return new IStack() {
            @Override
            public void push(Object value) throws StackOverflowException {
                if (!(value instanceof Integer)) {
                    throw new IllegalArgumentException("Only integers are allowed");
                }
                if (secondStackTopIndex - 1 == firstStackTopIndex) {
                    throw new StackOverflowException(); // Check for overflow
                }
                elements[--secondStackTopIndex] = (Integer) value; // Push value onto the second stack
            }

            @Override
            public Object pop() throws StackEmptyException {
                if (secondStackTopIndex == capacity) {
                    throw new StackEmptyException(); // Check for underflow
                }
                return elements[secondStackTopIndex++]; // Pop value from the second stack
            }

            @Override
            public Object top() throws StackEmptyException {
                if (secondStackTopIndex == capacity) {
                    throw new StackEmptyException(); // Check if stack is empty
                }
                return elements[secondStackTopIndex]; // Return top value of the second stack
            }

            @Override
            public int size() {
                return capacity - secondStackTopIndex; // Return the size of the second stack
            }

            @Override
            public boolean isEmpty() {
                return secondStackTopIndex == capacity; // Check if the second stack is empty
            }

            @Override
            public void clear() {
                secondStackTopIndex = capacity; // Clear the second stack
            }
        };
    }
}