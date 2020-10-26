package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.OrderDAO;
import ua.kram.tolm.db.entity.Order;
import ua.kram.tolm.exception.GlobalException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderBookCommand extends Command {
    private static final Logger LOG = Logger.getLogger(OrderBookCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        Order order = createOrder(req);
        OrderDAO.insertOrder(order);

        return new ShowBooksCommand().execute(req, resp);
    }

    private Order createOrder (HttpServletRequest req){
        Order order = new Order();
        HttpSession session = req.getSession();


        Integer userId = (Integer) session.getAttribute("userId");
        order.setUserId(userId);

        String bookId = req.getParameter("bookId");
        order.setBookId(Integer.parseInt(bookId));

        order.setDayCount(1);
        order.setStatusId(1);

        return order;
    }
}
