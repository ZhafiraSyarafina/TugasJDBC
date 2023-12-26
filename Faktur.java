// Kelas Faktur yang menerapkan interface Barang
class Faktur implements Barang {
    private String noFaktur;
    private Barang barang;
    private DataPelanggan dataPelanggan;

    // Constructor
    public Faktur(String noFaktur, Barang barang, DataPelanggan dataPelanggan) {
        this.noFaktur = noFaktur;
        this.barang = barang;
        this.dataPelanggan = dataPelanggan;
    }

    // Implementasi hitungTotal() dari interface Barang
    @Override
    public double hitungTotal() {
        return barang.hitungTotal();
    }

    // Override untuk representasi string objek
    @Override
    public String toString() {
        return "No Faktur      : " + noFaktur + "\n" +
               dataPelanggan.toString() + "\n" +
               barang.toString() + "\n" +
               "Total Bayar   : " + hitungTotal() + "\n" +
               "++++++++++++++++++++++++";
    }
}
