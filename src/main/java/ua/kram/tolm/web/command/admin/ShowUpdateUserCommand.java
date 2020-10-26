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

public class ShowUpdateUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ShowUpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("ShowUpdateUserAction #execute");

        String userId = req.getParameter("userId");
        LOG.info("userId = > " + userId);

        if(userId == null){
            throw new GlobalException("can't find userId parameter");
        }

        User user = UserDAO.findUser(Integer.parseInt(userId));
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

        return Link.UPDATE_USER;
    }
}
