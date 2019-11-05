package id.faiz.www.cobadatabase;

public class Telepon {
    private String id;
    private String kategori;
    private String nomor;

    public Telepon(String kategori, String nomor) {
        this.kategori = kategori;
        this.nomor = nomor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }
}
