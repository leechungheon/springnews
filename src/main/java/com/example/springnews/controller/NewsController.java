package com.example.springnews.controller;

import com.example.springnews.model.News;
import com.example.springnews.repository.NewsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {
  @Autowired
  NewsRepository newsRepository;

  @RequestMapping("/newsmain")
  public String list(Model model){
    List<News> list = newsRepository.findAll();
    model.addAttribute("newsList", list);
    return "board";
  }
}
