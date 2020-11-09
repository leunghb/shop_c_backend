package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.Goods;
import shop.demo.entity.GoodsType;
import shop.demo.entity.Result;
import shop.demo.service.GoodsService;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 获取商品分类
     *
     * @param soldOut int 状态 0-未下架 1-已下架
     */
    @PostMapping("goods/getGoodsType")
    public Result<Object> getGoodsType(@RequestParam(required = false, defaultValue = "0") int soldOut) {
        List<GoodsType> goodsTypes = goodsService.getGoodsType(soldOut);
        return Result.success(goodsTypes);
    }

    /**
     * 获取商品
     *
     * @param soldOut     int 状态 0-未下架 1-已下架 -1-全部
     * @param goodsTypeId int 商品分类id -1-全部
     * @param mainTitle   String 商品主标题
     */
    @PostMapping("goods/getGoods")
    public Result<Object> getGoods(@RequestParam(required = false, defaultValue = "-1") int soldOut,
                                   @RequestParam(required = false, defaultValue = "-1") int goodsTypeId,
                                   @RequestParam(required = false, defaultValue = "null") String mainTitle) {
        System.out.println(soldOut);
        System.out.println(goodsTypeId);
        System.out.println(mainTitle);
        List<Goods> goods = goodsService.getGoods(soldOut, goodsTypeId, mainTitle);
        return Result.success(goods);
    }
}
