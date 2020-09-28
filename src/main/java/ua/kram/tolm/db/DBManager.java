package ua.kram.tolm.db;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.Errors;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static Logger LOG =Logger.getLogger(DBManager.class);
    private static DBManager instance;

    private DBManager() {
        //no op
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        InitialContext ic = null;
        DataSource ds = null;

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/library");
        } catch (NamingException e) {
            LOG.error(Errors.TEST_ERROR);
        }
        if (ds == null){
            LOG.error(Errors.TEST_ERROR);
            throw new NullPointerException();
        }

        return ds.getConnection();
    }


    public boolean updateUser(User user){
        String url = "UPDATE teams SET name=? WHERE id=?";
        PreparedStatement ps = null;
        Connection con = null;
        boolean flag = false;
        try {
            con = getConnection();
            ps = con.prepareStatement(url);


            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            LOG.error(Errors.TEST_ERROR, ex);
        } finally {
            close(ps);
            close(con);
        }
        return flag;
    }

    public boolean deleteUser (int userId){
        String url = "DELETE FROM users WHERE " + DBFields.ENTITY_ID + "=?";

        PreparedStatement ps = null;
        Connection con = null;
        boolean flag = false;
        try {
            con = getConnection();
            ps = con.prepareStatement(url);
            ps.setInt(1, userId);

            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            LOG.error(Errors.TEST_ERROR, ex);
        } finally {
            close(ps);
            close(con);
        }
        return flag;
    }

    public boolean insertUser(User user) {
        String url = "INSERT INTO users (" +
                DBFields.USER_LOGIN + ", " +
                DBFields.USER_PASSWORD + ", " +
                DBFields.USER_FIRST_NAME + ", " +
                DBFields.USER_LAST_NAME + ", " +
                DBFields.USER_EMAIL + ", " +
                DBFields.USER_TELEPHONE + ", " +
                DBFields.USER_ROLE_ID + ", " +
                DBFields.USER_DEBT + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = null;
        Connection con = null;
        boolean flag = false;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(url);
            pstmt.setString(1, user.getLogin());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getTelephone());
            pstmt.setInt(7, user.getRoleId());
            pstmt.setInt(8, user.getDebt());

            if (pstmt.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            LOG.error(Errors.TEST_ERROR, ex);
        } finally {
            close(pstmt);
            close(con);
        }
        return flag;
    }


    public List <User> findAllUsers(){
        String url = "SELECT * FROM users";
        List <User> userList = new ArrayList <>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(url);
            while (rs.next()) {
                userList.add(extractUser(rs));
            }
        } catch (SQLException ex) {
            LOG.error(Errors.TEST_ERROR);

        } finally {
            close(con, stmt, rs);
        }
        return userList;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(DBFields.ENTITY_ID));
        user.setLogin(rs.getString(DBFields.USER_LOGIN));
        user.setPassword(rs.getString(DBFields.USER_PASSWORD));
        user.setFirstName(rs.getString(DBFields.USER_FIRST_NAME));
        user.setLastName(rs.getString(DBFields.USER_LAST_NAME));
        user.setEmail(rs.getString(DBFields.USER_EMAIL));
        user.setTelephone(rs.getString(DBFields.USER_TELEPHONE));
        user.setRoleId(rs.getInt(DBFields.USER_ROLE_ID));
        user.setDebt(rs.getInt(DBFields.USER_DEBT));
        return user;
    }

    /**
     * Closes a connection.
     * @param con Connection to be closed.
     */
    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Errors.TEST_ERROR);
                ex.printStackTrace();
            }
        }
    }


    /**
     * Closes a statement object.
     */
    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Errors.TEST_ERROR);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Closes a result set object.
     */
    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Errors.TEST_ERROR);
                ex.printStackTrace();
            }
        }
    }

    /**
     * Closes resources.
     */
    private void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }


}
