// Kelas untuk data pembelian barang
class DataPembelian {
    private String kodeBarang;
    private String namaBarang;
    private double hargaBarang;
    private int jumlahBeli;

    // Constructor
    public DataPembelian(String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBeli = jumlahBeli;
    }

    // Metode untuk menghitung total bayar
    public double hitungTotalBayar() {
        return hargaBarang * jumlahBeli;
    }

    // Override untuk representasi string objek
    @Override
    public String toString() {
        return "DATA PEMBELIAN BARANG\n----------------------------\n" +
               "Kode Barang      : " + kodeBarang + "\n" +
               "Nama Barang      : " + namaBarang + "\n" +
               "Harga Barang     : " + hargaBarang + "\n" +
               "Jumlah Beli      : " + jumlahBeli + "\n" +
               "TOTAL BAYAR      : " + hitungTotalBayar() + "\n" +
               "++++++++++++++++++++++++";
    }
}