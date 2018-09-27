package com.wdg.share.stream;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author wangdg
 * @Description:
 * @date 2018/9/7 22:46
 */
@ToString@Getter@Setter@NoArgsConstructor@AllArgsConstructor
  class SkuCartDto {

    private  int goodsId;
    private  long skuNumber;
    private  Integer cartId;
    private  Integer supplierId;
    private  String supplierName;

    // 1:使用 0:不使用
    private  Integer useScoreFlag;
    // 购物车总和
    private  BigDecimal currentPrice;
    private  BigDecimal skuBalancePrice;
    private  BigDecimal canUseScore;
    private BigDecimal canUseCoupon;

    private  Integer number;

    /**商品信息来源平台:1,五福商城2,合作商2 3,顺道ERP*/
    private  Integer platformSource;


}
