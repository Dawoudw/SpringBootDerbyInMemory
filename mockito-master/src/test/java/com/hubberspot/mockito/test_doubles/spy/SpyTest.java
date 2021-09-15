package com.hubberspot.mockito.test_doubles.spy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
 
@RunWith(MockitoJUnitRunner.class)
 @ExtendWith(MockitoExtension.class)
public class SpyTest {

	@Test
	public void demoSpy() {
		BookRepositorySpy bookRepositorySpy = new BookRepositorySpy();
		BookService bookService = new BookService(bookRepositorySpy);

		Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());

		bookService.addBook(book1);
		assertEquals(0, bookRepositorySpy.timesCalled());

		bookService.addBook(book2);
		assertEquals(1, bookRepositorySpy.timesCalled());

		// assertTrue(bookRepositorySpy.calledWith(book2));

	}

	@Test
	public void demoSpyWithMockito() {
		BookRepository bookRepositorySpy = spy(BookRepository.class);
		BookService bookService = new BookService(bookRepositorySpy);

		Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());

		bookService.addBook(book1); // return
		bookService.addBook(book2); // save will be called

		verify(bookRepositorySpy, Mockito.times(1)).save(book2);
		verify(bookRepositorySpy, Mockito.times(0)).save(book1);

	}

	@Spy
	BookRepository bookRepositorySpy1 ;

	@Test
	public void demoSpyWithMockito1() {

		BookService bookService = new BookService(bookRepositorySpy1);

		Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());

		bookService.addBook(book1); // return
		bookService.addBook(book2); // save will be called

		verify(bookRepositorySpy1, Mockito.times(1)).save(book2);
		verify(bookRepositorySpy1, Mockito.times(0)).save(book1);

	}

}
