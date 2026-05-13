package com.example.springnews.controller;

import com.example.springnews.model.News;
import com.example.springnews.repository.NewsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

  @PostMapping("/news/write")
  public String createNews(News news){
    News newNews = new News();
    newNews.setTitle(news.getTitle());
    newNews.setWriter(news.getWriter());
    newNews.setContent(news.getContent());
    newsRepository.save(newNews);
    return "redirect:/newsmain";
  }

  @GetMapping("/news/getDetail")
  @ResponseBody
  public News getNewsDetail(@RequestParam("id") int id){
    News news = newsRepository.findNewsById(id);
    news.setCnt(news.getCnt()+1);
    newsRepository.save(news);
    return news;
  }

  @PostMapping("/news/update")
  public String updateNews(News news){
    News savedNews = newsRepository.findNewsById(news.getId());
    savedNews.setTitle(news.getTitle());
    savedNews.setWriter(news.getWriter());
    savedNews.setContent(news.getContent());
    newsRepository.save(savedNews);
    return "redirect:/newsmain";
  }

  @GetMapping("/news/delete")
  public String deleteNews(@RequestParam("id") int id){
    newsRepository.deleteById(id);
    return "redirect:/newsmain";
  }
}
