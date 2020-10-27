package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.*;
import ua.kram.tolm.db.entity.*;
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

        Map<Integer, Author> authors = AuthorDAO.findAllAuthors();
        req.setAttribute("authors", authors);

        List<User> users = UserDAO.findAllUsers();
        req.setAttribute("users", users);

        Map<Integer, User> userMap = getOrderUserMap(users);
        req.setAttribute("userMap", userMap);

        List<Order> orders = OrderDAO.findAllOrders();
        req.setAttribute("orders", orders);

        List<Order> outOfTimeOrders = getOutOfTimeOrders(orders);
        req.setAttribute("outOfTimeOrders", outOfTimeOrders);

        List<Book> books = BooksDAO.findAllBooks();
        req.setAttribute("books", books);

        Map<Integer, Book> bookMap = getOrderBookMap(books);
        req.setAttribute("bookMap", bookMap);

        Map<Integer, Integer> booksCount = getBooksCount(orders);
        req.setAttribute("booksCount", booksCount);

        Map<Integer, Genre> genreMap = GenreDAO.findAllGenres();
        req.setAttribute("genreMap", genreMap);

        Map<Integer, Status> statusMap = StatusDAO.findAllStatuses();
        req.setAttribute("statusMap", statusMap);

        return Link.MANAGER;
    }

    private Map<Integer, Integer> getBooksCount (List<Order> orders) {
        Map<Integer, Integer> map = new HashMap<>();
            for (Order o : orders) {
                if (map.containsKey(o.getUserId())) {
                    map.put(o.getUserId(), map.get(o.getUserId()) + 1);
                } else {
                    map.put(o.getUserId(), 1);
                }
            }
        return map;
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

    private Map<Integer, Book> getOrderBookMap (List<Book> books) {
        Map<Integer, Book> orderBookMap = new HashMap<>();

        for (Book b : books){
            orderBookMap.put(b.getId(), b);
        }

        return orderBookMap;
    }

    private Map<Integer, User> getOrderUserMap (List<User> users){
        Map<Integer, User> orderBookMap = new HashMap<>();

        for (User u : users) {
            orderBookMap.put(u.getId(), u);
        }

        return orderBookMap;
    }
}
