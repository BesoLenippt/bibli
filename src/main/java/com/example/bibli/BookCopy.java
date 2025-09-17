package com.example.bibli;

public class BookCopy {
    private int id;
    private int bookId;
    private String inventoryNumber;
    private String barcode;
    private String status;
    private String location;

    public BookCopy() {}

    public BookCopy(int id, int bookId, String inventoryNumber, String barcode,
                    String status, String location) {
        this.id = id;
        this.bookId = bookId;
        this.inventoryNumber = inventoryNumber;
        this.barcode = barcode;
        this.status = status;
        this.location = location;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getInventoryNumber() { return inventoryNumber; }
    public void setInventoryNumber(String inventoryNumber) { this.inventoryNumber = inventoryNumber; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
