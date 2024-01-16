package com.example.study1.article;

import com.example.study1.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public RsData<Article> create(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(article);
        return RsData.of("S-3", "성공", article);
    }

    public Article getArticle(Long id) {
        Optional<Article> article = this.articleRepository.findById(id);

        if (article.isEmpty()) {
            throw new RuntimeException("없음");
        }

        return article.get();
    }

    public RsData<Article> modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        article.setModifyDate(LocalDateTime.now());

        this.articleRepository.save(article);
        return RsData.of("S-4", "수정 성공", article);
    }

    public RsData<Article> delete(Article article) {
        this.articleRepository.delete(article);

        return RsData.of("S-5", "삭제 성공");
    }
}
