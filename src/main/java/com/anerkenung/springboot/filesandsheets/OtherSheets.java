package com.anerkenung.springboot.filesandsheets;

public class OtherSheets {
    private String LfdNo;
    private String pool;
    private String prüfNr;
    private String prüfungName;
    private String describe;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    private String PrüfungCredit;


    public String getPrüfungName() {
        return prüfungName;
    }

    public void setPrüfungName(String prüfungName) {
        this.prüfungName = prüfungName;
    }

    public String getPrüfungCredit() {
        return PrüfungCredit;
    }

    public void setPrüfungCredit(String prüfungCredit) {
        PrüfungCredit = prüfungCredit;
    }

    public String getLfdNo() {
        return LfdNo;
    }

    public void setLfdNo(String lfdNo) {
        LfdNo = lfdNo;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getPrüfNr() {
        return prüfNr;
    }

    public void setPrüfNr(String prüfNr) {
        this.prüfNr = prüfNr;
    }


    @Override
    public String toString() {
        return "OtherSheets{" +
                "LfdNo='" + LfdNo + '\'' +
                ", pool='" + pool + '\'' +
                ", prüfNr='" + prüfNr + '\'' +
                ", prüfungName='" + prüfungName + '\'' +
                ", describe='" + describe + '\'' +
                ", PrüfungCredit='" + PrüfungCredit + '\'' +
                '}';
    }
}
