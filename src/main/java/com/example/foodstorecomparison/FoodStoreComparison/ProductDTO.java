package com.example.foodstorecomparison.FoodStoreComparison;

public class ProductDTO {
    private String name;
    public String ean;
    private double prismaPrice;
    private double selverPrice;

    public ProductDTO() {
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

    public double getPrismaPrice() {
        return prismaPrice;
    }

    public void setPrismaPrice(double prismaPrice) {
        this.prismaPrice = prismaPrice;
    }

    public double getSelverPrice() {
        return selverPrice;
    }

    public void setSelverPrice(double selverPrice) {
        this.selverPrice = selverPrice;
    }
}
