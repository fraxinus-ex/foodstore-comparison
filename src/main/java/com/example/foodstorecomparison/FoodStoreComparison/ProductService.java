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
//    public int countEan () {
//        return productRepository.countEan();
//    }

    public List<AllProductInfoDTO> getProductInfoByCategory(int ourCategory, Integer page, String search) {
        if(page == null) {
            page = 1;
        }
        int limit = 50;
        int offset = (page - 1) * limit;

        return productRepository.getProductInfoByCategory(ourCategory, limit, offset, search);
    }
    public int getPageCount (int ourCategory) {
        int pageCount = productRepository.getPageCount(ourCategory);
        int pages = 0;
        if(pageCount % 50 == 0) {
            pages = pageCount / 50;
        } else if (pageCount % 50 != 0) {
            pages = pageCount / 50 + 1;
        }
        return pages;
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
    public Double sumPrismaPrice() {
        return productRepository.sumPrismaPrice();
    }
    public Double sumSelverPrice() {
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
