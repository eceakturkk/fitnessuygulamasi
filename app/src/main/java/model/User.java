package model;

public class User {
    private String username;
    private String email;
    private String password;
    private String boy;
    private String kilo;
    private String cinsiyet;
    private String yas;

    public User(String username, String email, String password, String boy, String kilo, String cinsiyet, String yas) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.boy = boy;
        this.kilo = kilo;
        this.cinsiyet = cinsiyet;
        this.yas = yas;
    }

    public User() { }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBoy() {
        return boy;
    }

    public void setBoy(String boy) {
        this.boy = boy;
    }

    public String getKilo() {
        return kilo;
    }

    public void setKilo(String kilo) {
        this.kilo = kilo;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }
}