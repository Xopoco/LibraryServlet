package ua.kram.tolm.web.command.Admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DAO.BooksDAO;
import ua.kram.tolm.db.DAO.OrderDAO;
import ua.kram.tolm.db.DAO.UserDAO;
import ua.kram.tolm.db.entity.Book;
import ua.kram.tolm.db.entity.Order;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;
import ua.kram.tolm.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ManagerCommand.class);


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("ManagerCommand#execute");

        List<User> users = UserDAO.findAllUsers();
        req.setAttribute("users", users);

        List<Order> orders = OrderDAO.findAllOrders();
        req.setAttribute("orders", orders);

        List<Order> outOfTimeOrders = getOutOfTimeOrders(orders);
        req.setAttribute("outOfTimeOrders", outOfTimeOrders);

        List<Book> books = BooksDAO.findAllBooks();
        req.setAttribute("books", books);

        Map<Order, Book> orderBookMap = getOrderBookMap(orders, books);
        req.setAttribute("orderBookMap", orderBookMap);

        Map<Order, User> orderUserMap = getOrderUserMap(orders, users);
        req.setAttribute("orderUserMap", orderUserMap);

        return Link.MANAGER;
    }

    private List<Order> getOutOfTimeOrders(List<Order> orders) {
        List<Order> result = new ArrayList<>();

        for (Order o : orders){
            if (o.getDebt() > 0){
                result.add(o);
            }
        }

        return result;
    }

    private Map<Order, Book> getOrderBookMap (List<Order> orders, List<Book> books) throws GlobalException {
        Map<Order, Book> orderBookMap = new HashMap<>();

        for (Order o : orders) {
            Book book = null;

            for (Book b : books) {
                if (b.getId() == o.getBookId()){
                    book = b;
                }
            }
            if (book == null) {
                throw new GlobalException("Order error.");
            }

            orderBookMap.put(o, book);
        }

        return orderBookMap;
    }

    private Map<Order, User> getOrderUserMap (List<Order> orders, List<User> users) throws GlobalException {
        Map<Order, User> orderBookMap = new HashMap<>();

        for (Order o : orders) {
            User user = null;

            for (User u : users) {
                if (u.getId() == o.getBookId()){
                    user = u;
                }
            }
            if (user == null) {
                throw new GlobalException("Order error.");
            }

            orderBookMap.put(o, user);
        }

        return orderBookMap;
    }
}
