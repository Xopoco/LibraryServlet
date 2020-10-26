package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.BooksDAO;
import ua.kram.tolm.db.entity.Book;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateBookCommand extends Command {
    private static final Logger LOG = Logger.getLogger(CreateBookCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        Book book = createBook(req);
        BooksDAO.insertBook(book);

        return new ManagerCommand().execute(req, resp);
    }

    private Book createBook (HttpServletRequest req) {
        Book book = new Book();

        String bookTitle = req.getParameter("bookTitle");
        book.setName(bookTitle);

        String bookEdition = req.getParameter("bookEdition");
        book.setEdition(bookEdition);

        String editionDate = req.getParameter("editionDate");
        book.setDateOfEdition(editionDate);

        String bookReview = req.getParameter("bookReview");
        book.setReview(bookReview);

        String authorId = req.getParameter("authorId");
        book.setAuthorId(Integer.parseInt(authorId));

        String genreId = req.getParameter("genreId");
        book.setGenreId(Integer.parseInt(genreId));

        String bookPrice = req.getParameter("bookPrice");
        book.setPrice(Integer.parseInt(bookPrice));

        book.setCount(1);

        return book;
    }
}
