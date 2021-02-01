package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Goods;
import shop.demo.entity.GoodsType;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<GoodsType> getGoodsType(int soldOut);

    int delGoodsType(int id);

    List<Goods> getGoodsList(int soldOut, int goodsTypeId, String mainTitle, int limit, int page);

    int getCountWhereSomething(int soldOut, int goodsTypeId, String mainTitle, int limit, int page);

    List<Goods> getHotGoods(int num);

    Goods getGoodsDetail(String goodsId);

    int putGoodsStock(String goodsId, int stock);

    int putGoodsSalesVolume(String goodsId, int number);

    int hasGoodsOfType(int id);

    int addGoodsType(String title, int soldOut, int priority, String icon);

    int putGoodsType(int id, String title, int soldOut, int priority, String icon);
}
