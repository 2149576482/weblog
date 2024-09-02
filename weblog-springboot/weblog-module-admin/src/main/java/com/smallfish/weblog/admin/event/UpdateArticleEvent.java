package com.smallfish.weblog.admin.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Getter
public class UpdateArticleEvent extends ApplicationEvent {

    private Long articleId;

    public UpdateArticleEvent(Object source, long articleId) {
        super(source);
        this.articleId = articleId;
    }

}
