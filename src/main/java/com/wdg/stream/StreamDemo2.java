package com.wdg.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author wangdg
 * @Description: Stream创建
 * @date 2017-06-11 00:50:22
 */
public class StreamDemo2 {

    /**
     * Stream的创建方法:
     *  1. Collection.stream()
     *  2. Stream.of()
     *  3. Collection.parallelStream()
     *  4. Stream.empty()
     */
    @Test
    public void testCreateStream(){
        List<String> list = Arrays.asList("1", "4", "2", "6");
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<String> stream = list.stream();// 集合的元素的stream
        Stream<List<String>> stream1 = Stream.of(list);// 整个集合的stream
        IntStream intStream = intList.stream().mapToInt(x -> x);// 创建IntStream
        Stream<String> stream4 = list.parallelStream();// 得到并行Stream
        Stream<Object> emptyStream = Stream.empty();// 创建一个空的Stream
    }
}
