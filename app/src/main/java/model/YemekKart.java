package model;

public class YemekKart {
    private String yemekAdi;
    private int kalori;

    public YemekKart(String yemekAdi, int kalori) {
        this.yemekAdi = yemekAdi;
        this.kalori = kalori;
    }

    public String getYemekAdi() {
        return yemekAdi;
    }

    public int getKalori() {
        return kalori;
    }
}