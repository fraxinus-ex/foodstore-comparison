package com.example.foodstorecomparison.FoodStoreComparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    private Map<String, AllProductInfoDTO> accountBalanceMap = new HashMap<>();

    @Autowired
    private ProductService productService;

    @PostMapping("api/productController/createCategory")
    public int createCategory(@RequestBody CategoryDTO category) {
        return productService.createCategory(category.getProductCategory());
    }
//    @PutMapping("api/productController/updateProduct")
//    public void updateProduct(@PathVariable("ean") String ean, @PathVariable("selverPrice") double selverPrice) {
//        productService.updateProduct(ean, selverPrice);
//    }
//    @GetMapping("api/productController/getEan")
//    public void getEan(@PathVariable)


}
