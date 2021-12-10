package com.example.foodstorecomparison.FoodStoreComparison;

public class AllProductInfoDTO {
    private int id;
    private String name;
    public String ean;
    private int prismaPrice;
    private int selverPrice;
    private String productCategory;

    public AllProductInfoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public int getPrismaPrice() {
        return prismaPrice;
    }

    public void setPrismaPrice(int prismaPrice) {
        this.prismaPrice = prismaPrice;
    }

    public int getSelverPrice() {
        return selverPrice;
    }

    public void setSelverPrice(int selverPrice) {
        this.selverPrice = selverPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
