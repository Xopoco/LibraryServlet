package ua.kram.tolm.db.entity;

public class Book extends Entity{
    private static final long serialVersionUID = -7964377341993111271L;

    private String name;
    private String author;
    private String ganre;
    private String edition;
    private String dateOfEdition;
    private int price;
    private int count;

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

    public String getGanre() {
        return ganre;
    }

    public void setGanre(String ganre) {
        this.ganre = ganre;
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
                ", ganre='" + ganre + '\'' +
                ", edition='" + edition + '\'' +
                ", dateOfEdition='" + dateOfEdition + '\'' +
                ", price=" + price +
                '}';
    }
}
