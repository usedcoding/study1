package com.example.study1.article;

import com.example.study1.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @Getter
    @AllArgsConstructor
    public static class ArticlesResponse{
        private final List<Article> articles;
    }

    @GetMapping("")
    public RsData<ArticlesResponse> getList() {
        List<Article> articles = this.articleService.getList();
        return RsData.of("S-1", "성공", new ArticlesResponse(articles));
    }

    @AllArgsConstructor
    @Getter
    public static class ArticleResponse {
        private final Article getArticle;
    }

    @GetMapping("/{id}")
    public RsData<ArticleResponse> getArticle(@PathVariable(value = "id") Long id) {
        Article getArticle = this.articleService.getArticle(id);
        return RsData.of("S-2", "성공", new ArticleResponse(getArticle));
    }

    @Getter
    @AllArgsConstructor
    public static class WriteResponse {
        private final Article article;
    }

    @Data
    public static class WriteRequest {
        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }
    @PostMapping("")
    public RsData<Article> write(@Valid @RequestBody WriteRequest writeRequest) {

        RsData<Article> RsArticle = this.articleService.create(writeRequest.getTitle(), writeRequest.getContent());
        return RsArticle;
    }

    @Getter
    @AllArgsConstructor
    public static class ModifyResponse {
        private final Article article;
    }

    @Data
    public static class ModifyRequest{

        private String title;

        private String content;
    }

    @PatchMapping("/{id}")
    public  RsData<Article> modify(@PathVariable(value = "id")Long id, @Valid @RequestBody ModifyRequest modifyRequest) {
        Article article = this.articleService.getArticle(id);
        RsData<Article> RsModify = this.articleService.modify(article, modifyRequest.getTitle(), modifyRequest.getContent());
        return RsModify;
    }

    @DeleteMapping("/{id}")
    public RsData<Article> delete(@PathVariable(value = "id") Long id) {
        Article article = this.articleService.getArticle(id);
        RsData<Article> RsDelete = this.articleService.delete(article);
        return RsDelete;
    }

}
