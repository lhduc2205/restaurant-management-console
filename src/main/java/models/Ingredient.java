package models;

public class Ingredient {
    private int id;
    private String name;
    private String provider;

    public Ingredient(){

    }

    public Ingredient(String name, String provider) {
        this.name = name;
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\": " + name +
                ", \"provider\": " + provider +
                '}';
    }
}
