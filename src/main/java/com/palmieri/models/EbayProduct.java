package com.palmieri.models;

public class EbayProduct {
    private String name;
    private String desc;
    private String price;
    private String imgHref;

    public EbayProduct(String name, String desc, String price, String imgHref) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.imgHref = imgHref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgHref() {
        return imgHref;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }

    public void print() {
        System.out.println("Name : " + name);
        System.out.println("Subtitle : " + desc);
        System.out.println("Price : " + price);
        System.out.println("Href img : " + imgHref + "\n");
    }
}
