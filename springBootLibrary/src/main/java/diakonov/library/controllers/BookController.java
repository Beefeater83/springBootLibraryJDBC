package diakonov.library.controllers;

import diakonov.library.dao.BookDAO;
import diakonov.library.dao.PersonDAO;
import diakonov.library.models.Book;
import diakonov.library.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {

        return "books/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("selectedPerson") Person selectedPerson) {
        model.addAttribute("book", bookDAO.show(id));
        Optional<Person> bookOwner = bookDAO.findBookReader(id);
        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
         else
            model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/add")
    public String addBookReader(@PathVariable("id") int bookId, @ModelAttribute("selectedPerson") Person selectedPerson) {
        bookDAO.addReaderForBook(bookId, selectedPerson);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{id}/releaseBook")
    public String releaseBook(@PathVariable("id") int bookId) {
        bookDAO.releaseBook(bookId);
        return "redirect:/books/" + bookId;
    }
}
