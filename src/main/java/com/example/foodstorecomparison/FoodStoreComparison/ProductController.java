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
    public void userPick(@RequestBody EanDTO eanDTO) {
        productService.userPick(eanDTO.getEan());
    }

    // localhost:8080/api/productController/productsByCategory?ourCategory=1&page=1
    // localhost:8080/api/productController/productsByCategory?ourCategory=1&page=2
    @GetMapping("api/productController/productsByCategory")
    public List<AllProductInfoDTO> getProductInfoByCategory(@RequestParam ("ourCategory") int ourCategory,
                                                            @RequestParam (value = "page", required = false) Integer page,
                                                            @RequestParam ("search") String search) {
        return productService.getProductInfoByCategory(ourCategory, page, search);
    }
    @GetMapping("api/productController/getProduct/{ean}")
    public AllProductInfoDTO getProductInfo(@PathVariable("ean") String ean) {
        return productService.getProductInfo(ean);
    }
    @GetMapping("api/productController/getUserPick")
    public List<ProductDTO> getUserPick() {
        return productService.getUserPick();
    }
    @GetMapping("api/productController/getPageCount/{ourCategory}")
    public int getPageCount (@PathVariable ("ourCategory") int ourCategory, String search) {
        return productService.getPageCount(ourCategory, search);
    }
    @GetMapping("api/productController/sumPrismaPrice")
    public Double sumPrismaPrice() {
        return productService.sumPrismaPrice();
    }
    @GetMapping("api/productController/sumSelverPrice")
    public Double sumSelverPrice() {
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
