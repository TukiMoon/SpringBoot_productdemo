package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.mapper.ItemMapper;

@Service //サービスクラスはビジネスロジックを担当
public class ItemService {

  @Autowired //DIする（インターフェースの実装を注入する）
  private ItemMapper itemMapper;

  public List<Item> findAll() {
    return itemMapper.findAll();
  }

  public List<Item> findByProductName(String productname,BigDecimal lowerlimits,BigDecimal upperlimits,String vendorname) { //パラメタを受け取る
    return itemMapper.findByProductName(productname,lowerlimits,upperlimits,vendorname);       //ItemMapper.javaにパラメータを渡す
  }

  public int getTotalPrice() {
    Integer totalPrice = itemMapper.sumAll();
    return totalPrice != null ? totalPrice : 0; // nullの場合は0を返す
}
  //@Transactional
  public Item findOne(Long id) {
    return itemMapper.findOne(id);
  }

  //@Transactional
  public void save(Item item) {
    itemMapper.save(item);
  }

  //@Transactional
  public void update(Item item) {
    itemMapper.update(item);
  }

  //@Transactional
  public void delete(Long id) {
    itemMapper.delete(id);
  }
}