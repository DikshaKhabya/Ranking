package com.example.ranking;



public class Item  {
    private String name;
    private Integer marks;
    private Integer rank;

    public Item() {
        name = "";
        marks = 0;
        rank = -1;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }



}

