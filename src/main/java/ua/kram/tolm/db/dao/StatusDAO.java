package ua.kram.tolm.db.dao;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBFields;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.Status;
import ua.kram.tolm.exception.GlobalException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StatusDAO {
    private static final Logger LOG = Logger.getLogger(StatusDAO.class);

    private static final String MYSQL_FIND_STATUS_BY_ID = "SELECT * FROM order_status WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_FIND_ALL_STATUSES = "SELECT * FROM order_status";



    /**
     * Find and return all statuses.
     *
     * @return Map of status item entities.
     */
    public static Map<Integer, Status> findAllStatuses() throws GlobalException {
        Map<Integer, Status> statusMap = new HashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MYSQL_FIND_ALL_STATUSES);
            while (rs.next()) {
                Status status = extractStatus(rs);
                statusMap.put(status.getId(), status);
            }
        } catch (SQLException ex) {
            LOG.error("#findAllStatuses error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find statuses.");
        } finally {
            DBManager.getInstance().close(con, stmt, rs);
        }
        return statusMap;
    }


    /**
     * Find and return status by id.
     *
     */
    public static Status findStatus (int statusId) throws GlobalException {
        Status status = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_FIND_STATUS_BY_ID);
            ps.setInt(1, statusId);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = extractStatus(rs);
            }
        } catch (SQLException ex) {
            LOG.error("#findStatus error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find status.");
        } finally {
            DBManager.getInstance().close(con, ps, rs);
        }
        return status;
    }


    private static Status extractStatus(ResultSet rs) throws SQLException {
        Status status = new Status();
        status.setId(rs.getInt(DBFields.ENTITY_ID));
        status.setValue(rs.getString(DBFields.STATUS_VALUE));
        return status;
    }

    private StatusDAO(){
        throw new IllegalStateException("Utility class");
    }
}
