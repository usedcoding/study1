package com.example.study1;

import com.example.study1.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Study1ApplicationTests {

	@Autowired
	private  ArticleService articleService;

	@Test
	void test1() {
		articleService.create("제목 1", "내용 1");
		articleService.create("제목 2", "내용 2");
		articleService.create("제목 3", "내용 3");
	}

}
