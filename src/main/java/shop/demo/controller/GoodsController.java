package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.*;
import shop.demo.service.GoodsService;
import shop.demo.service.GoodsSpecsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsSpecsService goodsSpecsService;

    /**
     * 获取商品分类
     *
     * @param soldOut int 状态 0-未下架 1-已下架 -1-全部
     */
    @PostMapping("goods/getGoodsType")
    public Result<Object> getGoodsType(@RequestParam(required = false, defaultValue = "0") int soldOut) {
        List<GoodsType> goodsTypes = goodsService.getGoodsType(soldOut);
        return Result.success(goodsTypes);
    }

    /**
     * 获取商品列表
     *
     * @param soldOut     int 状态 0-上架 1-已下架 -1-全部
     * @param goodsTypeId int 商品分类id -1-全部
     * @param mainTitle   String 商品主标题
     * @param page        int 页数
     * @param limit       int 每页条数
     */
    @PostMapping("goods/getGoodsList")
    public Result<Object> getGoods(@RequestParam(required = false, defaultValue = "-1") int soldOut,
                                   @RequestParam(required = false, defaultValue = "-1") int goodsTypeId,
                                   @RequestParam(required = false, defaultValue = "") String mainTitle,
                                   @RequestParam(required = false, defaultValue = "10") int limit,
                                   @RequestParam(required = false, defaultValue = "1") int page) {
        page = (page - 1) * limit;
        List<Goods> list = goodsService.getGoodsList(soldOut, goodsTypeId, mainTitle, limit, page);
        return Result.success(list);
    }

    /**
     * 获取销量前三商品
     *
     * @param num int 数目
     */
    @GetMapping("goods/getHotGoods")
    public Result<Object> getHotGoods(@RequestParam(required = false, defaultValue = "3") int num) {
        List<Goods> list = goodsService.getHotGoods(num);
        return Result.success(list);
    }

    /**
     * 获取商品详情
     *
     * @param goodsId * String 商品id
     */
    @PostMapping("goods/getGoodsDetail")
    public Result<Object> getGoodsDetail(@RequestParam String goodsId) {
        Goods goods = goodsService.getGoodsDetail(goodsId);
        List<GoodsSpecs> goodsSpecsList = goodsSpecsService.getGoodsSpecs(goodsId);
        if (goods == null) {
            return Result.error(CodeMsg.NOT_FIND_DATA);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsInfo", goods);
        map.put("goodsSpecs", goodsSpecsList);

        return Result.success(map);
    }
}
