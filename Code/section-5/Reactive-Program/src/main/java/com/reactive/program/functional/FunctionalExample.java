package com.reactive.program.functional;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalExample {

    public static void main(String[] args) {
        var names = List.of("Polas", "Naime", "Jakariya", "Jakir", "James");
        var filterNames = filterUniqueNameWithLength(names, 5);
        System.out.println("filterNames = " + filterNames);

        var distinctFilterName = filterNameWithLength(names, 5);
        System.out.println("distinctFilterName = " + distinctFilterName);

        var filterNameAndUppercase = filterNameAndUppercaseWithLength(names, 5);
        System.out.println("filterNameAndUppercase = " + filterNameAndUppercase);

        var filterAndSortedNameAndUppercase = filterAndSortedNameAndUppercaseWithLength(names, 5);
        System.out.println("filterAndSortedNameAndUppercase = " + filterAndSortedNameAndUppercase);
    }

    private static Object filterUniqueNameWithLength(List<String> names, int length) {

       return names.stream().filter(name->
           name.length() <= length
       ).collect(Collectors.toList());

    }


    private static Object filterNameWithLength(List<String> names, int length) {

        return names.stream()
                .distinct()
                .filter(name-> name.length() <= length)
                .collect(Collectors.toList());

    }

    private static Object filterNameAndUppercaseWithLength(List<String> names, int length) {

       return names.stream()
               .distinct()
               .map(String::toUpperCase)
               .filter(name->name.length() <= length)
               .collect(Collectors.toList());

    }

    private static Object filterAndSortedNameAndUppercaseWithLength(List<String> names, int length) {

        return names.stream()
                .distinct()
                .sorted()
                .map(String::toUpperCase)
                .filter(name-> name.length() <= length)
                .collect(Collectors.toList());

    }

}
