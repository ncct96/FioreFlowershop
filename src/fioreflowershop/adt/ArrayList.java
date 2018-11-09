/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fioreflowershop.adt;

/**
 *
 * @author Nicholas
 */
public class ArrayList<T> implements ListInterface<T> {

    private T[] list;
    private int totalEntries;
    private static final int DEFAULT_LIST_SIZE = 25;

    public ArrayList() {
        this(DEFAULT_LIST_SIZE);
    }

    public ArrayList(int defaultSize) {
        totalEntries = 0;
        list = (T[]) new Object[defaultSize];
    }

    @Override
    public boolean add(T newEntry) {

        if (isFull()) {
            expandArray();
        }

        list[totalEntries] = newEntry;
        totalEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= totalEntries + 1)) {
            if (isFull()) {
                expandArray();
            }

            makeRoom(newPosition);
            list[newPosition - 1] = newEntry;
            totalEntries++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public T remove(int existPosition) {
        T result = null;

        if ((existPosition >= 1) && (existPosition <= totalEntries)) {
            result = list[existPosition - 1];

            if (existPosition < totalEntries) {
                removeGap(existPosition);
            }

            totalEntries--;
        }

        return result;
    }

    @Override
    public void clear() {
        totalEntries = 0;
    }

    @Override
    public T getItem(int position) {
        T result = null;

        if ((position >= 1) && (position <= totalEntries)) {
            result = list[position - 1];
        }

        return result;
    }

    @Override
    public int getTotalEntries() {
        return totalEntries;
    }

    @Override
    public boolean isEmpty() {
        return totalEntries == 0;
    }

    @Override
    public boolean isFull() {
        return totalEntries == list.length;
    }

    private void expandArray() {
        T[] oldList = list;

        list = (T[]) new Object[list.length * 2];

        list = oldList.clone();

    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = totalEntries - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            list[index + 1] = list[index];
        }
    }

    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of list
        int removedIndex = givenPosition - 1;
        int lastIndex = totalEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            list[index] = list[index + 1];
        }
    }

    public String toString() {
        String outputStr = "";
        for (int index = 0; index < totalEntries; ++index) {
            outputStr += list[index] + "\n";
        }

        return outputStr;
    }
}
