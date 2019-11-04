package id.faiz.www.cobadatabase;

public class Pegawai {
    private int id;
    private String nama;
    private int nip;

    public String getTanggallahir() {
        return tanggallahir;
    }

    public void setTanggallahir(String tanggallahir) {
        this.tanggallahir = tanggallahir;
    }

    public String getPerkawinan() {
        return perkawinan;
    }

    public void setPerkawinan(String perkawinan) {
        this.perkawinan = perkawinan;
    }

    private String tanggallahir;
    private String perkawinan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

}
