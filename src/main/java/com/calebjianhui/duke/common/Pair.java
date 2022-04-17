package com.calebjianhui.duke.common;

/**
 * A Custom data structure that contains 2 template variables
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * Pair Constructor
     * - Takes in 2 value
     *
     * @param first First Variable
     * @param second Second Variable
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

}
