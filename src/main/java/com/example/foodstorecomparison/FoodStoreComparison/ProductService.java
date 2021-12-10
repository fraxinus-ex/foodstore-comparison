package com.example.foodstorecomparison.FoodStoreComparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public int createCategory(String productCategory) {
        return productRepository.createCategory(productCategory);
    }

    public void createProduct(String name, String ean, Double prismaPrice, Double selverPrice, Integer prismaCategory, Integer selverCategory) {
        productRepository.createProduct(name, ean, prismaPrice, selverPrice, prismaCategory, selverCategory);
    }
    //kui kirje on baasis, siis update, kui mitte, siis insert
    public void addSelverInfo(String name, String ean, Double selverPrice, Integer selverCategory) {
        int count = productRepository.updateProduct(ean, selverPrice, selverCategory);
        if(count == 0){
            productRepository.createProduct(name, ean, null, selverPrice, null, selverCategory);
        }
    }
}
