package com.example.foodstorecomparison.FoodStoreComparison;

public class AllProductInfoDTO {
    private int id;
    private String name;
    public String ean;
    private double prismaPrice;
    private double selverPrice;
    private int prismaCategory;
    private int selverCategory;
    private String prismaImg;
    private String selverImg;
    private int ourCategory;

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

    public int getPrismaCategory() {
        return prismaCategory;
    }

    public void setPrismaCategory(int prismaCategory) {
        this.prismaCategory = prismaCategory;
    }

    public int getSelverCategory() {
        return selverCategory;
    }

    public void setSelverCategory(int selverCategory) {
        this.selverCategory = selverCategory;
    }

    public String getPrismaImg() {
        return prismaImg;
    }

    public void setPrismaImg(String prismaImg) {
        this.prismaImg = prismaImg;
    }

    public String getSelverImg() {
        return selverImg;
    }

    public void setSelverImg(String selverImg) {
        this.selverImg = selverImg;
    }

    public int getOurCategory() {
        return ourCategory;
    }

    public void setOurCategory(int ourCategory) {
        this.ourCategory = ourCategory;
    }
}
