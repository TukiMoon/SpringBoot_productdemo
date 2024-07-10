package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Item;
import com.example.demo.service.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class ItemController {
  @Autowired
  private ItemService itemService;

  @GetMapping("/items")
  public String index(Model model) {
    System.out.println("商品一覧画面を開く");
    List<Item> items = itemService.findAll();
    model.addAttribute("items", items);

    //合計１
    int totalPrice = itemService.getTotalPrice();
    model.addAttribute("totalPrice", totalPrice);
    //合計２
    int totalPrice2 = items.stream().mapToInt(item -> (int) item.getPrice()).sum();
    model.addAttribute("totalPrice2", totalPrice2);
    //件数
    int itemCount = items.size();
    model.addAttribute("itemCount", itemCount);
    //平均の金額
    float averagePrice = itemCount > 0 ? totalPrice2 / itemCount : 0;
    model.addAttribute("averagePrice", averagePrice);

    return "index"; //①のURLで、index.htmlを呼びだす。
  }
  @GetMapping("/items/new")  //② /items/newのget
  public String newItem(@ModelAttribute("item") Item item, Model model) {
    System.out.println("新規登録画面を開く");
    return "new";     //②のURLでnew.htmlを呼びだす。
  }
  @PostMapping("/items/new")
  public String create(@ModelAttribute("item") @Validated Item item, BindingResult result, Model model) {
    System.out.println("登録する");
    if (result.hasErrors()) {
      System.out.println("NG");
      return "new";
    } else {
      System.out.println("OK");
      itemService.save(item);
      return "redirect:/items";
    }
  }
  @GetMapping("/items/{id}")
  public String show(@PathVariable Long id, Model model) {
    System.out.println("詳細を開く");
    model.addAttribute("item", itemService.findOne(id));
    return "show";
  }
  @GetMapping("/items/{id}/edit")
  public String edit(@PathVariable Long id, @ModelAttribute("item") Item item, Model model) {
    System.out.println("変更ボタン：修正画面を開く");
    model.addAttribute("item", itemService.findOne(id));
    return "edit";
  }

  @PutMapping("/items/{id}")
  public String update(@PathVariable Long id, @ModelAttribute("item") @Validated Item item, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("item", item);
      return "edit";
    } else {
      item.setId(id);
      itemService.update(item);
      return "redirect:/items";
    }
  }
  @DeleteMapping("/items/{id}")
  public String delete(@PathVariable Long id) {
    itemService.delete(id);
    return "redirect:/items";
  }

  @PostMapping("/items/search")
  public String searchItems(Model model,@RequestParam String productname,@RequestParam BigDecimal price1,@RequestParam BigDecimal price2,@RequestParam String vendorname){
    System.out.println("検索結果" +"「"+ productname +"」「"+ price1 +"」「"+ price2+"」「"+vendorname+"」");
    List<Item> items = itemService.findByProductName(productname,price1,price2,vendorname);
    model.addAttribute("items", items);

    //合計１
    int totalPrice = itemService.getTotalPrice();
    model.addAttribute("totalPrice", totalPrice);
    //合計２
    int totalPrice2 = items.stream().mapToInt(item -> (int) item.getPrice()).sum();
    model.addAttribute("totalPrice2", totalPrice2);
    //件数
    int itemCount = items.size();
    model.addAttribute("itemCount", itemCount);
    //平均の金額
    float averagePrice = itemCount > 0 ? totalPrice2 / itemCount : 0;
    model.addAttribute("averagePrice", averagePrice);
    return "search";
  }
}