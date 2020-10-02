package ua.kram.tolm.db;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.entity.Order;
import ua.kram.tolm.exception.GlobalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final Logger LOG = Logger.getLogger(OrderDAO.class);

    private static final String MYSQL_FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE " + DBFields.ENTITY_ID + "=?";
    private static final String MYSQL_FIND_ALL_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE " + DBFields.ORDER_USER_ID + "=?";
    private static final String MYSQL_FIND_ALL_ORDERS_BY_BOOK_ID = "SELECT * FROM orders WHERE " + DBFields.ORDER_BOOK_ID + "=?";
    private static final String MYSQL_UPDATE_ORDER_BY_ID = "UPDATE orders SET " +
            DBFields.ORDER_USER_ID + "=?, " +
            DBFields.ORDER_BOOK_ID + "=?, " +
            DBFields.ORDER_PRICE + "=?, " +
            DBFields.ORDER_DAY_COUNT + "=?, " +
            DBFields.ORDER_DATE + "=? WHERE id=?";
    private static final String MYSQL_INSERT_ORDER = "INSERT INTO orders (" +
            DBFields.ORDER_USER_ID + ", " +
            DBFields.ORDER_BOOK_ID + ", " +
            DBFields.ORDER_PRICE + ", " +
            DBFields.ORDER_DAY_COUNT + ", " +
            DBFields.ORDER_DATE + ") VALUES (?, ?, ?, ?, ?)";

    /**
     * Update order.
     * @param order - order to update.
     *
     */
    public static void updateOrder(Order order) throws GlobalException {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(MYSQL_UPDATE_ORDER_BY_ID);
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getBookId());
            ps.setInt(3, order.getPrice());
            ps.setInt(4, order.getDayCount());
            ps.setString(5, order.getDate());
            ps.setInt(6, order.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            LOG.error("#updateOrder error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't update order.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Delete order by id.
     * @param orderId - order id to delete.
     *
     */
    public static void deleteOrder (int orderId) throws GlobalException {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(MYSQL_DELETE_ORDER_BY_ID);
            ps.setInt(1, orderId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            LOG.error("#deleteOrder error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't delete order.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Insert order to DB.
     * @param order  order to insert.
     *
     */
    public static void insertOrder(Order order) throws GlobalException {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(MYSQL_INSERT_ORDER);
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(4, order.getBookId());
            pstmt.setInt(2, order.getPrice());
            pstmt.setInt(3, order.getDayCount());
            pstmt.setString(5, order.getDate());
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            LOG.error("#insertOrder error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't insert order.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Find and return order by id.
     *
     */
    public static Order findOrder (int orderId) throws GlobalException {
        Connection con = null;
        Order order = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(MYSQL_FIND_ORDER_BY_ID);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = extractOrder(rs);
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            LOG.error("#findOrder error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find order.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
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
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(MYSQL_FIND_ALL_ORDERS_BY_USER_ID);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderList.add(extractOrder(rs));
            }
        } catch (SQLException ex) {
            LOG.error("#findUserOrders error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find orders.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
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
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(MYSQL_FIND_ALL_ORDERS_BY_BOOK_ID);
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderList.add(extractOrder(rs));
            }
        } catch (SQLException ex) {
            LOG.error("#findBookOrders error", ex);
            DBManager.getInstance().rollbackAndClose(con);
            throw new GlobalException("Can't find orders.");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return orderList;
    }



    private static Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt(DBFields.ENTITY_ID));
        order.setUserId(rs.getInt(DBFields.ORDER_USER_ID));
        order.setBookId(rs.getInt(DBFields.ORDER_BOOK_ID));
        order.setPrice(rs.getInt(DBFields.ORDER_PRICE));
        order.setDayCount(rs.getInt(DBFields.ORDER_DAY_COUNT));
        order.setDate(rs.getString(DBFields.ORDER_DATE));

        return order;
    }
}
