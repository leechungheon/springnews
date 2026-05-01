package com.example.springnews.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
public class News {
  @Id
  int id;
  String writer;
  String title;
  String content;
  @CreatedDate
  LocalDateTime writedate;
  int cnt;
}
