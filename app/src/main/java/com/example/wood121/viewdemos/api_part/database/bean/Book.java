package com.example.wood121.viewdemos.api_part.database.bean;

import java.io.Serializable;

/**
 * <p>描述：(这里用一句话描述这个类的作用)</p>
 * 作者： wood121<br>
 * 日期： 2018/10/31 19:05<br>
 * 版本： v2.0<br>
 */
public class Book implements Serializable {
    /**
     * author
     * price
     * pages
     * name t
     */
    private String author;
    private double price;
    private int pages;
    private String name;

    public Book() {

    }

    public Book(String name, String author, int pages, double price) {
        this.author = author;
        this.price = price;
        this.pages = pages;
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                '}';
    }
}
