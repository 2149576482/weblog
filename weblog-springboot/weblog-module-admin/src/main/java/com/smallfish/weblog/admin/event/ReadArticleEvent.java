package com.smallfish.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 用于表示一个文章阅读事件
 **/
@Getter
public class ReadArticleEvent extends ApplicationEvent {

    private Long articleId;

    public ReadArticleEvent(Object source, Long articleId) {
        super(source);
        this.articleId = articleId;
    }

}
