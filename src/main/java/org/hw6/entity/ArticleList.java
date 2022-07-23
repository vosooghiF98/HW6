package org.hw6.entity;

import java.util.Arrays;

public class ArticleList {
    private Article [] articles = new Article[1000];
    private int index = 0;

    public int size(){
        return index;
    }

    public void add(Article article){
        if (index == articles.length){
            articles = Arrays.copyOf(articles,articles.length * 2);
        }
        articles [index] = article;
        index++;
    }

    public Article get(int index){
        if (index >= size()){
            System.out.println("index not exist!");
            return null;
        }
        return articles[index];
    }

    public boolean isEmpty(){
        return index == 0;
    }

    public void remove(int index){
        if (index >= size()) {
            System.out.println("index not exist!");
            return;
        }
        for (int i = index; i < this.index ; i++) {
            articles [i] = articles[i+1];
        }
        this.index--;
    }

    public void remove(Article article){

    }

    public boolean contains(Article article){
        for (int i = 0; i < size(); i++) {
            if (article.getId() == articles[i].getId()
                    && article.getTitle().equals(articles[i].getTitle())
                    && article.getBrief().equals(articles[i].getBrief())){
                return true;
            }
        }
        return false;
    }



}
