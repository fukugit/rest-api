package com.github.fukugit.restapi.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.Test;

// JUnit5を使うので SpringRunnerは使えない。
// SpringRunnerを使うことでJUnit内でSpringアノテーション @Autowired等 が使えるようになります。
//@RunWith(SpringRunner.class)
// JUnit用に設定クラスを指定する場合は@ContextConfigurationを使います。
//@ContextConfiguration(classes = XXXX.class)
@WebAppConfiguration
public class BookControllerTest {

  @InjectMocks
  BookController bookController;

  @Mock
  private BookUseCase useCase;

  // mockMvc.perform で実際にHTTPリクエストするようなテストをすることが出来ます。
  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    // @Mock のオブジェクトを初期化するために必要です。 initMocksが無いと@Mock のオブジェクトがNullになります。
    initMocks(this);
    bookController = new BookController(useCase);
    mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
  }

  @Test // こいつはJUnit5の@Test
  public void 全件取得() throws Exception {
    // Given
    when(useCase.getAll()).thenReturn(
      List.of(new Book(1, "book1"), new Book(2, "book2")));

    // When
    ResultActions result =
      mockMvc.perform(MockMvcRequestBuilders.get("/books")).andExpect(status().isOk());

    // Then
    ObjectMapper mapper = new ObjectMapper();
    // Jacksonを使ってJSON＞Javaクラスに変換します。
    List list = mapper.readValue(result.andReturn().getResponse().getContentAsString(), List.class);
    assertEquals(list.size(), 2);
  }

  /**
   * ArgumentCaptor を使ってコントローラ内のロジックの確認もしています。
   */
  @Test
  public void 一件取得() throws Exception {
    // Given
    when(useCase.get(anyInt())).thenReturn(new Book(1, "book1"));

    // When
    ResultActions result =
      mockMvc.perform(MockMvcRequestBuilders.get("/books/1")).andExpect(status().isOk());

    // Then
    ObjectMapper mapper = new ObjectMapper();
    // Jacksonを使ってJSON＞Javaクラスに変換します。
    Book book = mapper.readValue(result.andReturn().getResponse().getContentAsString(), Book.class);
    assertEquals(book.getId(), 1);

    // ArgumentCaptorを使ってコントローラ内で使用しているロジックに適切な引数が渡っているかをチェックします。
    var argumentCaptor = ArgumentCaptor.forClass(Integer.class);
    verify(useCase, times(1)).get(argumentCaptor.capture());
    assertEquals(argumentCaptor.getValue(), 1);
  }
}
