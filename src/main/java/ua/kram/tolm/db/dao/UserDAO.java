package ua.kram.tolm.db.dao;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBFields;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    private static final String MYSQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE " + DBFields.USER_LOGIN + "=?";
    private static final String MYSQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String MYSQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_UPDATE_USER_BY_ID = "UPDATE users SET " +
            DBFields.USER_LOGIN + "=?, " +
            DBFields.USER_FIRST_NAME + "=?, " +
            DBFields.USER_LAST_NAME + "=?, " +
            DBFields.USER_EMAIL + "=?, " +
            DBFields.USER_TELEPHONE + "=? WHERE id=?";
    private static final String MYSQL_INSERT_USER = "INSERT INTO users (" +
            DBFields.USER_LOGIN + ", " +
            DBFields.USER_PASSWORD + ", " +
            DBFields.USER_FIRST_NAME + ", " +
            DBFields.USER_LAST_NAME + ", " +
            DBFields.USER_EMAIL + ", " +
            DBFields.USER_TELEPHONE + ", " +
            DBFields.USER_ROLE_ID + ") VALUES (?, ?, ?, ?, ?, ?, ?)";


    /**
     * Find and return user by id.
     *
     */
    public static User findUser (int userId) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_FIND_USER_BY_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException ex) {
            LOG.error("#findUser error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find user.");
        } finally {
            DBManager.getInstance().close(con, ps, rs);
        }
        return user;
    }

    /**
     * Find and return user by login.
     *
     */
    public static User findUserByLogin (String login) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_FIND_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException ex) {
            LOG.error("#findUserByLogin error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find user.");
        } finally {
            DBManager.getInstance().close(con, ps, rs);
        }
        return user;
    }

    /**
     * Find and return all registered users.
     *
     * @return List of user item entities.
     */
    public static List<User> findAllUsers() throws GlobalException {
        List <User> userList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MYSQL_FIND_ALL_USERS);
            while (rs.next()) {
                userList.add(extractUser(rs));
            }
        } catch (SQLException ex) {
            LOG.error("#findAllUsers error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find users.");
        } finally {
            DBManager.getInstance().close(con, stmt, rs);
        }
        return userList;
    }

    /**
     * Update user.
     * @param user user to update.
     *
     */
    public static void updateUser(User user) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(MYSQL_UPDATE_USER_BY_ID);
            int k = 1;
            ps.setString(k++, user.getLogin());
            ps.setString(k++, user.getFirstName());
            ps.setString(k++, user.getLastName());
            ps.setString(k++, user.getEmail());
            ps.setString(k++, user.getTelephone());
            ps.setInt(k, user.getId());
            ps.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            LOG.error("#updateUser error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't update user.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Delete user by id.
     * @param userId  user id to delete.
     *
     */
    public static void deleteUser (int userId) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_DELETE_USER_BY_ID);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("#deleteUser error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't delete user.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Insert user to DB.
     * @param user  user to insert.
     *
     */
    public static void insertUser(User user) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(MYSQL_INSERT_USER);
            int k = 1;
            ps.setString(k++, user.getLogin());
            ps.setString(k++, user.getPassword());
            ps.setString(k++, user.getFirstName());
            ps.setString(k++, user.getLastName());
            ps.setString(k++, user.getEmail());
            ps.setString(k++, user.getTelephone());
            ps.setInt(k++, user.getRoleId());
            ps.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            LOG.error("#insertUser error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't insert user.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }



    private static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(DBFields.ENTITY_ID));
        user.setLogin(rs.getString(DBFields.USER_LOGIN));
        user.setPassword(rs.getString(DBFields.USER_PASSWORD));
        user.setFirstName(rs.getString(DBFields.USER_FIRST_NAME));
        user.setLastName(rs.getString(DBFields.USER_LAST_NAME));
        user.setEmail(rs.getString(DBFields.USER_EMAIL));
        user.setTelephone(rs.getString(DBFields.USER_TELEPHONE));
        user.setRoleId(rs.getInt(DBFields.USER_ROLE_ID));
        user.setBlockStatus(rs.getInt(DBFields.USER_BLOCK_STATUS));
        return user;
    }

    private UserDAO(){
        throw new IllegalStateException("Utility class");
    }
}