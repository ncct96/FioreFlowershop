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

        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) {

        return true;
    }

    @Override
    public boolean remove(int existPosition) {
        return true;
    }

    @Override
    public void clear() {
    }

    @Override
    public void getItem(int position) {
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

}
