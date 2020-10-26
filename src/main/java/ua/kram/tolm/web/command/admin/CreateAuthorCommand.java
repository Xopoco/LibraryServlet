package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.AuthorDAO;
import ua.kram.tolm.db.entity.Author;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAuthorCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreateAuthorCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        Author author = createAuthor(req);
        AuthorDAO.insertAuthor(author);

        return new ManagerCommand().execute(req, resp);
    }

    private Author createAuthor (HttpServletRequest req) {
        Author author = new Author();

        String authorFirstName = req.getParameter("authorFirstName");
        author.setFirstName(authorFirstName);

        String authorLastName = req.getParameter("authorLastName");
        author.setLastName(authorLastName);

        String date = req.getParameter("year") + "-" + req.getParameter("month") + "-" + req.getParameter("day");
        author.setDateOfBirth(date);

        return author;
    }
}
