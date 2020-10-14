package ua.kram.tolm.db.dao;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBFields;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.Order;
import ua.kram.tolm.exception.GlobalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final Logger LOG = Logger.getLogger(OrderDAO.class);

    private static final String MYSQL_FIND_ALL_ORDERS = "SELECT * FROM orders";
    private static final String MYSQL_FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_FIND_ALL_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE " + DBFields.ORDER_USER_ID + "=?";
    private static final String MYSQL_FIND_ALL_ORDERS_BY_BOOK_ID = "SELECT * FROM orders WHERE " + DBFields.ORDER_BOOK_ID + "=?";
    private static final String MYSQL_UPDATE_ORDER_BY_ID = "UPDATE orders SET " +
            DBFields.ORDER_USER_ID + "=?, " +
            DBFields.ORDER_BOOK_ID + "=?, " +
            DBFields.ORDER_STATUS_ID + "=?, " +
            DBFields.ORDER_DEBT + "=?, " +
            DBFields.ORDER_DAY_COUNT + "=?, " +
            DBFields.ORDER_DATE + "=? WHERE id=?";
    private static final String MYSQL_INSERT_ORDER = "INSERT INTO orders (" +
            DBFields.ORDER_USER_ID + ", " +
            DBFields.ORDER_BOOK_ID + ", " +
            DBFields.ORDER_STATUS_ID + ", " +
            DBFields.ORDER_DEBT + ", " +
            DBFields.ORDER_DAY_COUNT + ", " +
            DBFields.ORDER_DATE + ") VALUES (?, ?, ?, ?, ?, ?)";



    /**
     * Find and return all orders by user id.
     *
     * @return List of order item entities.
     */
    public static List<Order> findAllOrders() throws GlobalException {
        List <Order> orderList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(MYSQL_FIND_ALL_ORDERS);
            while (rs.next()) {
                orderList.add(extractOrder(rs));
            }
        } catch (SQLException ex) {
            LOG.error("#findAllOrders error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find order.");
        } finally {
            DBManager.getInstance().close(con, stmt, rs);
        }
        return orderList;
    }

    /**
     * Update order.
     * @param order - order to update.
     *
     */
    public static void updateOrder(Order order) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(MYSQL_UPDATE_ORDER_BY_ID);
            int k = 1;
            ps.setInt(k++, order.getUserId());
            ps.setInt(k++, order.getBookId());
            ps.setInt(k++, order.getStatusId());
            ps.setInt(k++, order.getDebt());
            ps.setInt(k++, order.getDayCount());
            ps.setString(k++, order.getDate());
            ps.setInt(k++, order.getId());
            ps.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            LOG.error("#updateOrder error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't update order.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Delete order by id.
     * @param orderId - order id to delete.
     *
     */
    public static void deleteOrder (int orderId) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps =  con.prepareStatement(MYSQL_DELETE_ORDER_BY_ID);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.error("#deleteOrder error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't delete order.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Insert order to DB.
     * @param order  order to insert.
     *
     */
    public static void insertOrder(Order order) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(MYSQL_INSERT_ORDER);
            int k = 1;
            ps.setInt(k++, order.getUserId());
            ps.setInt(k++, order.getBookId());
            ps.setInt(k++, order.getStatusId());
            ps.setInt(k++, order.getDebt());
            ps.setInt(k++, order.getDayCount());
            ps.setString(k++, order.getDate());
            ps.executeUpdate();

            con.commit();
        } catch (SQLException ex) {
            LOG.error("#insertOrder error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't insert order.");
        } finally {
            DBManager.getInstance().close(con, ps);
        }
    }

    /**
     * Find and return order by id.
     *
     */
    public static Order findOrder (int orderId) throws GlobalException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_FIND_ORDER_BY_ID);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                order = extractOrder(rs);
            }

        } catch (SQLException ex) {
            LOG.error("#findOrder error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find order.");
        } finally {
            DBManager.getInstance().close(con, ps, rs);
        }
        return order;
    }

    /**
     * Find and return all orders by user id.
     *
     * @return List of order item entities.
     */
    public static List<Order> findUserOrders(int userId) throws GlobalException {
        List <Order> orderList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_FIND_ALL_ORDERS_BY_USER_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderList.add(extractOrder(rs));
            }
        } catch (SQLException ex) {
            LOG.error("#findUserOrders error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find orders.");
        } finally {
            DBManager.getInstance().close(con, ps, rs);
        }
        return orderList;
    }

    /**
     * Find and return all orders by book id.
     *
     * @return List of order item entities.
     */
    public static List<Order> findBookOrders(int bookId) throws GlobalException {
        List <Order> orderList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            ps = con.prepareStatement(MYSQL_FIND_ALL_ORDERS_BY_BOOK_ID);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderList.add(extractOrder(rs));
            }
        } catch (SQLException ex) {
            LOG.error("#findBookOrders error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find orders.");
        } finally {
            DBManager.getInstance().close(con, ps, rs);
        }
        return orderList;
    }



    private static Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt(DBFields.ENTITY_ID));
        order.setUserId(rs.getInt(DBFields.ORDER_USER_ID));
        order.setBookId(rs.getInt(DBFields.ORDER_BOOK_ID));
        order.setStatusId(rs.getInt(DBFields.ORDER_STATUS_ID));
        order.setDebt(rs.getInt(DBFields.ORDER_DEBT));
        order.setDayCount(rs.getInt(DBFields.ORDER_DAY_COUNT));
        order.setDate(rs.getString(DBFields.ORDER_DATE));

        return order;
    }

    private OrderDAO() {
        throw new IllegalStateException("Utility class");
    }
}
