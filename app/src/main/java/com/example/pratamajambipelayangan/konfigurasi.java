package com.example.pratamajambipelayangan;

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan

    public static final String URL_GET_EMP = "https://kpppratamakedaton.id/kedaton/php/tampilKuisioner.php?id=";
    public static final String URL_GET_DATA_WP = "https://kpppratamakedaton.id/kedaton/php/tampilDataWP.php?id=";
    public static final String URL_GET_BPS = "https://kpppratamakedaton.id/kedaton/php/tampilStatusBPS.php?id=";
    public static final String URL_CHECK_NPWP = "https://kpppratamakedaton.id/kedaton/php/check_npwp.php?id=";
    public static final String URL_GET_TUTORIAL = "https://kpppratamakedaton.id/kedaton/php/tampilTutorial.php";
    public static final String URL_GET_NOTIFIKASI = "https://kpppratamakedaton.id/kedaton/php/tampilNotifikasi.php";
    public static final String URL_GET_LAYANAN_DETAIL = "https://kpppratamakedaton.id/kedaton/php/tampilLayananDetail.php?id_layan=";
    public static final String URL_GET_LAYANAN_DOKUMEN = "https://kpppratamakedaton.id/kedaton/php/tampilLayananDokumen.php?id_layanan=";
    public static final String URL_GET_LAYANAN = "https://kpppratamakedaton.id/kedaton/php/tampilLayanan.php";

    //JSON Tags
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_NAMA_WP = "nama_wp"; //Nama WP
    public static final String TAG_ALAMAT_WP = "alamat_wp";
    public static final String TAG_KLU = "klu";
    public static final String TAG_NAMA_AR = "nama_ar"; //Nama AR
    public static final String TAG_SEKSI = "seksi"; //Seksi
    public static final String TAG_FOTO = "foto";
    public static final String TAG_HITUNG = "hitung";
    public static final String TAG_HP = "hp";
    public static final String TAG_TELPON = "telpon";
    public static final String TAG_TUNGGAKAN = "tunggakan";

    //Tarik data MFWP
    public static final String EMP_ID = "emp_id"; // untuk NPWP

    //Tutorial
    public static final String TAG_URL_YOUTUBE = "url";
    public static final String TAG_PENJELASAN = "penjelasan";
    public static final String TAG_DETAIL = "detail";

    //Notifikasi
    public static final String EMP_ID_NOTIFIKASI = "id_notifikasi";
    public static final String TAG_NAMA_NOTIFIKASI = "nama_notifikasi";
    public static final String TAG_DETAIL_NOTIFIKASI = "detail_notifikasi";
    public static final String TAG_KETERANGAN = "keterangan";

    //Kuesioner
    public static final String EMP_ID_KUESIONER = "emp_id_bps";
    public static final String TAG_URL_KUESIONER = "name";

    //BPS
    public static final String TAG_NAMA = "name";
    public static final String EMP_ID_BPS = "emp_id_bps";

    //Tarik data Layanan
    public static final String EMP_ID_LAYANAN = "id_layananan";
    public static final String TAG_NAMA_LAYANAN = "nama_layanan";
    public static final String TAG_HARI_KERJA = "hari_kerja";
    public static final String TAG_URL_FORMULIR = "url_formulir";
    public static final String TAG_ICON_FORMULIR = "icon_formulir";
}
