package com.share.stream;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class StreamDemo {

    @Test
    public void testBoxStream(){
        IntStream.of(1,2,3,4,2).sorted();

        System.out.println("dddd");
    }

    public static void main(String[] args) {
        IntStream.range(5,3).forEach(i->System.out.println(i));

    }
}
