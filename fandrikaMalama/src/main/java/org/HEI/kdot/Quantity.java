package org.HEI.kdot;

public record Quantity(double value, Unit unit) {
    public Quantity {
        if (value < 0) {
            throw new IllegalArgumentException("did this liquid come from a black hole ?");
        }
    }

    @Override
    public String toString() {
        return value +" " + unit;
    }

    public double toL() {
        return switch (unit) {
            case L -> value;
            case ML -> value / 1000.0;
        };
    }
}
