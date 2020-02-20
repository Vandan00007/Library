package com.vandan.patel.library.controller;

import com.vandan.patel.library.data.BookFormService;

import com.vandan.patel.library.model.BookForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookFormController {

    private final Logger logger= LoggerFactory.getLogger(BookFormController.class);

    private static final String[] languages={"English","French"};

    private BookFormService bookFormService;

    public BookFormController(BookFormService bookFormService){
        this.bookFormService=bookFormService;
    }



    @GetMapping(value = {"/","/Index.do"})
    public String index(){
        logger.trace("index() is called");
        return "Index";
    }
    @RequestMapping(value = {"/AddBook.do"},method = RequestMethod.GET)
    public ModelAndView addBook(){
        logger.trace("addBook() is called");
        ModelAndView modelAndView = new ModelAndView("AddBook","form",new BookForm());
        modelAndView.addObject("languages",languages);
        return modelAndView;
    }

    @RequestMapping(value = {"/InsertBook.do"},method = RequestMethod.POST)
    public String insertBook(@Validated @ModelAttribute("form") BookForm form, BindingResult bindingResult, Model model) {
        logger.trace("insertBook() is called");
        //correcting the checkbox input values;
        logger.debug("received usedorNew=" + form.getUsedOrNew());
        form.setUsedOrNew((form.getUsedOrNew() == null) ? "no" : "yes");
        //checking for the input validation errors
        if (!bindingResult.hasErrors()) {
            logger.trace("the user inputs are correct");
            bookFormService.insertBookForm(form);
            return "redirect:ConfirmInsert.do?id=" + form.getId();
        } else {
            logger.trace("input validation errors");
            model.addAttribute("form", form);
            model.addAttribute("languages", languages);
            return "AddBook";
        }
    }
    @RequestMapping("/ConfirmInsert.do")
    public String confirmInsert(@RequestParam(name = "id") String strId, Model model){
        logger.trace("confirmInsert() is called");
        if (strId == null || strId.isEmpty()) {
            logger.trace("no id in the request");
            return "DataNotFound";
        } else {
            try {
                int id = Integer.parseInt(strId);
                logger.trace("looking for the data in the database");
                BookForm form = bookFormService.getBookForm(id);
                if (form == null) {
                    logger.trace("no data for this id=" + id);
                    return "DataNotFound";
                } else {
                    logger.trace("showing the data");
                    model.addAttribute("book", form);
                    return "ConfirmInsert";
                }
                }
            catch (NumberFormatException e) {
                logger.trace("the id in not an integer");
                return "DataNotFound";
            }
        }
    }

    @RequestMapping("/ListBooks.do")
    public ModelAndView listBooks(){
        logger.trace("listBooks() is called");
        List<BookForm> list = bookFormService.getAllBookForm();
        return new ModelAndView("ListBooks","books",list);
    }

    @RequestMapping("/DeleteAll.do")
    public String deleteAll(){
        logger.trace("deleteAll() is called");
        bookFormService.deleteAllBookForm();
        return "redirect:ListBooks.do";
    }

    @RequestMapping("BookDetails.do")
    public String bookDetails(@RequestParam String id, Model model){
        logger.trace("bookDetails() is called");
        try{
            BookForm form = bookFormService.getBookForm(Integer.parseInt(id));
            if(form != null){
                model.addAttribute("book",form);
                return "BookDetails";// show the book data in the form to edit
            }else{
                logger.trace("no data for this id="+id);
                return "DataNotFound";
            }
        } catch (NumberFormatException e){
            logger.trace("the is is missing or not an integer");
            return "DataNotFound";
        }

    }

    // a user clicks "Delete " link (in the table) to "DeleteBook"
    @RequestMapping("/DeleteBook.do")
    public String deleteBook(@RequestParam String id,Model model){
        logger.trace("deleteBook() is called");
        try{
            BookForm form = bookFormService.getBookForm(Integer.parseInt(id));
            if(form!=null){
                model.addAttribute("book",form);
                return "DeleteBook";// ask "Do you really want to remove?"
            }else{
                return "redirect:ListBooks.do";
            }
        }catch (NumberFormatException e){
            return "redirect:ListBooks.do";
        }
    }
    // a user clicks "Edit link (in the table) to EditBook"
    @RequestMapping("/EditBook.do")
    public String editBook(@RequestParam String id,Model model){
        logger.trace("editBook() is called");
        try{
            BookForm form = bookFormService.getBookForm(Integer.parseInt(id));
            if(form!=null){
                model.addAttribute("form",form);
                model.addAttribute("languages",languages);
                return "EditBook";
            }else {
                logger.trace("no data for this id="+id);
                return "redirect:ListBooks.do";
            }
        }catch (NumberFormatException e){
            logger.trace("the id is missing or not an integer");
            return "redirect:ListBooks.do";
        }
    }

    //the form submits the data to "UpdateBook"
    @RequestMapping("/UpdateBook.do")
    public String updateBook(@Validated @ModelAttribute("form")BookForm form,BindingResult bindingResult,Model model){
        logger.trace("updateBook() is called");
        //correcting the checkbox input values
        form.setUsedOrNew((form.getUsedOrNew()==null)?"no":"yes");
        // checking for the input validation errors
        if (!bindingResult.hasErrors()) {
            logger.trace("the user inputs are correct");
            bookFormService.updateBookForm(form);
            return "redirect:BookDetails.do?id=" + form.getId();
        } else {
            logger.trace("input validation errors");
            model.addAttribute("form", form);
            model.addAttribute("languages", languages);
            return "EditBook";
        }
    }
}
