package com.radford.ratings.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radford.ratings.exception.ResourceNotFoundException;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/book")
	public Page<Book> getAllBookRatings(
			@RequestParam(defaultValue = "1") Integer pageNumber, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "author") String sortBy,
            @RequestParam(required = false) String author
    ) {
		pageNumber--;
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		if(author != null) {
			return bookRepository.findAllByAuthor(author, page);
		} else {			
			return bookRepository.findAll(page);
		}
	}
	
	
	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable String id) throws ResourceNotFoundException {
		Optional<Book> bookIfExists = bookRepository.findById(id);
		if(bookIfExists.isPresent()) {
			return bookIfExists.get();
		} else {
			throw new ResourceNotFoundException("Book not found for this id :: " + id);
		}
	}
	
	@PostMapping("/book")
	public Book createBookRating(@RequestBody Book book) {
		Book response = bookRepository.save(book);
		return response;
	}
	
	@PutMapping("/book/{id}")
	public Book updateBook(@PathVariable String id, @RequestBody Book book) throws ResourceNotFoundException {
		if(bookRepository.existsById(id)) {
			book.setId(id);
			Book response = bookRepository.save(book);
			return response;
		} else {
			throw new ResourceNotFoundException("Book not found for this id :: " + id);
		}
	}
	
	@DeleteMapping("/book/{id}")
	public void deleteBook(@PathVariable String id) throws ResourceNotFoundException {
		if(bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Book not found for this id :: " + id);
		}
	}
}
