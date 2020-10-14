package ua.kram.tolm.db.entity;

public class Book extends Entity{

    private String name;
    private String edition;
    private String dateOfEdition;
    private String review;
    private String picture;
    private int genreId;
    private int authorId;
    private int price;
    private int count;


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int ganre) {
        this.genreId = ganre;
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
                ", edition='" + edition + '\'' +
                ", dateOfEdition='" + dateOfEdition + '\'' +
                ", price=" + price +
                '}';
    }
}
