package com.github.fukugit.restapi.endpoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.AssertTrue;
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

  /**
   * {@code @AssertTrue}を使ってフィールドを複合的にチェックします。
   * <p>メソッド名は、isXXXX としないと動作しません。他設定は特に不要です。
   * @return titleにid値が含まれていない場合はfalseとします。
   */
  @AssertTrue(message = "error", groups = {Update.class})
  boolean isTitle() {
    if (StringUtils.isEmpty(title) || id == null) {
      return true;
    }
    return title.contains(id.toString());
  }

  public Book toBook() {
    return new Book(id, title);
  }


}
