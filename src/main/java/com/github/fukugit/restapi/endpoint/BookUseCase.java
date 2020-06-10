package com.github.fukugit.restapi.endpoint;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookUseCase {

  public List<Book> getAll() {
    List<Book> list = new ArrayList<>();
    list.add(new Book(1, "book1"));
    list.add(new Book(2, "book2"));
    return List.of(new Book(1, "book1"), new Book(2, "book2"));
  }

  public Book get(int id) {
    return new Book(id, "book" + id);
  }

  public void regist(Book book) {
    // 空実装
  }

  public void update(Book book) {
    // 空実装
  }

  public void delete(Integer id) {
    // 空実装
  }
}
