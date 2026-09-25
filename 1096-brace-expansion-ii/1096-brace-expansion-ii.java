import java.util.*;

class Solution {

    private int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> result = parseExpression(expression);

        List<String> answer = new ArrayList<>(result);

        Collections.sort(answer);

        return answer;
    }

    // Handles UNION
    // Example: a,b,c
    private Set<String> parseExpression(String s) {

        Set<String> result = new HashSet<>();

        // First term
        result.addAll(parseTerm(s));

        // More terms separated by comma
        while (index < s.length() && s.charAt(index) == ',') {

            index++; // skip ','

            result.addAll(parseTerm(s));
        }

        return result;
    }

    // Handles CONCATENATION
    // Example: ab{c,d}e
    private Set<String> parseTerm(String s) {

        Set<String> result = new HashSet<>();

        // Important:
        // Empty string is the identity for concatenation
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> current;

            // Case 1: Nested expression
            if (s.charAt(index) == '{') {

                index++; // skip '{'

                current = parseExpression(s);

                index++; // skip '}'

            }

            // Case 2: Normal character
            else {

                current = new HashSet<>();

                current.add(String.valueOf(s.charAt(index)));

                index++;
            }

            // Concatenate result with current
            result = concatenate(result, current);
        }

        return result;
    }

    private Set<String> concatenate(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        for (String a : first) {

            for (String b : second) {

                result.add(a + b);
            }
        }

        return result;
    }
}