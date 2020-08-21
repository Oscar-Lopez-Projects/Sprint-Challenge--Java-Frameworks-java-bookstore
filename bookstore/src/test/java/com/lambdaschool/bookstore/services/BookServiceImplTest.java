package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void findAll()
    {
        Assert.assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void findBookById()
    {
        Assert. assertEquals("Flatterland", bookService.findBookById(26).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void notFindBookById()
    {
        Assert.assertEquals("Flatterland", bookService.findBookById(100).getTitle());
    }

    @Test
    public void delete()
    {
        bookService.delete(27);
        Assert.assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void save()
    {
        Section s1 = new Section("Fiction");
        s1.setSectionid(21);

        Book b1 = new Book("Lauren's book", "9780738206555", 2020, s1);
        b1 = bookService.save(b1);

        Assert.assertEquals("Lauren's book", b1.getTitle());
    }

//    @Test
//    public void G_update()
//    {
//    }
//
//
//    @Test
//    public void ZY_deleteAll()
//    {
//        bookService.deleteAll();
//        Assert.assertEquals(0, bookService.findAll().size());
//    }
}