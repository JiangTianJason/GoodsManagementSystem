package com.example.springdemo.controller;

import com.example.springdemo.entity.Product;
import com.example.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public  String index(Model model, HttpServletResponse response,Integer pageNum){

        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort = Sort.by(Sort.Order.asc("productId"));  // 这里的"productId"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(pageNum - 1,6, sort); // （当前页， 每页记录数， 排序方式）
        Page<Product> list = productService.findMarker(pageable);

        model.addAttribute("pageInfo", list);
        response.addHeader("x-frame-options","SAMEORIGIN");  // 允许iframe
        return "product";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Integer id){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "productEdit";
    }

    @RequestMapping("/edit")
    public String edit(Product product){
        productService.saveProduct(product);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "productAdd";
    }

    @RequestMapping("/add")
    public String add(Product product){
        productService.saveProduct(product);
        return "redirect:/";
    }

    @RequestMapping("/toSearch")
    public String toSearch(){
        return "productSearch";
    }

    @RequestMapping("/search")
    public String search(Model model,HttpServletRequest request){
        String productName = request.getParameter("productName");
        Product  product = productService.findByName(productName);
        model.addAttribute("products",product);
        return "productSearchRes";
    }
}
