package com.github.fukugit.restapi.endpoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookForm {
  public interface Insert{};
  public interface Update{};

  @NotNull(groups = {Update.class})
  Integer id;
  @NotEmpty(groups = {Insert.class, Update.class})
  String title;

  public Book toBook() {
    return new Book(id, title);
  }
}
