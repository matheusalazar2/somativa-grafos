public class WaitlistEntry {
    private String userName;
    private String bookTitle;

    public WaitlistEntry(String userName, String bookTitle) {
        this.userName = userName;
        this.bookTitle = bookTitle;
    }

    public String getUserName() {
        return userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    @Override
    public String toString() {
        return userName + " - " + bookTitle;
    }
}