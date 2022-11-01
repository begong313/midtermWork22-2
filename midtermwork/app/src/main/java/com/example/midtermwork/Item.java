package com.example.midtermwork;

public class Item {

    private String title;
    private String contents;
    private int itemImage;

    public Item(String title, String contents){
        this.title = title;
        this.contents = contents;

    }
    public Item(String title, String contents, int itemImage){
        this(title,contents);
        this.itemImage = itemImage;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getItemImage() {
        return this.itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}