package ua.kram.tolm.db.entity;

public class Book extends Entity{

    private String name;
    private String author;
    private int genre;
    private String edition;
    private String dateOfEdition;
    private String review;
    private int price;
    private int count;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getGanre() {
        return genre;
    }

    public void setGanre(int ganre) {
        this.genre = ganre;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getDateOfEdition() {
        return dateOfEdition;
    }

    public void setDateOfEdition(String dateOfEdition) {
        this.dateOfEdition = dateOfEdition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", ganre='" + genre + '\'' +
                ", edition='" + edition + '\'' +
                ", dateOfEdition='" + dateOfEdition + '\'' +
                ", price=" + price +
                '}';
    }
}
