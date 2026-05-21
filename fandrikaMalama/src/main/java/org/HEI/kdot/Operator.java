package org.HEI.kdot;

import java.util.List;

public enum Operator {

    TELMA(List.of("034", "038")),
    ORANGE(List.of("032", "037")),
    AIRTEL(List.of("033"));

    private final List<String> prefixes;

    Operator(List<String> prefixes) {
        this.prefixes = prefixes;
    }

    public boolean matches(String number) {
        return number != null &&
                prefixes.stream()
                        .anyMatch(number::startsWith);
    }
}
