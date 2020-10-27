package ua.kram.tolm.web.command.admin;

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
import ua.kram.tolm.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowOrderCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ShowOrderCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        String orderId = req.getParameter("orderId");
        LOG.info("order id ==> " +orderId);

        Order order = OrderDAO.findOrder(Integer.parseInt(orderId));
        req.setAttribute("order", order);

        Book book = BooksDAO.findBook(order.getBookId());
        LOG.info("book id => " + book.getId());
        req.setAttribute("book", book);

        User user = UserDAO.findUser(order.getUserId());
        LOG.info("user id => " + user.getId());
        req.setAttribute("user", user);

        Map<Integer, Status> statusMap = StatusDAO.findAllStatuses();
        req.setAttribute("statusMap", statusMap);

        return Link.SHOW_ORDER;
    }

}
