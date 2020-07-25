package tdc.edu.vn.projectquanlychamcongvsactivity.model;

import java.util.Date;

public class ChamCong {
    String MaCC,MaCN,NgayCC;

    public ChamCong() {
    }

    public ChamCong(String maCC, String maCN, String ngayCC) {
        MaCC = maCC;
        MaCN = maCN;
        NgayCC = ngayCC;
    }

    public String getMaCC() {
        return MaCC;
    }

    public void setMaCC(String maCC) {
        MaCC = maCC;
    }

    public String getMaCN() {
        return MaCN;
    }

    public void setMaCN(String maCN) {
        MaCN = maCN;
    }

    public String getNgayCC() {
        return NgayCC;
    }

    public void setNgayCC(String ngayCC) {
        NgayCC = ngayCC;
    }

    @Override
    public String toString() {
        return "ChamCong{" +
                "MaCC='" + MaCC + '\'' +
                ", MaCN='" + MaCN + '\'' +
                ", NgayCC='" + NgayCC + '\'' +
                '}';
    }
}
