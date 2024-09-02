package com.smallfish.weblog.web.markdown.provider;

import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
public class NofollowLinkAttributeProvider implements AttributeProvider {

    /**
     * 网站名字
     */
    private final static String DOMAIN = "www.xiaoyu.com";

    @Override
    public void setAttributes(Node node, String s, Map<String, String> map) {
        if (node instanceof Link) {
            Link linkNode = (Link) node;
            String destination = linkNode.getDestination();
            // 如果链接不是自己域名 则添加rel=nofollow
            if (!destination.contains(DOMAIN)) {
                map.put("rel", "nofollow");
            }
        }
    }
}
