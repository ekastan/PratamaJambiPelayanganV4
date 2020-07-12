package com.example.pratamajambipelayangan;

/**
 * Created by putuguna on 21/09/17.
 */

public class PersyaratanModel {

    private String IdLayanan;
    private String NamaLayanan;
    private String Keterangan;
    private String Dokumen;
    private String HariKerja;
    private String Url;
    private String Penjelasan;
    private String URLFormulir;

    public PersyaratanModel(String IdLayanan, String NamaLayanan, String Keterangan, String Dokumen, String HariKerja, String Url, String Penjelasan, String URLFormulir) {
        this.IdLayanan = IdLayanan;
        this.NamaLayanan = NamaLayanan;
        this.Keterangan = Keterangan;
        this.Dokumen = Dokumen;
        this.HariKerja = HariKerja;
        this.Url = Url;
        this.Penjelasan = Penjelasan;
        this.URLFormulir = URLFormulir;
    }

    public PersyaratanModel() {
    }

    public String getIdLayanan() {
        return IdLayanan;
    }

    public void setIdLayanan(String idLayanan) {
        this.IdLayanan = idLayanan;
    }

    public String getNamaLayanan() {
        return NamaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.NamaLayanan = namaLayanan;
    }

    public String getKeterangan() {

        return Keterangan;
    }

    public void setKeterangan(String keterangan) {

        this.Keterangan = keterangan;
    }

    public String getHariKerja() {

        return HariKerja;
    }

    public void setHariKerja(String hari_kerja) {

        this.HariKerja = hari_kerja;
    }

    public String getDokumen() {

        return Dokumen;
    }

    public void setDokumen(String dokumen) {

        this.Dokumen = dokumen;
    }

    public String getUrl() {

        return Url;
    }

    public void setUrl(String url) {

        this.Url = url;
    }

    public String getPenjelasan() {

        return Penjelasan;
    }

    public void setPenjelasan(String penjelasan) {

        this.Penjelasan = penjelasan;
    }

    public String getURLFormulir() {

        return URLFormulir;
    }

    public void setURLFormulir(String url_formulir) {

        this.URLFormulir = url_formulir;
    }

    public String getIconFormulir() {

        return URLFormulir;
    }

    public void setIconFormulir(String url_formulir) {

        this.URLFormulir = url_formulir;
    }

}
