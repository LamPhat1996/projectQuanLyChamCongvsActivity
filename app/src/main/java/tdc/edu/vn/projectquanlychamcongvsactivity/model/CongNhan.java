package tdc.edu.vn.projectquanlychamcongvsactivity.model;

public class CongNhan {
    String MaCN,HoCN,TenCN,PhanXuong;

    public CongNhan() {
    }

    public CongNhan(String maCN, String hoCN, String tenCN, String phanXuong) {
        MaCN = maCN;
        HoCN = hoCN;
        TenCN = tenCN;
        PhanXuong = phanXuong;
    }

    public String getMaCN() {
        return MaCN;
    }

    public void setMaCN(String maCN) {
        MaCN = maCN;
    }

    public String getHoCN() {
        return HoCN;
    }

    public void setHoCN(String hoCN) {
        HoCN = hoCN;
    }

    public String getTenCN() {
        return TenCN;
    }

    public void setTenCN(String tenCN) {
        TenCN = tenCN;
    }

    public String getPhanXuong() {
        return PhanXuong;
    }

    public void setPhanXuong(String phanXuong) {
        PhanXuong = phanXuong;
    }

    @Override
    public String toString() {
        return "CongNhan{" +
                "MaCN='" + MaCN + '\'' +
                ", HoCN='" + HoCN + '\'' +
                ", TenCN='" + TenCN + '\'' +
                ", PhanXuong='" + PhanXuong + '\'' +
                '}';
    }
}
