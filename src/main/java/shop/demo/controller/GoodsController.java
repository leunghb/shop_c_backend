package shop.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.*;
import shop.demo.service.EvaluateService;
import shop.demo.service.GoodsService;
import shop.demo.service.GoodsSpecsService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsSpecsService goodsSpecsService;
    @Autowired
    private EvaluateService evaluateService;

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
     * 删除商品分类
     *
     * @param id
     */
    @PostMapping("goods/delGoodsType")
    public Result<Object> delGoodsType(@RequestParam int id) {
        System.out.println(id);
        int count = goodsService.hasGoodsOfType(id);
        if (count != 0) {
            return Result.error(CodeMsg.FAIL, "该分类存在上架商品");
        }
        count = goodsService.delGoodsType(id);
        if (count == 0) {
            return Result.error(CodeMsg.FAIL, "删除失败");
        }
        return Result.success(CodeMsg.SUCCESS, "删除成功");
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
        int count = goodsService.getCountWhereSomething(soldOut, goodsTypeId, mainTitle, limit, page);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("list", list);
        hashMap.put("count", count);
        return Result.success(hashMap);
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
        int evaluateCount = evaluateService.goodsEvaluateListCount(goodsId);
        if (goods == null) {
            return Result.error(CodeMsg.NOT_FIND_DATA);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsInfo", goods);
        map.put("goodsSpecs", goodsSpecsList);
        map.put("evaluateCount", evaluateCount);

        return Result.success(map);
    }

    /**
     * 添加商品分类
     *
     * @param title soldOut priority icon
     */
    @PostMapping("goods/addGoodsType")
    public Result<Object> addGoodsType(@RequestParam String title,
                                       @RequestParam int soldOut,
                                       @RequestParam int priority,
                                       @RequestParam String icon) {
        int count = goodsService.addGoodsType(title, soldOut, priority, icon);
        if (count == 0) {
            return Result.error(CodeMsg.FAIL, "添加失败");
        }
        return Result.success(CodeMsg.SUCCESS, "添加成功");
    }

    /**
     * 修改商品分类
     *
     * @param id title soldOut priority icon
     */
    @PostMapping("goods/putGoodsType")
    public Result<Object> putGoodsType(@RequestParam int id,
                                       @RequestParam String title,
                                       @RequestParam int soldOut,
                                       @RequestParam int priority,
                                       @RequestParam String icon) {
        int count = goodsService.putGoodsType(id, title, soldOut, priority, icon);
        if (count == 0) {
            return Result.error(CodeMsg.FAIL, "修改失败");
        }
        return Result.success(CodeMsg.SUCCESS, "修改成功");
    }

    @PostMapping("goods/addGoods")
    public Result<Object> addGoods(@RequestParam String goodsId,
                                   @RequestParam int goodsTypeId,
                                   @RequestParam BigDecimal originalPrice,
                                   @RequestParam BigDecimal discountPrice,
                                   @RequestParam(required = false) String cover,
                                   @RequestParam String mainTitle,
                                   @RequestParam(required = false) String subTitle,
                                   @RequestParam int soldOut,
                                   @RequestParam int stock,
                                   @RequestParam(required = false) String content,
                                   @RequestParam(required = false) String specList,
                                   @RequestParam(required = false) String attr) {
        //新增、修改goods表
        int row = goodsService.addGoods(goodsId, goodsTypeId, originalPrice, discountPrice, cover, mainTitle, subTitle, soldOut, stock, content, specList);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL);
        }
        //删除goods_specs表旧数据
        goodsSpecsService.delGoodsSpecs(goodsId);
        //新增goods_specs
        JSONArray jsonArray = JSONArray.parseArray(attr);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.get("price"));
            String specs = (String) jsonObject.get("id");
            int specStock = (int) jsonObject.get("stock");
            Integer price = (Integer) jsonObject.get("price");
            BigDecimal _price = new BigDecimal(price);
            goodsSpecsService.addGoodsSpecs(goodsId, specs, specStock, _price);
        }
        return Result.success();
    }

    @PostMapping("goods/delGoods")
    public Result<Object> delGoods(@RequestParam String goodsId) {
        int row = goodsService.delGoods(goodsId);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "删除失败");
        }
        return Result.success(CodeMsg.SUCCESS, "删除成功");
    }
}
