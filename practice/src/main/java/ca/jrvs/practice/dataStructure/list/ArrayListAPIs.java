package ca.jrvs.practice.dataStructure.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListAPIs {
    public static void main(String[] args){
        List<String> names = new ArrayList<>();

        names.add("Tayo");
        names.add("Love");
        names.add("Indigo");

        int s = names.size();

        String getName = names.get(0);
        Boolean isPresent = names.contains("Love");
        int index = names.indexOf("Love");

        names.add("Ore");
        boolean isRemoved = names.remove("Ore");
        String removedValue = names.remove(2);

        names.sort(String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names.toArray()));

    }
}
