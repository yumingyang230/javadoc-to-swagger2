package cn.yumy.doc2swagger.spring.examples.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"current", "size", "orders", "hitCount", "searchCount", "pages"})
public class MyPage<T> extends Page<T> {
}
