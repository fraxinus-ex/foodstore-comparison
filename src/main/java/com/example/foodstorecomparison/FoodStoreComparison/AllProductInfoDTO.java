package com.example.foodstorecomparison.FoodStoreComparison;

public class AllProductInfoDTO {
    private int id;
    private String name;
    public String ean;
    private double prismaPrice;
    private double selverPrice;
    private int prismaCategory;
    private int selverCategory;
    private String image;
    private int ourCategory;
    private boolean alertVariable;
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public boolean isAlertVariable() {
        return alertVariable;
    }

    public void setAlertVariable(boolean alertVariable) {
        this.alertVariable = alertVariable;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOurCategory() {
        return ourCategory;
    }

    public void setOurCategory(int ourCategory) {
        this.ourCategory = ourCategory;
    }
}
