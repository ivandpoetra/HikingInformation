package com.finalproject.hikinginformation.model;

import java.io.Serializable;

public class ModelPeralatan implements Serializable {

    String strNamaPeralatan;
    String strImagePeralatan;
    String strTipePeralatan;
    String strDeskripsiPeralatan;
    String strTipsPeralatan;

    public String getStrNamaPeralatan() {
        return strNamaPeralatan;
    }

    public void setStrNamaPeralatan(String strNamaPeralatan) {
        this.strNamaPeralatan = strNamaPeralatan;
    }

    public String getStrImagePeralatan() {
        return strImagePeralatan;
    }

    public void setStrImagePeralatan(String strImagePeralatan) {
        this.strImagePeralatan = strImagePeralatan;
    }

    public String getStrTipePeralatan() {
        return strTipePeralatan;
    }

    public void setStrTipePeralatan(String strTipePeralatan) {
        this.strTipePeralatan = strTipePeralatan;
    }

    public String getStrDeskripsiPeralatan() {
        return strDeskripsiPeralatan;
    }

    public void setStrDeskripsiPeralatan(String strDeskripsiPeralatan) {
        this.strDeskripsiPeralatan = strDeskripsiPeralatan;
    }

    public String getStrTipsPeralatan() {
        return strTipsPeralatan;
    }

    public void setStrTipsPeralatan(String strTipsPeralatan) {
        this.strTipsPeralatan = strTipsPeralatan;
    }
}
