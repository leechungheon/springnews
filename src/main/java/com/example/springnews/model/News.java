package com.example.springnews.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class News {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String writer;
  String title;
  String content;
  @CreationTimestamp
  LocalDateTime writedate;
  int cnt;
}
