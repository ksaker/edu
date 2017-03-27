package ru.platonov.edu.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by platonov on 25/03/17.
 */
public class Fibonaci {

    /**
     * Самый простой вариант решения - рекурсивная формула
     * У рекурсивной реализации есть один очень большой минус: пересекающиеся вычисления
     * Когда вызывается fib(n), то подсчитываются fib(n-1) и fib(n-2).
     * Но когда считается fib(n-1), она снова независимо подсчитает fib(n-2) – то есть,
     * fib(n-2) подсчитается дважды. Если продолжить рассуждения, будет видно, что fib(n-3)
     * будет подсчитана трижды, и т.д. Слишком много пересечений.
     * Решение этой проблемы - improvedRecurrent
     * Хорошее:
     * Очень простая реализация, повторяющая математическое определение
     * Плохое:
     * Экспоненциальное время выполнения. Для больших n очень медленно
     * Злое:
     * Переполнение стека
     */
    public int recurrent(int n) {
        if (n <= 2) {
            return 1;
        }

        return recurrent(n - 1) + recurrent(n - 2);
    }

    /**
     * надо просто запоминать результаты, чтобы не подсчитывать их снова.
     * Время и память у этого решения расходуются линейным образом.
     * В решении я использую словарь, но можно было бы использовать и простой массив.
     *
     * Хорошее:
     *  Просто превратить рекурсию в решение с запоминанием.
     *  Превращает экспоненциальное время выполнение в линейное, для чего тратит больше памяти.
     * Плохое:
     *  Тратит много памяти
     * Злое:
     *  Возможно переполнение стека, как и у рекурсии
     */

    private ArrayList<Integer> mas = new ArrayList<>();
    {
        mas.add(0);
        mas.add(1);
        mas.add(1);
    }

    public int improvedRecurrent(int n) {
        if (mas.size() >= n + 1) {
            return mas.get(n);
        }

        mas.add(improvedRecurrent(n - 1) + improvedRecurrent(n - 2));

        return mas.get(n);
    }

    /**
     * После решения с запоминанием становится понятно, что нам нужны не все предыдущие результаты,
     * а только два последних. Кроме этого, вместо того, чтобы начинать с fib(n) и идти назад,
     * можно начать с fib(0) и идти вперёд. У следующего кода линейное время выполнение,
     * а использование памяти – фиксированное. На практике скорость решения будет ещё выше,
     * поскольку тут отсутствуют рекурсивные вызовы функций и связанная с этим работа. И код выглядит проще.
     *
     * Это решение часто приводится в качестве примера динамического программирования.
     *
     * Хорошее:
     *  Быстро работает для малых n, простой код
     * Плохое:
     *  Всё ещё линейное время выполнения
     * Злое:
     *  Да особо ничего.
     */
    public BigInteger dynamic(int n) {
        BigInteger a = BigInteger.valueOf(0);
        BigInteger b = BigInteger.valueOf(1);

        for (int i = 1; i <= n; i++) {
            BigInteger tmp = a;
            a = a.add(b);
            b = tmp;
        }

        return a;
    }

    /**
     * Откуда и растёт «золотое сечение» ϕ=(1+√5)/2.
     * Подставив исходные значения и проделав ещё вычисления, мы получаем:
     *
     * Хорошее:
     *  Быстро и просто для малых n
     * Плохое:
     *  Требуются операции с плавающей запятой. Для больших n потребуется большая точность.
     * Злое:
     *  Использование комплексных чисел для вычисления Fn красиво с математической точки зрения,
     *  но уродливо — с компьютерной.
     */
    public int gSection(int n) {
        double sqrt5 = Math.sqrt(5);
        double phi = (sqrt5 + 1) / 2;

        return (int)(Math.pow(phi, n)/sqrt5 + 0.5);
    }

    /**
     * Рассмотрим матрицу:
     * <p>
     * | F0, F1 | = | 0 1 |  = One
     * | F1, F2 |   | 1 1 |
     * Произведение матриц:
     * <p>
     * [ F(n-2), F(n-1) ] * One = [ F(n-1), F(n)]
     * показывает, что можно получить любое число Фибоначчи возведя матрицу One в степень N.
     * <p>
     * Воспользовавшись алгоритмом быстрого возведения в степень, можно реализовать
     * Поиск n-го числа Фибоначчи за O(logN)
     */
    public static class Fib
    {
        public final static BigInteger[][] ONE = {
                        {BigInteger.ZERO, BigInteger.ONE},
                        {BigInteger.ONE, BigInteger.ONE}
        };

        public static BigInteger[][] mul(BigInteger[][] a, BigInteger[][] b) {
            BigInteger[][] res = {
                    {a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0])), a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]))},
                    {a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0])), a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]))}
            };
            return res;
        }

        public static BigInteger[][] pow(BigInteger[][] a, int k) {

            if (k == 0) return ONE;
            if (k == 1) return a;
            if (k == 2) return mul(a, a);
            if (k % 2 == 1) return mul(ONE, pow(a, k - 1));
            return pow(pow(a, k / 2), 2);
        }

        public static void main(String[] args)
        {
            System.out.println(1024+": "+pow(ONE, 1024)[0][1]);
            System.out.println(4096+": "+pow(ONE, 4096)[0][1]);
            System.out.println(4096+": "+pow(ONE, 8)[0][1]);
        }
    }

    /**
     * Максимальное и минимальное double
     * 2,2250738585072014×10^-308
     * 1,7976931348623157×10^308
     */

    public static void main(String[] args) {
        System.out.println(new Fibonaci().gSection(8));
        System.out.println(new Fibonaci().gSection(7));
        System.out.println(new Fibonaci().dynamic(4096));
        System.out.println(Fib.pow(Fib.ONE, 8)[0][0]);
        System.out.println(Fib.pow(Fib.ONE, 8)[0][1]);
    }
}
