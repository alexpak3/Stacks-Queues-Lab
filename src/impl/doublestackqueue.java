package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IQueue;
import interfaces.IStack;

public class doublestackqueue implements IQueue {
    private doublestack doublestack;
    private int capacity;

    public doublestackqueue(int capacity) {
        this.capacity = capacity;
        this.doublestack = new doublestack(capacity);
    }

    @Override
    public void enqueue(Object element) throws QueueFullException, StackOverflowException {
        if (size() == capacity) {
            throw new QueueFullException();
        }
        doublestack.getFirstStack().push(element);
    }

    @Override
    public Object dequeue() throws QueueEmptyException, StackEmptyException {
        IStack outputStack = doublestack.getSecondStack();
        IStack inputStack = doublestack.getFirstStack();

        // If output stack is empty, transfer all elements from input stack
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                try {
                    outputStack.push(inputStack.pop());
                } catch (StackOverflowException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (outputStack.isEmpty()) {
            throw new QueueEmptyException();
        }

        return outputStack.pop();
    }

    @Override
    public int size() {
        return doublestack.getFirstStack().size() + doublestack.getSecondStack().size();
    }

    @Override
    public boolean isEmpty() {
        return doublestack.getFirstStack().isEmpty() && doublestack.getSecondStack().isEmpty();
    }

    @Override
    public boolean isFull() {
        return size() == capacity;
    }

    @Override
    public void clear() {
        doublestack.getFirstStack().clear();
        doublestack.getSecondStack().clear();
    }
}