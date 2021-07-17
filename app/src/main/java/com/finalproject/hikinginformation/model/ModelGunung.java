package com.finalproject.hikinginformation.model;

import java.io.Serializable;

public class ModelGunung implements Serializable {

    String strNamaGunung;
    String strImageGunung;
    String strLokasiGunung;
    String strDeskripsi;
    String strJalurPendakian;
    String strInfoGunung;
    double strLat;
    double strLong;

    public String getStrNamaGunung() {
        return strNamaGunung;
    }

    public void setStrNamaGunung(String strNamaGunung) {
        this.strNamaGunung = strNamaGunung;
    }

    public String getStrImageGunung() {
        return strImageGunung;
    }

    public void setStrImageGunung(String strImageGunung) {
        this.strImageGunung = strImageGunung;
    }

    public String getStrLokasiGunung() {
        return strLokasiGunung;
    }

    public void setStrLokasiGunung(String strLokasiGunung) {
        this.strLokasiGunung = strLokasiGunung;
    }

    public String getStrDeskripsi() {
        return strDeskripsi;
    }

    public void setStrDeskripsi(String strDeskripsi) {
        this.strDeskripsi = strDeskripsi;
    }

    public String getStrJalurPendakian() {
        return strJalurPendakian;
    }

    public void setStrJalurPendakian(String strJalurPendakian) {
        this.strJalurPendakian = strJalurPendakian;
    }

    public String getStrInfoGunung() {
        return strInfoGunung;
    }

    public void setStrInfoGunung(String strInfoGunung) {
        this.strInfoGunung = strInfoGunung;
    }

    public double getStrLat() {
        return strLat;
    }

    public void setStrLat(double strLat) {
        this.strLat = strLat;
    }

    public double getStrLong() {
        return strLong;
    }

    public void setStrLong(double strLong) {
        this.strLong = strLong;
    }

}
