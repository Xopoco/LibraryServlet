package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.OrderDAO;
import ua.kram.tolm.db.entity.Order;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateOrderCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreateOrderCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        Order order = createOrder(req);
        OrderDAO.insertOrder(order);

        return new ManagerCommand().execute(req, resp);
    }

    private Order createOrder (HttpServletRequest req){
        Order order = new Order();

        String userId = req.getParameter("userId");
        order.setUserId(Integer.parseInt(userId));

        String bookId = req.getParameter("bookId");
        order.setBookId(Integer.parseInt(bookId));

        String count = req.getParameter("count");
        order.setDayCount(Integer.parseInt(count));

        String statusId = req.getParameter("statusId");
        order.setStatusId(Integer.parseInt(statusId));

        return order;
    }
}
