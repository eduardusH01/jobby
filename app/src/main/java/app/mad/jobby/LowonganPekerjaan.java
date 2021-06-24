package app.mad.jobby;

public class LowonganPekerjaan {
    private String nama, lokasi, penyedia;
    private int gaji, umur_min, umur_max;
    private String pendidikan_terakhir, image_url, description, requirement, job_url;

    public LowonganPekerjaan(String nama, String lokasi, String penyedia, int gaji, int umur_min, int umur_max, String pendidikan_terakhir, String image_url, String description, String requirement, String job_url) {
        this.nama = nama;
        this.lokasi = lokasi;
        this.penyedia = penyedia;
        this.gaji = gaji;
        this.umur_min = umur_min;
        this.umur_max = umur_max;
        this.pendidikan_terakhir = pendidikan_terakhir;
        this.image_url = image_url;
        this.description = description;
        this.requirement = requirement;
        this.job_url = job_url;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getPenyedia() {
        return penyedia;
    }

    public int getGaji() {
        return gaji;
    }

    public int getUmur_min() {
        return umur_min;
    }

    public int getUmur_max() {
        return umur_max;
    }

    public String getPendidikan_terakhir() {
        return pendidikan_terakhir;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getJob_url() {
        return job_url;
    }

    @Override
    public String toString() {
        return "LowonganPekerjaan{" +
                "nama='" + nama + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", penyedia='" + penyedia + '\'' +
                ", gaji=" + gaji +
                ", umur_min=" + umur_min +
                ", umur_max=" + umur_max +
                ", pendidikan_terakhir='" + pendidikan_terakhir + '\'' +
                ", image_url='" + image_url + '\'' +
                ", description='" + description + '\'' +
                ", requirement='" + requirement + '\'' +
                ", job_url='" + job_url + '\'' +
                '}';
    }
}
