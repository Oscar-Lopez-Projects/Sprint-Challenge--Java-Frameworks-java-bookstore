package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceImplTest
{   @Autowired
    private SectionService sectionService;

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);
//        List<Book> myList = bookService.findAll();
//        for(Book u : myList){
//            System.out.println(u.getBookid()+ " "+ u.getTitle());
//        }
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void B_findAll()
    {
        Assert.assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void findBookById()
    {
        Assert.assertEquals("Flatterland", bookService.findBookById(26).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void notFindBookById()
    {
    }

    @Test
    public void delete()
    {
        bookService.delete(26);
        Assert.assertEquals("Flatterlands", bookService.findBookById(26));
    }

    @Test
    public void save()
    {
        Section fiction = new Section("Fiction");
        sectionService.save(fiction);
        fiction.setSectionid(21);
        String bookName = "Calling Houston Home";
        Book b6 = new Book(bookName, "1885171384134", 2001, fiction);
        bookService.save(b6);
        Book addBook = bookService.save(b6);
        Assert.assertNotNull(addBook);
        Assert.assertEquals(bookName, addBook.getTitle());
    }

    @Test
    public void update()
    {
    }

    @Test
    public void deleteAll()
    {
        bookService.deleteAll();
        Assert.assertEquals(0, bookService.findAll().size());
    }
}