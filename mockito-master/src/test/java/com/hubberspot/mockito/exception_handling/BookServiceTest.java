package com.hubberspot.mockito.exception_handling;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testTotalPriceOfBooks() throws SQLException {
		when(bookRepository.findAllBooks()).thenThrow(SQLException.class);
		exceptionRule.expect(DatabaseReadException.class);
		bookService.getTotalPriceOfBooks();
		// assertThrows(DatabaseReadException.class, () ->
		// bookService.getTotalPriceOfBooks());
	}

	@Test(expected = DatabaseReadException.class)
	public void testTotalPriceOfBooks3() throws SQLException {
//		when(bookRepository.findAllBooks()).thenThrow(SQLException.class);
		given(bookRepository.findAllBooks()).willThrow(SQLException.class);
		bookService.getTotalPriceOfBooks();
	}

	@Test(expected = DatabaseReadException.class)
	public void testTotalPriceOfBooks1() throws SQLException {
		when(bookRepository.findAllBooks()).thenThrow(new SQLException("Database not available"));
	//	assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
		bookService.getTotalPriceOfBooks();
	}

	@Test(expected = DatabaseWriteException.class)
	public void testAddBook() throws SQLException {
		Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
		doThrow(SQLException.class).when(bookRepository).save(book);
		//assertThrows(DatabaseWriteException.class, () -> bookService.addBook(book));
		 bookService.addBook(book);
	}

	@Test(expected = DatabaseReadException.class)
	public void testTotalPriceOfBooks2() throws SQLException {
		when(bookRepository.findAllBooks()).thenThrow(new SQLException("Database not available"));
		//assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
		bookService.getTotalPriceOfBooks();
	}

}
