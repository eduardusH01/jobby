package app.mad.jobby;

public class LowonganPekerjaan {
    private String nama, lokasi, pendidikanTerakhir, penyedia, imageUrl;
    private int gaji, umurMin, umurMax;

    public LowonganPekerjaan(String nama, String lokasi, String pendidikanTerakhir, String penyedia, String imageUrl, int gaji, int umurMin, int umurMax) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.pendidikanTerakhir = pendidikanTerakhir;
        this.penyedia = penyedia;
        this.imageUrl = imageUrl;
        this.gaji = gaji;
        this.umurMin = umurMin;
        this.umurMax = umurMax;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getPendidikanTerakhir() {
        return pendidikanTerakhir;
    }

    public String getPenyedia() {
        return penyedia;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getGaji() {
        return gaji;
    }

    public int getUmurMin() {
        return umurMin;
    }

    public int getUmurMax() {
        return umurMax;
    }

    @Override
    public String toString() {
        return "LowonganPekerjaan{" +
                "nama='" + nama + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", pendidikanTerakhir='" + pendidikanTerakhir + '\'' +
                ", penyedia='" + penyedia + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", gaji=" + gaji +
                ", umurMin=" + umurMin +
                ", umurMax=" + umurMax +
                '}';
    }
}
