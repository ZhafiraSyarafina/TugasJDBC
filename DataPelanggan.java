// Kelas untuk data pelanggan
class DataPelanggan {
    public String namaPelanggan;
    public String noHP;
    public String alamat;

    // Constructor
    public DataPelanggan(String namaPelanggan, String noHP, String alamat) {
        this.namaPelanggan = namaPelanggan;
        this.noHP = noHP;
        this.alamat = alamat;
    }

    // Override untuk representasi string objek
    @Override
    public String toString() {
        return "DATA PELANGGAN\n---------------------\n" +
               "Nama Pelanggan : " + namaPelanggan + "\n" +
               "No. HP         : " + noHP + "\n" +
               "Alamat         : " + alamat + "\n" +
               "++++++++++++++++++++++++";
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public String getNoHP() {
        return noHP;
    }

    public String getAlamat() {
        return alamat;
    }
}
