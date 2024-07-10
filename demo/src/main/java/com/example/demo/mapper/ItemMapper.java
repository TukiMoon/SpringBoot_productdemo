package com.example.demo.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.domain.Item; //Itemクラスとpath

@Mapper
public interface ItemMapper {
  List<Item> findAll();
  List<Item> findByProductName(String productname,BigDecimal lowerlimits,BigDecimal upperlimits,String vendorname);
  Integer sumAll();
  Item findOne(Long id);
  void save(Item item);
  void update(Item item);
  void delete(Long id);
}