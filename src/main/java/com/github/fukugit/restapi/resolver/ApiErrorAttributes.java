package com.github.fukugit.restapi.resolver;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * エラーレスポンスを定義します。
 * <p>バリデーションエラー時は下記のレスポンスを返却します。
 * {@code
 * {
 *   "timestamp": "2020-05-14 16:04:46.863",
 *   "status": 400,
 *   "message":[
 *     {
 *       "field": "title",
 *       "message": "must not be empty"
 *     }
 *   ],
 *   "path": "/books/"
 * }}
 */
@Component
public class ApiErrorAttributes extends DefaultErrorAttributes {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

  @Override
  public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
    Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
    errorAttributes.replace("timestamp", formatter.format(LocalDateTime.now()));
    errorAttributes.remove("error");

    if (errorAttributes.containsKey("errors")) {
      List<FieldError> originalError = (List<FieldError>)errorAttributes.get("errors");
      List<Map<String, String>> errors = originalError.stream().map(map -> {
        Map<String, String> result = new HashMap<>();
        result.put("message", map.getDefaultMessage());
        result.put("field", map.getField());
        return result;
      }).collect(Collectors.toList());
      errorAttributes.remove("errors");
      errorAttributes.put("message", errors);
    }
    return errorAttributes;
  }
}
