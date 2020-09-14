package com.example.todo_assignment;

public class MyDoes {
    String titledoes;
    String datadoes;
    String descdoes;
    String keydoes;

    public MyDoes(String titledoes, String datadoes, String descdoes, String keydoes)
    {
        this.titledoes = titledoes;
        this.datadoes = datadoes;
        this.descdoes = descdoes;
        this.keydoes = keydoes;
    }

    public String getTitledoes() {
        return titledoes;
    }

    public void setTitledoes(String titledoes) {
        this.titledoes = titledoes;
    }

    public MyDoes() {}

    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
    }

    public String getDatadoes() {
        return datadoes;
    }

    public void setDatadoes(String datadoes) {
        this.datadoes = datadoes;
    }

    public String getDescdoes() {
        return descdoes;
    }

    public void setDescdoes(String descdoes) {
        this.descdoes = descdoes;
    }

}
