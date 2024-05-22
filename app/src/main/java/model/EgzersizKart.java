package model;

public class EgzersizKart {
    private String egzersizId; // Unique identifier for the exercise
    private String egzersizAdi;
    private int yakilanKalori;

    public EgzersizKart(String egzersizId, String egzersizAdi, int yakilanKalori) {
        this.egzersizId = egzersizId;
        this.egzersizAdi = egzersizAdi;
        this.yakilanKalori = yakilanKalori;
    }

    public String getEgzersizId() {
        return egzersizId;
    }

    public String getEgzersizAdi() {
        return egzersizAdi;
    }

    public int getYakilanKalori() {
        return yakilanKalori;
    }
}