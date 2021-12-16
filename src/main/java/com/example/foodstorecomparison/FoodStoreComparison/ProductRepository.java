package com.example.foodstorecomparison.FoodStoreComparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void userPick (String ean) {
        String sql = "INSERT INTO user_pick (ean) VALUES (:ean)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ean", ean);
        jdbcTemplate.update(sql, paramMap);
    }
//    public int countEan () {
//        String sql = "SELECT ean, COUNT(*) FROM user_pick GROUP BY ean";
//        Map<String, Object> paramMap = new HashMap<>();
//        return jdbcTemplate.query(sql, paramMap);
//    }
    public void createProduct(String name, String ean, Double prismaPrice, Double selverPrice, Integer prismaCategory, Integer selverCategory, String prismaImg, String selverImg) {
        String sql = "INSERT INTO product_prices (product_name, ean, prisma_price, selver_price, prisma_category, selver_category, prisma_img, selver_img) VALUES (:productName, :ean, :prismaPrice, :selverPrice, :prismaCategory, :selverCategory, :prismaImg, :selverImg)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("productName", name);
        paramMap.put("ean", ean);
        paramMap.put("prismaPrice", prismaPrice);
        paramMap.put("selverPrice", selverPrice);
        paramMap.put("prismaCategory", prismaCategory);
        paramMap.put("selverCategory", selverCategory);
        paramMap.put("prismaImg", prismaImg);
        paramMap.put("selverImg", selverImg);

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

    public AllProductInfoDTO getProductInfo(String ean) {
        String sql = "SELECT * FROM product_prices WHERE ean = :ean";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ean", ean);
        AllProductInfoDTO allProductInfoDTO = jdbcTemplate.queryForObject(sql, paramMap, new AllProductInfoDTORowMapper());

        return allProductInfoDTO;
    }
    public List<AllProductInfoDTO> getProductInfoByCategory(int ourCategory, int limit, int offset, String search) {
        String sql1 = "SELECT * FROM product_prices WHERE our_category = :ourCategory " +
                "AND prisma_price IS NOT NULL and selver_price IS NOT NULL ";
        String sql2 = "";
        String sql3 = "order by id limit :limit offset :offset";
        Map<String, Object> paramMap = new HashMap<>();
        if(search != null && !search.isBlank()) {
            sql2 += "and product_name ILIKE :search ";
            paramMap.put("search", "%" + search + "%");
        }
        String sql = sql1 + sql2 + sql3;
        paramMap.put("ourCategory", ourCategory);
        paramMap.put("limit", limit);
        paramMap.put("offset", offset);
        List<AllProductInfoDTO> result = jdbcTemplate.query(sql, paramMap, new AllProductInfoDTORowMapper());
        return result;

    }
    public int getPageCount (int ourCategory, String search) {
        String sql = "SELECT count(*) FROM product_prices where our_category = :ourCategory and " +
                "prisma_price IS NOT NULL and selver_price IS NOT NULL ";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ourCategory", ourCategory);
        if(search != null && !search.isBlank()) {
            sql += "and product_name ILIKE :search ";
            paramMap.put("search", "%" + search + "%");
        }
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void deleteOne(String ean) {
        String sql = "DELETE FROM user_pick WHERE ean = :ean";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ean", ean);
        jdbcTemplate.update(sql, paramMap);
    }
    public void deleteAll(){
        String sql = "DELETE FROM user_pick";
        Map<String, Object> paramMap = new HashMap<>();
        jdbcTemplate.update(sql, paramMap);
    }
    public Double sumPrismaPrice() {
        String sql = "SELECT SUM(prisma_price) FROM user_pick JOIN product_prices pp on user_pick.ean = pp.ean";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.queryForObject(sql, paramMap, Double.class);
    }
    public Double sumSelverPrice() {
        String sql = "SELECT SUM(selver_price) FROM user_pick JOIN product_prices pp on user_pick.ean = pp.ean";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.queryForObject(sql, paramMap, Double.class);
    }

    public List<ProductDTO> getUserPick() {
        String sql = "SELECT pp.* FROM user_pick JOIN product_prices pp on user_pick.ean = pp.ean";
        Map<String, Object> paramMap = new HashMap<>();
        List<ProductDTO> result = jdbcTemplate.query(sql, paramMap, new ProductDTORowMapper());
        return result;
    }

    public static class ProductDTORowMapper implements RowMapper<ProductDTO> {
        @Override
        public ProductDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            ProductDTO result = new ProductDTO();

            result.setName(resultSet.getString("product_name"));
            result.setEan(resultSet.getString("ean"));
            result.setPrismaPrice(resultSet.getDouble("prisma_price"));
            result.setSelverPrice(resultSet.getDouble("selver_price"));
            return result;
        }
    }
    public static class AllProductInfoDTORowMapper implements RowMapper<AllProductInfoDTO> {
        @Override
        public AllProductInfoDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            AllProductInfoDTO result = new AllProductInfoDTO();
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("product_name"));
            result.setEan(resultSet.getString("ean"));
            result.setPrismaPrice(resultSet.getDouble("prisma_price"));
            result.setSelverPrice(resultSet.getDouble("selver_price"));
            result.setPrismaCategory(resultSet.getInt("prisma_category"));
            result.setSelverCategory(resultSet.getInt("selver_category"));
            String prismaImage = resultSet.getString("prisma_img");
            String selverImage = resultSet.getString("selver_img");
            if (prismaImage == null) {
                result.setImage(selverImage);
            } else {
                result.setImage(prismaImage);
            }
            result.setOurCategory(resultSet.getInt("our_category"));

            return result;
        }
    }

}
