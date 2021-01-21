package com.example.lista13;

public class COVIDData {
    private String country;
    private double cases;
    private double active;
    private double casesPerOneMillion;
    private double testsPerOneMillion;

    public COVIDData(String country_name, double cases, double active, double casesPerOneMillion, double testsPerOneMillion) {
        this.country= country_name;
        this.cases = cases;
        this.active = active;
        this.casesPerOneMillion = casesPerOneMillion;
        this.testsPerOneMillion = testsPerOneMillion;
    }

    public COVIDData() {
    }

    @Override
    public String toString() {
        return "COVIDData{" +
                "country='" + country + '\'' +
                ", cases=" + cases +
                ", active=" + active +
                ", casesPerOneMillion=" + casesPerOneMillion +
                ", testsPerOneMillion=" + testsPerOneMillion +
                '}';
    }

    public String getCountry_name() {
        return country;
    }

    public void setCountry_name(String country_name) {
        this.country = country_name;
    }

    public double getCases() {
        return cases;
    }

    public void setCases(double cases) {
        this.cases = cases;
    }

    public double getActive() {
        return active;
    }

    public void setActive(double active) {
        this.active = active;
    }

    public double getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(double casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public double getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(double testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }
}
