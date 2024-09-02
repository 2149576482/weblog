package com.smallfish.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Getter
public class PublishArticleEvent extends ApplicationEvent {

    private final Long ArticleId;

    public PublishArticleEvent(Object source, long articleId) {
        super(source);
        this.ArticleId = articleId;
    }

}
