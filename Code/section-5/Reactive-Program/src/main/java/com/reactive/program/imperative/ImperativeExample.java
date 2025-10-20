package com.reactive.program.imperative;

import java.util.ArrayList;
import java.util.List;

public class ImperativeExample {
    public static void main(String[] args) {
        var names = List.of("Polas", "Naime", "Jakariya", "Jakir", "James", "Polas");
        var filterNames = filterNameWithLength(names, 5);
        System.out.println("filterNames = " + filterNames);

        var distinctFilterName = filterUniqueNameWithLength(names, 5);
        System.out.println("distinctFilterName = " + distinctFilterName);
    }

    private static Object filterNameWithLength(List<String> names, int length) {

        List<String> filteredNames = new ArrayList<>();

        for (String name: names){
            if(name.length()<= length){
                filteredNames.add(name);
            }
        }
        return filteredNames;
        
    }

    private static Object filterUniqueNameWithLength(List<String> names, int length) {

        List<String> filteredNames = new ArrayList<>();

        for (String name: names){
            if(name.length()<= length && !filteredNames.contains(name)){
                filteredNames.add(name);
            }
        }
        return filteredNames;

    }
}
