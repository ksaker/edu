package ru.platonov.edu.concurrency;

import java.util.Objects;

/**
 * CopyOnWriteArray.
 * <p>
 * </p>
 *
 * @author Platonov Alexey
 * @since 27/05/17.
 */
public class CopyOnWriteArray<T> {

    private Object [] mas = new Object[0];

    public void add(int index, T value) {
        boolean needModification = index >= mas.length;

        if (!needModification) {
            needModification = value == null
                    ? mas[index] != null
                    : !Objects.equals(mas[index], value);
        }

        if (!needModification) {
            return;
        }

        Object [] newArray;
        if (index >= mas.length) {
            newArray = new Object[index + 1];
            System.arraycopy(mas, 0, newArray, 0, mas.length);
        } else {
            newArray = new Object[mas.length+1];

            System.arraycopy(mas, 0, newArray, 0, index);
            System.arraycopy(mas, index, newArray, index + 1, mas.length - index);
        }

        newArray[index] = value;
        mas = newArray;
    }

    public void remove(int index) {
        if(index < 0 || index >= mas.length) {
            throw new IndexOutOfBoundsException();
        }

        Object [] newArray = new Object[mas.length - 1];
        System.arraycopy(mas, 0, newArray, 0, index);
        System.arraycopy(mas, index + 1, newArray, index, mas.length - 1 - index);

        mas = newArray;
    }

    public int  size() {
        return mas.length;
    }

    public T get(int index) {
        if(index < 0 || index >= mas.length) {
            throw new IndexOutOfBoundsException();
        }

        return (T) mas[index];
    }

    public static void main(String[] args) {
        CopyOnWriteArray<Integer> testIteger = new CopyOnWriteArray<>();

        testIteger.add(3, 8);
        testIteger.add(1, 2);
        testIteger.add(4, 16);
        testIteger.add(0, 1);
        testIteger.add(2, 4);

        testIteger.remove(3);

    }
}
