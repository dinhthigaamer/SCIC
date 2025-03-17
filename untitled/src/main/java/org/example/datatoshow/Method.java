package org.example.datatoshow;

public class Method {
    private String name;
    private int number;
    private String backgroundColor;
    private String imageLink;

    public Method(String name, int number, String backgroundColor, String imageLink) {
        this.name = name;
        this.number = number;
        this.backgroundColor = backgroundColor;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getImageLink() {
        return imageLink;
    }
}
