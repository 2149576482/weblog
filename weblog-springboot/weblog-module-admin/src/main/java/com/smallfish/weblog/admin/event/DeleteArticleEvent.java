package com.smallfish.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 文章删除事件
 **/
@Getter
public class DeleteArticleEvent extends ApplicationEvent {

    private Long articleId;

    public DeleteArticleEvent(Object source, long articleId) {
        super(source);
        this.articleId = articleId;
    }

}
