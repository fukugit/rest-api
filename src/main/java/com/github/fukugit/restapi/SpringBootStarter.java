package com.github.fukugit.restapi;

import com.github.fukugit.restapi.listener.SampleApplicationStartedEvent;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * {@code @SpringBootApplication}の仕様は、このアノテーションをつけることで以下のアノテーションの機能を踏襲します。
 * <pre>
 * {@code @EnableAutoConfiguration}は、外部で定義したConfigの読み込みを可能にします。
 * {@code @ComponentScan}は、指定したpackage配下をスキャン可能にします。（要は{@code @Autowired}が使えるようにします）
 * {@code @Configuration}は、Configクラスとして定義します。
 * </pre>
 *
 * @see <a href="https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/html/using-spring-boot.html#using-boot-using-springbootapplication-annotation">Oficial document</a>
 **/
@SpringBootApplication
public class SpringBootStarter {

  public static void main(String[] args) {
    // Builderパターンで設定可能です。
    // https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/html/spring-boot-features.html#boot-features-fluent-builder-api
    new SpringApplicationBuilder()
      .listeners()
      .sources(SpringBootStarter.class)
      .bannerMode(Banner.Mode.CONSOLE)
      // リスナーの登録です。
      // https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/html/spring-boot-features.html#boot-features-application-events-and-listeners
      .listeners(new SampleApplicationStartedEvent())
      .run(args);
  }
}

