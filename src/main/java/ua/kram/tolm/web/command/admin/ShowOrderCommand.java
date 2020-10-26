package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.OrderDAO;
import ua.kram.tolm.db.entity.Order;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;
import ua.kram.tolm.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowOrderCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ShowOrderCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        String orderId = req.getParameter("orderId");
        LOG.info("order id ==> " +orderId);
        Order order = OrderDAO.findOrder(Integer.parseInt(orderId));
        req.setAttribute("order", order);

        return Link.SHOW_ORDER;
    }
}
