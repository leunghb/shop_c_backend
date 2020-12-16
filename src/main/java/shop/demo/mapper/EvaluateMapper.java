package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Evaluate;

import java.util.List;

@Mapper
public interface EvaluateMapper {
    int addEvaluate(String account, String orderId, String goodsId, Integer goodsSpecsId, Integer rate, String comment);

    List<Evaluate> selEvaluate(String account, String orderId, Integer goodsSpecsId, String goodsId);

    List<Object> selGoodsEvaluateList(String goodsId, Integer type);

    int goodsEvaluateListCount(String goodsId);
}
