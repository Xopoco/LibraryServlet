package ua.kram.tolm.db.dao;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBFields;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.Genre;
import ua.kram.tolm.exception.GlobalException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GenreDAO {
    private static final Logger LOG = Logger.getLogger(GenreDAO.class);

    private static final String MYSQL_FIND_GENRE_BY_ID = "SELECT * FROM genres WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_FIND_ALL_GENRES = "SELECT * FROM genres";
    private static final String MYSQL_UPDATE_GENRE_BY_ID = "UPDATE genres SET " + DBFields.GENRE_NAME + "=? WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_INSERT_GENRE = "INSERT INTO genres (" + DBFields.GENRE_NAME + ") VALUES (?)";
    private static final String MYSQL_DELETE_GENRE_BY_ID = "DELETE FROM genres WHERE " + DBFields.ENTITY_ID + "=?";

    /**
     * Find and return genre by id.
     *
     */
    public static Genre findGenre(int genreId) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Genre genre = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_FIND_GENRE_BY_ID);
            ps.setInt(1, genreId);
            rs = ps.executeQuery();
            if (rs.next()) {
                genre = new Genre();
                genre.setId(rs.getInt(DBFields.ENTITY_ID));
                genre.setName(rs.getString(DBFields.GENRE_NAME));
            }
        } catch (SQLException ex) {
            LOG.error("#findGenre error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find genre.");
        } finally {
            DBManager.getInstance().close(con, ps, rs);
        }
        return genre;
    }

    /**
     * Delete genre by id.
     * @param genreId genre id to delete.
     *
     */
    public static void deleteGenre (int genreId) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_DELETE_GENRE_BY_ID);
            ps.setInt(1, genreId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("#deleteGenre error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't delete genre.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Insert genre to DB.
     * @param genre  genre to insert.
     *
     */
    public static void insertGenre(Genre genre) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_INSERT_GENRE);
            ps.setString(1, genre.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("#insertGenre error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't insert genre.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Find and return all genres.
     *
     * @return Map of genres item entities.
     */
    public static Map<Integer, Genre> findAllGenres() throws GlobalException {
        Map<Integer, Genre> statusMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MYSQL_FIND_ALL_GENRES);
            while (rs.next()) {
                Genre genre = extractGenre(rs);
                statusMap.put(genre.getId(), genre);
            }
        } catch (SQLException ex) {
            LOG.error("#findAllGenres error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find genres.");

        } finally {
            DBManager.getInstance().close(con, stmt, rs);
        }
        return statusMap;
    }

    /**
     * Update genre.
     * @param genre genre to update.
     *
     */
    public static void updateGenre(Genre genre) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(MYSQL_UPDATE_GENRE_BY_ID);
            ps.setString(1, genre.getName());
            ps.setInt(1, genre.getId());
            ps.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            LOG.error("#updateBook error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't update book.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    private static Genre extractGenre(ResultSet rs) throws SQLException {
        Genre genre = new Genre();
        genre.setId(rs.getInt(DBFields.ENTITY_ID));
        genre.setName(rs.getString(DBFields.GENRE_NAME));
        return genre;
    }

    private GenreDAO() {
        throw new IllegalStateException("Utility class");
    }
}
