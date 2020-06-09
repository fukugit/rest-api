package com.github.fukugit.restapi.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  private final BookUseCase useCase;

  /**
   * 本の一覧表示します。
   * http://localhost:8080/books
   * @return {@link ResponseEntity}
   */
  @GetMapping
  public ResponseEntity<List<Book>> list() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(useCase.getAll());
  }

  /**
   * 本をID検索します。
   * http://localhost:8080/books/1
   * @return {@link ResponseEntity}
   */
  @GetMapping("{id}")
  public ResponseEntity<Book> get(@PathVariable("id") int id) {
    return ResponseEntity.status(HttpStatus.OK).body(useCase.get(id));
  }

  @PutMapping
  public ResponseEntity put(@RequestBody @Valid Book book) {
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * 本をサンプル(PDF)を返却します。
   * http://localhost:8080/books/1/content
   * @return /resources/book.pdf の内容
   */
  @GetMapping("{id}/content")
  public ResponseEntity<InputStreamResource> getContent(@PathVariable("id") int id) throws IOException {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    File file = new File(classLoader.getResource("book.pdf").getFile());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDisposition(
      ContentDisposition.builder("attachment").filename("sample.pdf").build()
    );
    headers.setContentLanguage(Locale.JAPAN);
    headers.setExpires(-1); // キャッシュではなく常に最新を取得します。
    return new ResponseEntity<>(
      new InputStreamResource(new FileInputStream(file)), headers, HttpStatus.OK
    );
  }

}
