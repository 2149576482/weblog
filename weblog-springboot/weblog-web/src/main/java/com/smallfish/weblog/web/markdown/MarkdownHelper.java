package com.smallfish.weblog.web.markdown;

import com.smallfish.weblog.web.markdown.provider.NofollowLinkAttributeProvider;
import com.smallfish.weblog.web.markdown.renderer.ImageNodeRenderer;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: markdown转换器工具类
 **/
public class MarkdownHelper {

    /**
     * markdown解析器
     */
    private static final Parser PARSER;

    /**
     * HTML 渲染器
     */
    private static final HtmlRenderer HTML_RENDERER;

    /**
     * 初始化
     */
    static {
        // markdown 扩展
        List<Extension> extensions = Arrays.asList(
                // 表格拓展
                TablesExtension.create(),
                // 标题锚点拓展
                HeadingAnchorExtension.create(),
                // 图片宽高
                ImageAttributesExtension.create(),
                // 任务列表
                TaskListItemsExtension.create()
                );
        PARSER = Parser.builder().extensions(extensions).build();
        HTML_RENDERER = HtmlRenderer.builder()
                .extensions(extensions)
                .attributeProviderFactory(context -> new NofollowLinkAttributeProvider())
                .nodeRendererFactory(context -> new ImageNodeRenderer(context))
                .build();
    }

    /**
     * 将Markdown转换成html
     */
    public static String convertMarkdown2Html(String markdown) {
        Node parse = PARSER.parse(markdown);
        return HTML_RENDERER.render(parse);
    }


    public static void main(String[] args) {
        String markdown = "![图 1-1 技术栈](https://img.quanxiaoha.com/quanxiaoha/169560181378937 \"图 1-1 技术栈\")";
        System.out.println(MarkdownHelper.convertMarkdown2Html(markdown));
    }
}
