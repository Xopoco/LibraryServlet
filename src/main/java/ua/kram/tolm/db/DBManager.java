package ua.kram.tolm.db;

import org.apache.log4j.Logger;
import ua.kram.tolm.exception.GlobalException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


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

    private DBManager() {
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
        Connection con = null;
        try {
            Context ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/library");
            con = ds.getConnection();
        } catch (NamingException e) {
            LOG.error(e);
        }
        if (con == null){
            LOG.error("Connection is null!");
            throw new GlobalException("Database connection failed");
        }

        return con;
    }


    /**
     * Commits and close the given connection.
     *
     * @param con Connection to be committed and closed.
     *
     */
    public void commitAndClose(Connection con) {
        try {
            if (!con.getAutoCommit()) {
                con.commit();
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con  Connection to be rollbacked and closed.
     *
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
