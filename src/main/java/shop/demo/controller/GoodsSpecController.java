package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.*;
import shop.demo.service.GoodsSpecsService;

import java.util.*;

@RestController
public class GoodsSpecController {
    @Autowired
    protected GoodsSpecsService goodsSpecsService;

    @PostMapping("spec/getAttr")
    public Result<Object> getAttrKey(@RequestParam(required = false, defaultValue = "0") int goodsTypeId) {
        List<Map<String, Object>> attrKeyList = goodsSpecsService.getAttrKey(goodsTypeId);
        List<Object> list = new ArrayList<>();
        for (Map<String, Object> map : attrKeyList) {
            HashMap<String, Object> hashMap = new HashMap<>();
            int id = (int) map.get("id");
            List<AttrValue> attrValue = goodsSpecsService.getAttrValue(id);
            hashMap.put("keyId", id);
            hashMap.put("keyName", map.get("name"));
            hashMap.put("goodsTypeName", map.get("title"));
            hashMap.put("values", attrValue);
            list.add(hashMap);
        }
        return Result.success(list);
    }

    @PostMapping("spec/addAttrKey")
    public Result<Object> addAttrKey(@RequestParam int goodsTypeId,
                                     @RequestParam String name) {
        int count = goodsSpecsService.addAttrKey(goodsTypeId, name);
        if (count == 0) {
            return Result.error(CodeMsg.FAIL, "添加失败");
        }
        return Result.success(CodeMsg.SUCCESS, "添加成功");
    }

    @PostMapping("spec/addAttrValue")
    public Result<Object> addAttrValue(@RequestParam int attrKeyId,
                                       @RequestParam String name) {
        AttrValue attrValue = new AttrValue();
        attrValue.setAttrKeyId(attrKeyId);
        attrValue.setName(name);
        goodsSpecsService.addAttrValue(attrValue);
        Long id = attrValue.getId();
        if (!(id > 0)) {
            return Result.error(CodeMsg.FAIL, "添加失败");
        }
        return Result.success(id);
    }

    @PostMapping("spec/delAttrValue")
    public Result<Object> delAttrValue(@RequestParam int id) {
        int count = goodsSpecsService.delAttrValue(id);
        if (count == 0) {
            return Result.error(CodeMsg.FAIL, "删除失败");
        }
        return Result.success(CodeMsg.SUCCESS, "删除成功");
    }

    @PostMapping("spec/delAttrKey")
    public Result<Object> delAttrKey(@RequestParam int id) {
        int count = goodsSpecsService.delAttrKey(id);
        if (count == 0) {
            return Result.error(CodeMsg.FAIL, "删除失败");
        }
        return Result.success(CodeMsg.SUCCESS, "删除成功");
    }

    @PostMapping("spec/getAttrKeyByGoodsType")
    public Result<Object> getAttrKeyByGoodsType(@RequestParam int goodsTypeId) {
        List<AttrKey> list = goodsSpecsService.getAttrKeyByGoodsType(goodsTypeId);
        if (list.size() == 0) {
            return Result.error(CodeMsg.NOT_FIND_DATA);
        }
        return Result.success(list);
    }

    @PostMapping("spec/getAttrValueByKeyId")
    public Result<Object> getAttrValueByKeyId(@RequestParam int attrKeyId) {
        System.out.println(attrKeyId);
        List<AttrValue> list = goodsSpecsService.getAttrValueByKeyId(attrKeyId);
        if (list.size() == 0) {
            return Result.error(CodeMsg.NOT_FIND_DATA);
        }
        return Result.success(list);
    }
}
