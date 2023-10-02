package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        System.out.println(source);
        System.out.println(delimiters);

        List<String> list = new ArrayList<>();
        int counterStart = 0;
        for (int i = 0; i < source.length(); i++) {
            char p = source.charAt(i);
            if(delimiters.contains(String.valueOf(p))) {
                if(i > counterStart){
                    list.add(source.substring(counterStart, i));
                    counterStart = i + 1;
                } else if(counterStart == i) {
                    counterStart = i + 1;
                }
            } else if(i == source.length()-1) {
                list.add(source.substring(counterStart, i + 1));
            }
        }
        System.out.println(list);
        return list;
    }
}
