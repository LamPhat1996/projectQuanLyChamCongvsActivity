package tdc.edu.vn.projectquanlychamcongvsactivity.model;

public class SanPham {
    String MaSP,TenSP,DonGia;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, String donGia) {
        MaSP = maSP;
        TenSP = tenSP;
        DonGia = donGia;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String donGia) {
        DonGia = donGia;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "MaSP='" + MaSP + '\'' +
                ", TenSP='" + TenSP + '\'' +
                ", DonGia='" + DonGia + '\'' +
                '}';
    }
}
