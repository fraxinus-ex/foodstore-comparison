package com.example.foodstorecomparison.FoodStoreComparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void userPick(String ean) {
        productRepository.userPick(ean);
    }

    public List<AllProductInfoDTO> getProductInfoByCategory(int ourCategory) {
        return productRepository.getProductInfoByCategory(ourCategory);
    }

    public void createProduct(String name, String ean, Double prismaPrice, Double selverPrice, Integer prismaCategory, Integer selverCategory, String prismaImg, String selverImg) {
        productRepository.createProduct(name, ean, prismaPrice, selverPrice, prismaCategory, selverCategory, prismaImg, selverImg);
    }
    public void addSelverInfo(String name, String ean, Double selverPrice, Integer selverCategory, String selverImg) {
        int count = productRepository.updateProduct(ean, selverPrice, selverCategory);
        if(count == 0){
            productRepository.createProduct(name, ean, null, selverPrice, null, selverCategory, null, selverImg);
        }
    }
    public AllProductInfoDTO getProductInfo(String ean) {
        AllProductInfoDTO allProductInfoDTO = productRepository.getProductInfo(ean);
        return allProductInfoDTO;
    }
    public double sumPrismaPrice() {
        return productRepository.sumPrismaPrice();
    }
    public double sumSelverPrice() {
        return productRepository.sumSelverPrice();
    }

    public void deleteOne(String ean) {
        productRepository.deleteOne(ean);
    }
    public void deleteAll() {
        productRepository.deleteAll();
    }

    public List<ProductDTO> getUserPick() {
        return productRepository.getUserPick();
    }
}
