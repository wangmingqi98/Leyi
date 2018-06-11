package com.leyijf.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class HBean {

    /**
     * id : 16
     * title : 理财人常见问题
     * article : {"list":[{"id":"52","title":"费用、合同、理财金额限","icon":"","content":""}]}
     */

    private String id;
    private String title;
    private ArticleBean article;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArticleBean getArticle() {
        return article;
    }

    public void setArticle(ArticleBean article) {
        this.article = article;
    }

    public static class ArticleBean {
        public ArticleBean(List<ListBean> list) {
            this.list = list;
        }

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            public ListBean(String id, String title, String content) {
                this.id = id;
                this.title = title;
                this.content = content;
            }

            /**
             * id : 52
             * title : 费用、合同、理财金额限
             * icon :
             * content :
             */

            private String id;
            private String title;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }



            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
