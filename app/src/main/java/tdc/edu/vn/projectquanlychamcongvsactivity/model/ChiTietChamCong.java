package tdc.edu.vn.projectquanlychamcongvsactivity.model;

public class ChiTietChamCong  {
    String MaCC,MaSP,SoTP,SoPP;

    public ChiTietChamCong() {
    }

    public ChiTietChamCong(String maCC, String maSP, String soTP, String soPP) {
        MaCC = maCC;
        MaSP = maSP;
        SoTP = soTP;
        SoPP = soPP;
    }

    public String getMaCC() {
        return MaCC;
    }

    public void setMaCC(String maCC) {
        MaCC = maCC;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getSoTP() {
        return SoTP;
    }

    public void setSoTP(String soTP) {
        SoTP = soTP;
    }

    public String getSoPP() {
        return SoPP;
    }

    public void setSoPP(String soPP) {
        SoPP = soPP;
    }

    @Override
    public String toString() {
        return "ChiTietChamCong{" +
                "MaCC='" + MaCC + '\'' +
                ", MaSP='" + MaSP + '\'' +
                ", SoTP='" + SoTP + '\'' +
                ", SoPP='" + SoPP + '\'' +
                '}';
    }
}
