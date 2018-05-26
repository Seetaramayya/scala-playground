package com.seeta.codility.lesson3;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Seeta (Ramayya) Vadali
 */
public class PermMissingElemTest {
    @Test
    public void testSolution() {
        /*
         * TODO:
         * In my case it is working fine but codility says
         * following message. I tried in both scala and java.
         *
         * WRONG ANSWER got -2147483647 expected 1
         */
        int[] input = IntStream.range(2, 100002).toArray();
        assertThat(PermMissingElem.solution(input), is(1));
    }
}
