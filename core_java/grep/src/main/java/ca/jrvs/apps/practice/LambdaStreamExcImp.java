package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc{
    @Override
    public Stream<String> createStrStream(String... strings) {
        Stream<String> arr = Arrays.stream(strings);
        return arr;
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        return Arrays.stream(strings).map(i -> i.toUpperCase());
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(i -> i.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return IntStream.of(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.range(start, end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.asDoubleStream().map(i -> Math.sqrt(i));
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(i -> (i % 2 != 0));
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return (String message) -> System.out.println(prefix + message + suffix);
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        createStrStream(messages).forEach(message -> printer.accept(message));
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        intStream.filter(i -> (i % 2 != 0)).forEach(oddNo -> printer.accept( String.valueOf(oddNo)));
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        return ints.flatMap(List::stream);
    }


}
