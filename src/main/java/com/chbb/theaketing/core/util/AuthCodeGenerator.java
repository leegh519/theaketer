package com.chbb.theaketing.core.util;

import java.util.Random;
import java.util.stream.Collectors;

public class AuthCodeGenerator {

    private static Random random = new Random();

    public static String generate(int digit) {
        String code = random.ints(digit, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
        return code;
    }

}
