package ca.jrvs.practice.dataStructure.list;

import java.util.LinkedList;
import java.util.List;

public class LinkedListAPIs {
    public static void main(String[] args){
        List<String> names = new LinkedList<>();
        names.add("Tayo");
        names.add("Love");
        names.add("Indigo");

        System.out.println(names.size());
        System.out.println(names.isEmpty());
        System.out.println(names.indexOf("Love"));
        System.out.println(names.contains("Ore"));
        System.out.println(names.get(1));
        System.out.println(names.remove(2));

    }

}
