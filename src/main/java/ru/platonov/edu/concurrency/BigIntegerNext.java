package ru.platonov.edu.concurrency;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * BigIntegerNext.
 * <p>
 *     Напишите реализацию класса с неблокирующим методом BigInteger next(),
 *     который возвращает элементы последовательности: [1, 2, 4, 8, 16, ...].
 *     Код должен корректно работать в многопоточной среде.
 * </p>
 *
 * @author Platonov Alexey
 * @since 29/05/17.
 */
public class BigIntegerNext {

    private AtomicReference<BigInteger> headRef = new AtomicReference<>(null);

    public BigInteger next() {
        BigInteger oldHead;
        BigInteger newHead;

        do {
            oldHead = headRef.get();
            newHead = (oldHead == null) ? BigInteger.valueOf(1) : oldHead.shiftLeft(1);

        } while (!headRef.compareAndSet(oldHead, newHead));

        return newHead;
    }

    public static void main(String[] args) {
        BigIntegerNext bigIntegerNext = new BigIntegerNext();

        System.out.println(bigIntegerNext.next());
        System.out.println(bigIntegerNext.next());
        System.out.println(bigIntegerNext.next());
    }

}
