package ua.kram.tolm.db.dao;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBFields;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.Author;
import ua.kram.tolm.exception.GlobalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorDAO {
    private static final Logger LOG = Logger.getLogger(AuthorDAO.class);

    private static final String MYSQL_FIND_AUTHOR_BY_ID = "SELECT * FROM authors WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_FIND_ALL_AUTHORS = "SELECT * FROM authors";
    private static final String MYSQL_DELETE_AUTHOR_BY_ID = "DELETE FROM authors WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_UPDATE_AUTHOR_BY_ID = "UPDATE authors SET " +
            DBFields.AUTHOR_FIRST_NAME + "=?, " +
            DBFields.AUTHOR_LAST_NAME + "=?, " +
            DBFields.AUTHOR_DATE_OF_BIRTH + "=? WHERE id=?";
    private static final String MYSQL_INSERT_AUTHOR = "INSERT INTO authors (" +
            DBFields.AUTHOR_FIRST_NAME + ", " +
            DBFields.AUTHOR_LAST_NAME + ", " +
            DBFields.AUTHOR_DATE_OF_BIRTH + ") VALUES (?, ?, ?)";

    /**
     * Find and return author by id.
     *
     */
    public static Author findAuthor(int bookId) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Author author = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_FIND_AUTHOR_BY_ID);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            if (rs.next()) {
                author = extractAuthor(rs);
            }

        } catch (SQLException ex) {
            LOG.error("#findAuthor error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find author.");
        } finally {
            DBManager.getInstance().close(con, ps, rs);
        }
        return author;
    }

    /**
     * Find and return all authors.
     *
     * @return Map of author item entities.
     */
    public static Map<Integer, Author> findAllAuthors() throws GlobalException {
        Map<Integer, Author> authorMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MYSQL_FIND_ALL_AUTHORS);
            while (rs.next()) {
                Author author = extractAuthor(rs);
                authorMap.put(author.getId(), author);
            }
        } catch (SQLException ex) {
            LOG.error("#findAllAuthors error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find authors.");
        } finally {
            DBManager.getInstance().close(con, stmt, rs);
        }
        return authorMap;
    }

    /**
     * Delete author by id.
     * @param authorId  author id to delete.
     *
     */
    public static void deleteAuthor (int authorId) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_DELETE_AUTHOR_BY_ID);
            ps.setInt(1, authorId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("#deleteAuthor error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't delete author.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Update author.
     * @param author author to update.
     *
     */
    public static void updateAuthor(Author author) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(MYSQL_UPDATE_AUTHOR_BY_ID);
            int k = 1;
            ps.setString(k++, author.getFirstName());
            ps.setString(k++, author.getLastName());
            ps.setString(k++, author.getDateOfBirth());
            ps.setInt(k++, author.getId());
            ps.executeUpdate();

            con.commit();

        } catch (SQLException ex) {
            LOG.error("#updateAuthor error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't update author.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Insert author to DB.
     * @param author author to insert.
     *
     */
    public static void insertAuthor(Author author) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(MYSQL_INSERT_AUTHOR);
            int k = 1;
            ps.setString(k++, author.getFirstName());
            ps.setString(k++, author.getLastName());
            ps.setString(k++, author.getDateOfBirth());
            ps.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            LOG.error("#insertAuthor error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't insert author.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }


    private static Author extractAuthor(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt(DBFields.ENTITY_ID));
        author.setFirstName(rs.getString(DBFields.AUTHOR_FIRST_NAME));
        author.setLastName(rs.getString(DBFields.AUTHOR_LAST_NAME));
        author.setDateOfBirth(rs.getString(DBFields.AUTHOR_DATE_OF_BIRTH));

        return author;
    }

    private AuthorDAO() {
        throw new IllegalStateException("Utility class");
    }
}
