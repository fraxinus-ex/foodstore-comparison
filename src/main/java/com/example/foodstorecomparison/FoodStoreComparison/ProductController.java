package com.example.foodstorecomparison.FoodStoreComparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private Map<String, AllProductInfoDTO> accountBalanceMap = new HashMap<>();

    @Autowired
    private ProductService productService;

    @PostMapping("api/productController/userPick")
    public void userPick(@RequestBody ProductDTO product) {
        productService.userPick(product.getEan());
    }
    //api/productController/productsByCategory?ourCategory=1
    @GetMapping("api/productController/productsByCategory")
    public List<AllProductInfoDTO> getProductInfoByCategory(@RequestParam ("ourCategory") int ourCategory) {
        return productService.getProductInfoByCategory(ourCategory);
    }
    @GetMapping("api/productController/getProduct/{ean}")
    public AllProductInfoDTO getProductInfo(@PathVariable("ean") String ean) {
        return productService.getProductInfo(ean);
    }
    @GetMapping("api/productController/getUserPick")
    public List<ProductDTO> getUserPick() {
        return productService.getUserPick();
    }
    @GetMapping("api/productController/sumPrismaPrice")
    public double sumPrismaPrice() {
        return productService.sumPrismaPrice();
    }
    @GetMapping("api/productController/sumSelverPrice")
    public double sumSelverPrice() {
        return productService.sumSelverPrice();
    }

    @DeleteMapping("api/productController/deleteOne/{ean}")
    public void deleteOne(@PathVariable("ean") String ean) {
        productService.deleteOne(ean);
    }
    @DeleteMapping("api/productController/deleteAll")
    public void deleteAll() {
        productService.deleteAll();
    }
}
