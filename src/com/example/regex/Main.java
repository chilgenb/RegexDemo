package com.example.regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern passwordPattern = Pattern.compile("(?:^|\\W|\\S)password(?:$|\\W|\\S)");
    private static final Pattern characterPattern = Pattern.compile("^(?=.*[a-z])(?=."
            + "*[A-Z])(?=.*\\d)"
            + "(?=.*[-+_!@#$%^&*., ?]).+$");

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printOutput(input.nextLine());
    }

    public static String printOutput(String str) {
        Matcher match;
        boolean isAMatch;
        boolean isAllowed = false;

        Map<Pattern, String> matchers = buildMatchers();

        if (matchers != null && !matchers.isEmpty()) {
            for(Map.Entry<Pattern, String> matches : matchers.entrySet()) {
                match = matches.getKey().matcher(str);
                isAMatch = match.find();
                if (isAMatch && matches.getValue() == "ALLOWED") {
                    isAllowed = true;
                    continue;
                } else if (isAMatch && matches.getValue() == "NOT") {
                    System.out.println(" ' " + str + " ' " + " is not an allowed password");
                    isAllowed = false;
                    break;
                }
            }
            if (str.length() < 7 || str.length() > 37) {
                isAllowed = false;
            }
            System.out.println(" ' " + str + " '  is " + (isAllowed ? "" : " not ") + "an allowed password");
        }
        return str;
    }

    private static Map<Pattern, String> buildMatchers() {
        Map<Pattern, String> patternsToMatchAgainst = new HashMap<>();
        //NOT - input should not match, reject it
        //ALLOWED - input is allowed to contain, accept it
        patternsToMatchAgainst.put(passwordPattern, "NOT");
        patternsToMatchAgainst.put(characterPattern, "ALLOWED");
        return patternsToMatchAgainst;
    }
}
