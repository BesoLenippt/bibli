package com.example.bibli;

public class Book {
        private int id;
        private String title;
        private String author;
        private String isbn;
        private String publisher;
        private int year;
        private String description;
        private int genreId;

        public Book() {}

        public Book(int id, String title, String author, String isbn, String publisher,
                    int year, String description, int genreId) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.publisher = publisher;
            this.year = year;
            this.description = description;
            this.genreId = genreId;
        }

        // Геттеры и сеттеры
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }

        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }

        public String getPublisher() { return publisher; }
        public void setPublisher(String publisher) { this.publisher = publisher; }

        public int getYear() { return year; }
        public void setYear(int year) { this.year = year; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public int getGenreId() { return genreId; }
        public void setGenreId(int genreId) { this.genreId = genreId; }
}
