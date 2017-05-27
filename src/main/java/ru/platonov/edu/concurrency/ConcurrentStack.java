package ru.platonov.edu.concurrency;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ConcurrentStack.
 * <p>
 * </p>
 *
 * @author Platonov Alexey
 * @since 23/05/17.
 */
public class ConcurrentStack<T> {

    private AtomicReference<Element> headRef = new AtomicReference<>(null);

    public void push(T value){
        Element newhead = new Element();
        newhead.value = value;
        Element oldHead;
        do {
            oldHead = headRef.get();
            newhead.next = oldHead;
        } while (!headRef.compareAndSet(oldHead, newhead));
    }

    public T pop(){
        Element oldHead = null;
        Element newHead = null;
        do{
            oldHead = headRef.get();
            if(oldHead == null) {
                return null;
            }

            newHead = oldHead.next;
        }while (!headRef.compareAndSet(oldHead, newHead));

        return oldHead.value;
    }

    private class Element {
        private T value;
        private Element next;
    }

    public static void main(String[] args) {
        ConcurrentStack<Integer> integerConcurrentStack = new ConcurrentStack<>();
        integerConcurrentStack.push(1);
        integerConcurrentStack.push(2);

        System.out.println(integerConcurrentStack.pop());
        System.out.println(integerConcurrentStack.pop());
        System.out.println(integerConcurrentStack.pop());

    }
}
