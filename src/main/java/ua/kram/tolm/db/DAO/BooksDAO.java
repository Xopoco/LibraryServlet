package ua.kram.tolm.db.DAO;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBFields;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.Book;
import ua.kram.tolm.exception.GlobalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO {
    private static final Logger LOG = Logger.getLogger(BooksDAO.class);

    private static final String MYSQL_FIND_BOOK_BY_ID = "SELECT * FROM books WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_FIND_ALL_BOOKS = "SELECT * FROM books";
    private static final String MYSQL_DELETE_BOOK_BY_ID = "DELETE FROM books WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_UPDATE_BOOK_BY_ID = "UPDATE books SET " +
            DBFields.BOOK_NAME + "=?, " +
            DBFields.BOOK_AUTHOR + "=?, " +
            DBFields.BOOK_GENRE_ID + "=?, " +
            DBFields.BOOK_EDITION + "=?, " +
            DBFields.BOOK_EDITION_DATE + "=?, " +
            DBFields.BOOK_REVIEW + "=?, " +
            DBFields.BOOK_PRICE + "=?, " +
            DBFields.BOOK_COUNT + "=? WHERE id=?";
    private static final String MYSQL_INSERT_BOOK = "INSERT INTO books (" +
            DBFields.BOOK_NAME + ", " +
            DBFields.BOOK_AUTHOR + ", " +
            DBFields.BOOK_GENRE_ID + ", " +
            DBFields.BOOK_EDITION + ", " +
            DBFields.BOOK_EDITION_DATE + ", " +
            DBFields.BOOK_REVIEW + ", " +
            DBFields.BOOK_PRICE + ", " +
            DBFields.BOOK_COUNT + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


    /**
     * Find and return book by id.
     *
     */
    public static Book findBook (int bookId) throws GlobalException {
        Connection con = null;
        Book book = null;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(MYSQL_FIND_BOOK_BY_ID);
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = extractBook(rs);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            LOG.error("#findBook error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find book.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return book;
    }

    /**
     * Find and return all books.
     *
     * @return List of book item entities.
     */
    public static List<Book> findAllBooks() throws GlobalException {
        List <Book> bookList = new ArrayList<>();
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(MYSQL_FIND_ALL_BOOKS);
            while (rs.next()) {
                bookList.add(extractBook(rs));
            }
        } catch (SQLException ex) {
            LOG.error("#findAllBooks error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find book.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return bookList;
    }

    /**
     * Update book.
     * @param book book to update.
     *
     */
    public static void updateBook(Book book) throws GlobalException {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(MYSQL_UPDATE_BOOK_BY_ID);
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getGenre());
            ps.setString(4, book.getEdition());
            ps.setString(5, book.getDateOfEdition());
            ps.setString(6, book.getReview());
            ps.setInt(7, book.getPrice());
            ps.setInt(8, book.getCount());
            ps.setInt(9, book.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            LOG.error("#updateBook error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't update book.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Delete book by id.
     * @param bookId book id to delete.
     *
     */
    public static void deleteBook (int bookId) throws GlobalException {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(MYSQL_DELETE_BOOK_BY_ID);
            ps.setInt(1, bookId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            LOG.error("#deleteBook error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't delete book.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Insert book to DB.
     * @param book  book to insert.
     *
     */
    public static void insertBook(Book book) throws GlobalException {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(MYSQL_INSERT_BOOK);
            int k = 1;
            pstmt.setString(k++, book.getName());
            pstmt.setString(k++, book.getAuthor());
            pstmt.setInt(k++, book.getGenre());
            pstmt.setString(k++, book.getEdition());
            pstmt.setString(k++, book.getDateOfEdition());
            pstmt.setString(k++, book.getReview());
            pstmt.setInt(k++, book.getPrice());
            pstmt.setInt(k++, book.getCount());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            LOG.error("#insertBook error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't insert book.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }



    private static Book extractBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt(DBFields.ENTITY_ID));
        book.setName(rs.getString(DBFields.BOOK_NAME));
        book.setAuthor(rs.getString(DBFields.BOOK_AUTHOR));
        book.setGenre(rs.getInt(DBFields.BOOK_GENRE_ID));
        book.setEdition(rs.getString(DBFields.BOOK_EDITION));
        book.setDateOfEdition(rs.getString(DBFields.BOOK_EDITION_DATE));
        book.setPicture(rs.getString(DBFields.BOOK_PICTURE));
        book.setReview(rs.getString(DBFields.BOOK_REVIEW));
        book.setPrice(rs.getInt(DBFields.BOOK_PRICE));
        book.setCount(rs.getInt(DBFields.BOOK_COUNT));

        return book;
    }


}
