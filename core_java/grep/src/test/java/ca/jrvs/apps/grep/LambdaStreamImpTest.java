package ca.jrvs.apps.grep;

import ca.jrvs.apps.practice.LambdaStreamExc;
import ca.jrvs.apps.practice.LambdaStreamExcImp;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class LambdaStreamImpTest {
    @Test
    public void createStrStream(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        Stream<String> strs = lambdaStreamExc.createStrStream("Hello", "World", "Welcome", "To", "Java");
        assertEquals(Arrays.asList("Hello", "World", "Welcome", "To", "Java"), strs.collect(Collectors.toList()));
    }
    @Test
    public void toUpperCase(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        Stream<String> strs = lambdaStreamExc.toUpperCase("Hello", "World");
        assertEquals(Arrays.asList("HELLO", "WORLD"), strs.collect(Collectors.toList()));
    }

    @Test
    public void filter(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        Stream<String> strs = lambdaStreamExc.createStrStream("Hello", "World", "Welcome", "To", "Java");
        Stream<String> strsFilter = lambdaStreamExc.filter(strs, "o");
        assertEquals(Arrays.asList("Hello", "World", "Welcome", "To"), strsFilter.collect(Collectors.toList()) );
    }

    @Test
    public void createIntStream(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        int[] intValues = new int[]{1, 2, 3, 4};
        IntStream intStream = lambdaStreamExc.createIntStream(intValues);
        assertEquals(Arrays.stream(intValues).boxed().collect(Collectors.toList()), intStream.boxed().collect(Collectors.toList()));
    }

    @Test
    public void streamToList(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        Stream<String> strs = lambdaStreamExc.createStrStream("Hello", "World", "Welcome", "To", "Java");
        List<String> lists = lambdaStreamExc.toList(strs);
        assertEquals(Arrays.asList("Hello", "World", "Welcome", "To", "Java"), lists);
    }

    @Test
    public void intStreamToList(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        int[] intValues = new int[]{1, 2, 3, 4};
        IntStream intStream = lambdaStreamExc.createIntStream(intValues);
        List<Integer> lists = lambdaStreamExc.toList(intStream);
        assertEquals(Arrays.asList(1,2,3,4), lists);
    }

    @Test
    public void rangeIntStream(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        IntStream intStream = lambdaStreamExc.createIntStream(0,5);
        assertEquals(Arrays.asList(0,1,2,3,4), intStream.boxed().collect(Collectors.toList()));
    }

    @Test
    public void squareRootIntStream(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        IntStream intStream = lambdaStreamExc.createIntStream(0,2);
        DoubleStream doubleStream = lambdaStreamExc.squareRootIntStream(intStream);
        assertEquals(Arrays.asList(0.00,1.00), doubleStream.boxed().collect(Collectors.toList()));
    }

    @Test
    public void getOdd(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        IntStream intStream = lambdaStreamExc.createIntStream(0,5);
        IntStream intStream1 = lambdaStreamExc.getOdd(intStream);
        assertEquals(Arrays.asList(1, 3), intStream1.boxed().collect(Collectors.toList()));
    }

    @Test
    public void printMessages(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        lambdaStreamExc.printMessages(new String[]{"a", "b", "c"}, lambdaStreamExc.getLambdaPrinter("msg:", "!"));
    }

    @Test
    public void printOdd(){
        LambdaStreamExc lambdaStreamExc = new LambdaStreamExcImp();
        IntStream intStream = lambdaStreamExc.createIntStream(0,5);
        lambdaStreamExc.printOdd(intStream, lambdaStreamExc.getLambdaPrinter("odd number:", "!"));
    }
}
