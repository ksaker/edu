package ru.platonov.edu.sort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by platonov on 23/02/17.
 */
public class StreamApi {

    public void creationStream() throws IOException {
        int[] integerArray = {1,2,3,4,5,6};
        Collection<String> stringCollection = Arrays.asList("string1", "string2", "string3");

        Stream<String> streamFromCollection = stringCollection.stream();
        Stream<String> parallelStreamFromCollection = stringCollection.parallelStream();
        Stream<Integer> elementIntegerStream = Stream.of(1, 2, 3, 4, 5, 6);
        IntStream stream = Arrays.stream(integerArray);
        Stream<String> lines = Files.lines(Paths.get("C:/text.txt"));
        IntStream chars = "abc".chars();
        Stream<Integer> builder = Stream.<Integer>builder().add(1).add(2).add(3).build();
        Stream<Integer> iterate = Stream.iterate(1, a -> a + 1);//Создание бесконечного стрима начиная от 1 с приращением на 1
        Stream<String> generate = Stream.generate(() -> "a1");//Генерирует стрим из одного элемента a1

        //9
    }

    //Есть другое название, которое я не помню =(
    public void conveyor(){
        Stream<Integer> elementIntegerStream = Stream.of(1, 2, 3, 4, 5, 6);

        elementIntegerStream
                .filter(a -> a % 2 == 0)
                .skip(1)
                .distinct()
                .map(a -> a + 1)
                .peek(System.out::print)
                .limit(20)
                .sorted((o1, o2) -> Integer.compare(o1, o2) * -1)
//                .mapToInt()
//                .mapToDouble()
//                .mapToLong()
//                .flatMap((Function<Integer, Stream<?>>) integer -> integer) //Посмотреть какой пример дают
//                .flatMapToInt()
//                .flatMapToDouble()
//                .flatMapToLong()
                .forEach(System.out::print); //Терминальная функция нужна только для вывода результата!

        //14
    }

    public void terminal() {
        Stream<Integer> elementIntegerStream = Stream.of(1, 2, 3, 4, 5, 6);

        elementIntegerStream.findFirst().get();
        elementIntegerStream.findAny().get();
        elementIntegerStream.collect(Collectors.toList());

        elementIntegerStream.count();
        elementIntegerStream.anyMatch(a -> a == 2);
        elementIntegerStream.noneMatch(a -> a == 2);
        elementIntegerStream.allMatch(a -> a < 7);

        elementIntegerStream.max(Integer::compareTo).get();
        elementIntegerStream.min(Integer::compareTo).get();
        IntStream.empty().average();

        elementIntegerStream.forEach(System.out::print);
        elementIntegerStream.forEachOrdered(System.out::print);

        elementIntegerStream.toArray();
        elementIntegerStream.reduce((a1, a2) -> a1 + a2);

        //14
    }

    public void shortCircuit(){
        Stream<Integer> elementIntegerStream = Stream.of(1, 2, 3, 4, 5, 6);

        System.out.println(elementIntegerStream.anyMatch(a -> a == 2));
        System.out.println(elementIntegerStream.findFirst().get());
        System.out.println(elementIntegerStream.findAny().get());

        //3
    }

    public void math(){
        IntStream elementIntegerStream = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6});

        elementIntegerStream.max();
        elementIntegerStream.min();
        elementIntegerStream.count();

        elementIntegerStream.average();
        Stream<Integer> stream = elementIntegerStream.mapToObj(Integer::new); //Возвращает обратно в объектный стрим

        //4
    }

    public void helpFullMethods() {
        Stream<Integer> elementIntegerStream = Stream.of(1, 2, 3, 4, 5, 6);

        elementIntegerStream.isParallel(); //показывает параллельный стрим или нет.
        elementIntegerStream.parallel();    //выставляет свойство стрима как параллельный
        elementIntegerStream.sequential(); // выставляет свойство стрима как последовательный

        //3
    }

    public static void main(String[] args) {

    }

}
