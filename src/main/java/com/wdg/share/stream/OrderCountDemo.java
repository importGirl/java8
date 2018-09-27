package com.wdg.share.stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangdg
 * @Description:
 * @date 2018/9/7 23:16
 */
public class OrderCountDemo {
    private static List<SkuCartDto> skuCarts;

    // 加载数据
    static {
        skuCarts = Stream.generate(new SkuCartSupplier()).limit(10).collect(Collectors.toList());
    }

    @Test
    public void testCountSkuCart() {
        // 拆单 来源拆
        Map<Integer, Map<Integer, List<SkuCartDto>>> collect = skuCarts.stream().collect(Collectors.groupingBy(SkuCartDto::getPlatformSource, Collectors.groupingBy(SkuCartDto::getSupplierId)));
        Map<Integer, List<SkuCartDto>> wfOrder = collect.get(1);
        Map<Integer, List<SkuCartDto>> sdOrder = collect.get(3).get(3).stream().collect(Collectors.groupingBy(SkuCartDto::getGoodsId));
        for (Integer integer : wfOrder.keySet()) {
            System.out.println(wfOrder.get(integer));
        }
        for (Integer integer : sdOrder.keySet()) {
            System.out.println(sdOrder.get(integer));
        }


    }

    public static void main(String[] args) {


    }

    public static class SkuCartSupplier implements Supplier<SkuCartDto> {

        private Random random = new Random();
        private int index = 1;
        private long skuNumber = 2018090700000001L;


        @Override
        public SkuCartDto get() {
            index = ++index;
            skuNumber = ++skuNumber;
            int supplierId = index % 2 == 1 ? 1 : 3;
            int useScoreFlag = index % 2 == 1 ? 1 : 0;
            BigDecimal currentPrice = BigDecimal.TEN.multiply(new BigDecimal(index));
            BigDecimal skuBalancePrice = currentPrice.multiply(new BigDecimal("0.3"));
            int platformSource = index % 2 == 1 ? 3 : 1;
            return new SkuCartDto(index, skuNumber, index + 2, supplierId, "xixi", useScoreFlag, currentPrice, skuBalancePrice, skuBalancePrice,
                    skuBalancePrice, 5,
                    supplierId);
        }
    }


}
