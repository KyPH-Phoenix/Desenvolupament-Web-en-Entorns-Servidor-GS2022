package com.liceu.countries.model;

public class Language {
    private String name;
    private boolean official;
    private double percentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOficial(boolean oficial) {
        this.official = oficial;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
