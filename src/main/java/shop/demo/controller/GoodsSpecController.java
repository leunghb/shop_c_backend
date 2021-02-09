package shop.demo.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.AttrKey;
import shop.demo.entity.AttrValue;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.service.GoodsSpecsService;

import java.lang.reflect.Array;
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
        int count = goodsSpecsService.addAttrValue(attrKeyId, name);
        if (count == 0) {
            return Result.error(CodeMsg.FAIL, "添加失败");
        }
        return Result.success(CodeMsg.SUCCESS, "添加成功");
    }

    @PostMapping("spec/delAttrValue")
    public Result<Object> delAttrValue(@RequestParam int id) {
        int count = goodsSpecsService.delAttrValue(id);
        if (count == 0) {
            return Result.error(CodeMsg.FAIL, "删除失败");
        }
        return Result.success(CodeMsg.SUCCESS, "删除成功");
    }
}
