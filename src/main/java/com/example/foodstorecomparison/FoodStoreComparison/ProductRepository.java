package com.example.foodstorecomparison.FoodStoreComparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int createCategory(String productCategory) {
        String sql = "INSERT INTO product_categories (product_category) VALUES (:productCategory)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productCategory", productCategory);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }

    public void createProduct(String name, String ean, Double prismaPrice, Double selverPrice, Integer prismaCategory, Integer selverCategory) {
        String sql = "INSERT INTO product_prices (product_name, ean, prisma_price, selver_price, prisma_category, selver_category) VALUES (:productName, :ean, :prismaPrice, :selverPrice, :prismaCategory, :selverCategory)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productName", name);
        paramMap.put("ean", ean);
        paramMap.put("prismaPrice", prismaPrice);
        paramMap.put("selverPrice", selverPrice);
        paramMap.put("prismaCategory", prismaCategory);
        paramMap.put("selverCategory", selverCategory);

        jdbcTemplate.update(sql, paramMap);
    }
    public int updateProduct(String ean, Double selverPrice, Integer selverCategory) {
        String sql = "UPDATE product_prices SET selver_price = :selverPrice, selver_category = :selverCategory WHERE ean = :ean";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ean", ean);
        paramMap.put("selverPrice", selverPrice);
        paramMap.put("selverCategory", selverCategory);
        return jdbcTemplate.update(sql, paramMap);
    }

}
