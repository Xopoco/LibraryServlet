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
    public static final String USER_BLOCK_STATUS = "block_status";

    //table books
    public static final String BOOK_NAME = "name";
    public static final String BOOK_AUTHOR_ID = "author_id";
    public static final String BOOK_EDITION = "edition";
    public static final String BOOK_EDITION_DATE = "edition_date";
    public static final String BOOK_PICTURE = "picture_address";
    public static final String BOOK_REVIEW = "review";
    public static final String BOOK_PRICE = "price";
    public static final String BOOK_GENRE_ID = "genres_id";
    public static final String BOOK_COUNT = "count";

    // table authors
    public static final String AUTHOR_FIRST_NAME = "first_name";
    public static final String AUTHOR_LAST_NAME = "last_name";
    public static final String AUTHOR_DATE_OF_BIRTH = "date_of_birth";

    //table orders
    public static final String ORDER_USER_ID = "user_id";
    public static final String ORDER_BOOK_ID = "book_id";
    public static final String ORDER_STATUS_ID = "status_id";
    public static final String ORDER_DEBT = "debt";
    public static final String ORDER_DAY_COUNT = "day_count";
    public static final String ORDER_DATE = "order_date";

    //table order_status
    public static final String STATUS_ID = "id";
    public static final String STATUS_VALUE = "title";

    //table genres
    public static final String GENRE_NAME = "name";

    private DBFields() {
        throw new IllegalStateException("Utility class");
    }
}
