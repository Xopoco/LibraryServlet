package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.BooksDAO;
import ua.kram.tolm.db.dao.OrderDAO;
import ua.kram.tolm.db.dao.StatusDAO;
import ua.kram.tolm.db.dao.UserDAO;
import ua.kram.tolm.db.entity.Book;
import ua.kram.tolm.db.entity.Order;
import ua.kram.tolm.db.entity.Status;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowSettingsCommand extends Command{
    private static final Logger LOG = Logger.getLogger(ShowSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        User user = UserDAO.findUser((Integer) req.getSession().getAttribute("userId"));
        req.setAttribute("user", user);

        List<Order> orders = OrderDAO.findUserOrders(user.getId());
        req.setAttribute("orders", orders);

        Map<Integer, Book> bookMap = new HashMap<>();
        for (Order o : orders) {
            Book b = BooksDAO.findBook(o.getBookId());
            bookMap.put(b.getId(), b);
        }
        req.setAttribute("bookMap", bookMap);

        Map<Integer, Status> statusMap = StatusDAO.findAllStatuses();
        req.setAttribute("statusMap", statusMap);

        return Link.SETTINGS;
    }
}
