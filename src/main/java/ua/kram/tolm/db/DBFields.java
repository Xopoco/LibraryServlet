package ua.kram.tolm.db;

public final class DBFields {

    //common fields
    public static final String ENTITY_ID = "id";

    //table users
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_TELEPHONE = "telephone";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_DEBT = "debt";

    //table books
    public static final String BOOK_NAME = "name";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_EDITION = "edition";
    public static final String BOOK_EDITION_DATE = "edition_date";
    public static final String BOOK_REVIEW = "review";
    public static final String BOOK_PRICE = "price";
    public static final String BOOK_GENRE_ID = "genres_id";
    public static final String BOOK_COUNT = "count";

    //table orders
    public static final String ORDER_USER_ID = "user_id";
    public static final String ORDER_BOOK_ID = "book_id";
    public static final String ORDER_PRICE = "price";
    public static final String ORDER_DAY_COUNT = "day_count";
    public static final String ORDER_DATE = "order_date";

    //table genres
    public static final String GENRE_NAME = "name";

}
