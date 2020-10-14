package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.AuthorDAO;
import ua.kram.tolm.db.dao.BooksDAO;
import ua.kram.tolm.db.entity.Author;
import ua.kram.tolm.db.entity.Book;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ShowBooksCommand extends Command{
    private static final Logger LOG = Logger.getLogger(ShowBooksCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        Map<Integer, Author> authors = AuthorDAO.findAllAuthors();
        req.setAttribute("authors", authors);

        List<Book> books = BooksDAO.findAllBooks();
        req.setAttribute("books", books);

        return Link.SHOW_BOOKS;
    }
}
