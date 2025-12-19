package com.jonathas.challenge.outsera.util;

import java.util.Arrays;
import java.util.List;

public class ProducerParser {

    public static List<String> parse(String producersRaw) {
        return Arrays.stream(normalizeDelimiters(producersRaw).split(","))
            .map(String::trim)
            .filter(name -> !name.isEmpty())
            .toList();
    }

    private static String normalizeDelimiters(String input) {
        return input.replace(", and ", ", ")
                    .replace(" and ", ", ");
    }

}
