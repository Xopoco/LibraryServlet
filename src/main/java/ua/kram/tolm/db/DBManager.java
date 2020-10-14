package ua.kram.tolm.db;

import org.apache.log4j.Logger;
import ua.kram.tolm.exception.GlobalException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * DB manager.
 * Singleton.
 * Works with MySQL.
 *
 * @author Tolmachev
 *
 */
public class DBManager {
    private static final Logger LOG =Logger.getLogger(DBManager.class);
    private static DBManager instance;
    private Context ic ;
    private DataSource ds;

    private DBManager() {
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/library");
        } catch (NamingException e) {
            LOG.error("DBManager initialize error.", e);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Returns  DB connection from the TomCat Pool Connections.
     * Configuration in webapp/META-INF/context.xml file.
     *
     * @return  DB connection.
     */
    public Connection getConnection() throws SQLException, GlobalException {

        Connection con = ds.getConnection();;

        if (con == null){
            LOG.error("Connection is null!");
            throw new GlobalException("Database connection failed");
        }

        return con;
    }


    /**
     * Rollbacks and close the given connection.
     *
     * @param con  Connection to be rollbacked and closed.
     *
     */
    public void rollbackAndClose(Connection con) {
        try {
            if (con != null) {
                con.rollback();
                con.close();
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        }
    }

    /**
     * Close the given connection.
     *
     * @param con Connection to be closed.
     *
     */


    public void close(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        }
    }

    public void close(Connection con, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        }
    }

    public void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        }
    }


}
