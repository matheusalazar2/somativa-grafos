import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private Integer year;
    private String genre;
    private Integer availableBooks;

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book(String title, String author, Integer year, String genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.availableBooks = 1;
    }

    public Book(String title, String author, Integer year, String genre, Integer availableBooks) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.availableBooks = availableBooks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(Integer availableBooks) {
        this.availableBooks = availableBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(year, book.year) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, genre);
    }

    @Override
    public String toString() {
        return "Book[" +
                "title: '" + title + '\'' +
                "| author: '" + author + '\'' +
                "| year: " + year +
                "| genre: '" + genre + '\'' +
                "| availableBooks: " + availableBooks +
                ']';
    }
}
