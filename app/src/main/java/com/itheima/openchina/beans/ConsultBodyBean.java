package com.itheima.openchina.beans;

import com.itheima.openchina.interfaces.BodyType;

import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/6---
 * Time:  --- 9:09---
 * Function:
 */

public class ConsultBodyBean {
    /**
     * code : 1
     * message : success
     * result : {"items":[{"author":"周其","body":"Marionette v3.2.0 发布了，Backbone.Marionette 是 Backbone.js 的一个组合应用库，简化了大规模 JavaScript 应用的开发。包含一组常用的设计模...","commentCount":0,"href":"https://www.oschina.net/news/90289/marionette-3-5-0","id":90289,"pubDate":"2017-11-06 06:37:08","recommend":false,"title":"Marionette v3.5.0 发布，Backbone.js 组合框架","type":6,"viewCount":38},{"author":"同一种调调","body":"高品质的UI工具包，React 16+的组件库 uiw  v1.2.13 发布。 更新内容： 6476e90 - 更新字体库。 691cf41 - 更新Collapse组件文档。 6a12e81 ...","commentCount":0,"href":"https://www.oschina.net/news/90288/uiw-1-2-13","id":90288,"pubDate":"2017-11-06 01:19:41","recommend":false,"title":"uiw 1.2.13 发布，基于 React 16 的组件库","type":6,"viewCount":46},{"author":"DevExtreme","body":"DevExpress正准备发布DevExtreme的下一个主要更新。 他们希望对17.2中的新功能有任何反馈意见。 点此查看该版本中的新功能列表，主要包括了数据表格...","commentCount":1,"href":"https://www.oschina.net/news/90287/devextreme-17-2-beta","id":90287,"pubDate":"2017-11-05 21:30:13","recommend":false,"title":"DevExtreme v17.2 BETA 发布，HTML5 JavaScript 组件套件","type":6,"viewCount":89},{"author":"白豆腐徐长卿","body":"Python 科学计算包 Anaconda 发布了 5.0.1 版本，详细更新日志如下： The changes detailed here are based on an upgrade from Anaconda 5.0.0. R...","commentCount":2,"href":"https://www.oschina.net/news/90286/anaconda-python-5-0-1","id":90286,"pubDate":"2017-11-05 12:59:59","recommend":false,"title":"Anaconda 5.0.1 发布，Python 科学计算包","type":6,"viewCount":830},{"author":"Deament","body":"ybg-spring-fast 1.6.3 发布了。以SpringBoot 为中心，模块化开发系统，用户可以随意删减除权限框架外 任意的系统模块。复用，组装性强主要应用技术...","commentCount":9,"href":"https://www.oschina.net/news/90285/ybg-spring-fast-1-6-3","id":90285,"pubDate":"2017-11-05 10:26:41","recommend":false,"title":"ybg-spring-fast 1.6.3，基于 springboot 模块化开发","type":6,"viewCount":568},{"author":"暗夜在火星","body":"[前言] 感谢对PhalApi的关注！PhalApi是一个PHP轻量级开源接口框架，致力于快速开发接口服务。支持HTTP/SOAP/RPC等协议，可用于搭建接口/微服务/RE...","commentCount":1,"href":"https://www.oschina.net/news/90284/phalapi-2-1-2","id":90284,"pubDate":"2017-11-05 09:55:33","recommend":false,"title":"PhalApi 2.1.2 发布，PHP轻量级开源接口框架","type":6,"viewCount":437},{"author":"局长","body":"一个完美的视频播放条控件（刻度尺），可以将控件与时间对应起来，也可以对控件进行移动和缩放，把时间戳和位置对应起来","commentCount":0,"href":"https://gitee.com/wzy901213145499/Tunlview","id":90283,"pubDate":"2017-11-05 08:40:20","recommend":false,"title":"码云推荐 | 一个安卓端的完美视频播放条控件 Tunlview","type":0,"viewCount":7},{"author":"局长","body":"Horovod 是 Uber 开源的针对 TensorFlow 的分布式深度学习框架，旨在使分布式深度学习更快速，更易于使用。","commentCount":1,"href":"https://www.oschina.net/p/horovod","id":46207,"pubDate":"2017-11-05 08:39:46","recommend":false,"title":"Horovod \u2014\u2014 针对 TensorFlow 的分布式深度学习框架","type":1,"viewCount":836},{"author":"局长","body":"喜欢用 IDE 做开发的程序员必定不能错过 JetBrains 家族的 IDE，JetBrains 出品，必属精品，款款可谓都是 IDE 中的神兵利器。 去年 12 月，JetBrai...","commentCount":27,"href":"https://www.oschina.net/news/90281/announcing-goland-former-gogland","id":90281,"pubDate":"2017-11-05 08:38:41","recommend":false,"title":"JetBrains 的 Go 集成开发环境已确定最终名称：GoLand","type":6,"viewCount":2879},{"author":"局长","body":"尽管被称为Angular5，实际上它只是这个诞生于2012年的前端框架的的第四个版本：看起来差不多半年就发布一个新版本，不过实际上从重写的版本2开始，...","commentCount":2,"href":"https://my.oschina.net/u/2275217/blog/1560605","id":1560605,"pubDate":"2017-11-05 08:38:30","recommend":false,"title":"每日一博 | 最新发布的 Angular 5 快速入门与提高","type":3,"viewCount":1080},{"author":"局长","body":"Spring 重定向指南，本文将重点介绍在 Spring 中实现重定向（Redirect），并将讨论每个策略背后的原因。","commentCount":1,"href":"https://www.oschina.net/translate/spring-redirect-and-forward","id":10004037,"pubDate":"2017-11-05 08:35:24","recommend":false,"title":"协作翻译 | 介绍在 Spring 中实现重定向的三种不同方法","type":4,"viewCount":646},{"author":"局长","body":"张量计算从爱因斯坦时代起就是科学研究的重要内容。大数据时代，大数据和机器学习对稀疏张量（绝大多数元素为 0 的稀疏数组）的计算要求越来越高。...","commentCount":3,"href":"https://www.oschina.net/news/90278/mit-new-compiler-taco-faster","id":90278,"pubDate":"2017-11-05 08:32:38","recommend":false,"title":"比现有软件包快100倍 MIT 新型计算系统带来的编译优化","type":6,"viewCount":1235},{"author":"周其","body":"16 年的开发后 SciPy 正式发布 1.0 版本，SciPy (pronounced \"Sigh Pie\") 是一个开源的数学、科学和工程计算包。 SciPy 1.0版本的一些亮点是： 主要...","commentCount":6,"href":"https://www.oschina.net/news/90277/scipy-1-0","id":90277,"pubDate":"2017-11-05 08:28:52","recommend":false,"title":"16 年的开发后 SciPy 正式发布 1.0 版本","type":6,"viewCount":1192},{"author":"局长","body":"一年前，微软官方表示，Azure 上的所有虚拟机近三分之一都在运行着 Linux 发行版。现在这一个数据达到了 40％。微软 Azure 云中的所有虚拟机近 40...","commentCount":4,"href":"https://www.oschina.net/news/90276/linux-powers-microsofts-azure-vm","id":90276,"pubDate":"2017-11-05 08:24:38","recommend":false,"title":"微软的 Azure 虚拟机约 40% 运行着 Linux 发行版","type":6,"viewCount":989},{"author":"局长","body":"\u201c哪部电影曾经让你痛哭过？\u201d \u201c被我爸发现的那部小电影\u201d","commentCount":18,"href":"https://my.oschina.net/xxiaobian/blog/1560953","id":1560953,"pubDate":"2017-11-05 08:23:09","recommend":false,"title":"OSChina 周日乱弹 \u2014\u2014 给苹果电脑选机械键盘","type":3,"viewCount":1293},{"author":"局长","body":"前段时间比较热门的是 AlphaGo（阿法狗）的升级版：AlphaGo Zero（阿法狗零）。跟阿法狗不同，阿法狗零不依赖于任何人类对弈记录，完全从围棋的规则...","commentCount":14,"href":"https://www.oschina.net/news/90274/alphago-zero","id":90274,"pubDate":"2017-11-05 08:22:11","recommend":false,"title":"由 AlphaGo Zero 说起 我们尚未真正理解什么是人类智能","type":6,"viewCount":604},{"author":"局长","body":"罗马尼亚网络安全公司 Bitdefender 设置的蜜罐检测结果显示，黑客在互联网上大规模扫描以太坊(Ethereum)挖矿设备。黑客使用这些凭证访问矿机，并替...","commentCount":2,"href":"https://www.oschina.net/news/90273/hackers-using-default-ssh-creds-ethereum-mining-equipment","id":90273,"pubDate":"2017-11-05 08:09:23","recommend":false,"title":"挖矿手段多 黑客使用默认 SSH 凭证控制以太坊挖矿设备","type":6,"viewCount":669},{"author":"周其","body":"此次更新内容： 新版本提供了三个不同名称的独立ISO映像表明意图 \u2014 Xfce，DDE（Deepin桌面环境）和服务器。 发行说明仅以土耳其语提供，但在临时...","commentCount":1,"href":"https://www.oschina.net/news/90272/pardus-17-1","id":90272,"pubDate":"2017-11-05 08:01:54","recommend":false,"title":"Pardus 17.1 发布，基于 Debian 的 Linux 发行版","type":6,"viewCount":322},{"author":"周其","body":"JDK 10 早期试用版发布，其中做了大量改进。 JDK 10 发行说明： 自动显示Swing / AWT文本组件的触摸键盘  删除policytool安全工具 删除常见的DOM...","commentCount":16,"href":"https://www.oschina.net/news/90271/jdk-10","id":90271,"pubDate":"2017-11-05 07:43:42","recommend":false,"title":"JDK 10 早期试用版发布，Java 开发工具包","type":6,"viewCount":1969},{"author":"周其","body":"NG-ZORRO 0.6.0-rc.3 已发布，NG-ZORRO 是一个企业级的 UI 组件库，是 Ant Design 的 Angular 4.0 实现，开发和服务于企业级后台产品。 此次更...","commentCount":3,"href":"https://www.oschina.net/news/90270/ng-zorro-0-6-0-rc-3","id":90270,"pubDate":"2017-11-05 07:26:22","recommend":false,"title":"NG-ZORRO 0.6.0-rc.3 发布，Ant Design 的 Angular 4.0 实现","type":6,"viewCount":237}],"nextPageToken":"B6735F814F088BCC","prevPageToken":"0997C855C600E421","requestCount":20,"responseCount":20,"totalResults":82974}
     * time : 2017-11-06 09:16:06
     */

    private int code;
    private String message;
    private ConsultBodyResultBean result;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ConsultBodyResultBean getResult() {
        return result;
    }

    public void setResult(ConsultBodyResultBean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class ConsultBodyResultBean implements BodyType {
        /**
         * items : [{"author":"周其","body":"Marionette v3.2.0 发布了，Backbone.Marionette 是 Backbone.js 的一个组合应用库，简化了大规模 JavaScript 应用的开发。包含一组常用的设计模...","commentCount":0,"href":"https://www.oschina.net/news/90289/marionette-3-5-0","id":90289,"pubDate":"2017-11-06 06:37:08","recommend":false,"title":"Marionette v3.5.0 发布，Backbone.js 组合框架","type":6,"viewCount":38},{"author":"同一种调调","body":"高品质的UI工具包，React 16+的组件库 uiw  v1.2.13 发布。 更新内容： 6476e90 - 更新字体库。 691cf41 - 更新Collapse组件文档。 6a12e81 ...","commentCount":0,"href":"https://www.oschina.net/news/90288/uiw-1-2-13","id":90288,"pubDate":"2017-11-06 01:19:41","recommend":false,"title":"uiw 1.2.13 发布，基于 React 16 的组件库","type":6,"viewCount":46},{"author":"DevExtreme","body":"DevExpress正准备发布DevExtreme的下一个主要更新。 他们希望对17.2中的新功能有任何反馈意见。 点此查看该版本中的新功能列表，主要包括了数据表格...","commentCount":1,"href":"https://www.oschina.net/news/90287/devextreme-17-2-beta","id":90287,"pubDate":"2017-11-05 21:30:13","recommend":false,"title":"DevExtreme v17.2 BETA 发布，HTML5 JavaScript 组件套件","type":6,"viewCount":89},{"author":"白豆腐徐长卿","body":"Python 科学计算包 Anaconda 发布了 5.0.1 版本，详细更新日志如下： The changes detailed here are based on an upgrade from Anaconda 5.0.0. R...","commentCount":2,"href":"https://www.oschina.net/news/90286/anaconda-python-5-0-1","id":90286,"pubDate":"2017-11-05 12:59:59","recommend":false,"title":"Anaconda 5.0.1 发布，Python 科学计算包","type":6,"viewCount":830},{"author":"Deament","body":"ybg-spring-fast 1.6.3 发布了。以SpringBoot 为中心，模块化开发系统，用户可以随意删减除权限框架外 任意的系统模块。复用，组装性强主要应用技术...","commentCount":9,"href":"https://www.oschina.net/news/90285/ybg-spring-fast-1-6-3","id":90285,"pubDate":"2017-11-05 10:26:41","recommend":false,"title":"ybg-spring-fast 1.6.3，基于 springboot 模块化开发","type":6,"viewCount":568},{"author":"暗夜在火星","body":"[前言] 感谢对PhalApi的关注！PhalApi是一个PHP轻量级开源接口框架，致力于快速开发接口服务。支持HTTP/SOAP/RPC等协议，可用于搭建接口/微服务/RE...","commentCount":1,"href":"https://www.oschina.net/news/90284/phalapi-2-1-2","id":90284,"pubDate":"2017-11-05 09:55:33","recommend":false,"title":"PhalApi 2.1.2 发布，PHP轻量级开源接口框架","type":6,"viewCount":437},{"author":"局长","body":"一个完美的视频播放条控件（刻度尺），可以将控件与时间对应起来，也可以对控件进行移动和缩放，把时间戳和位置对应起来","commentCount":0,"href":"https://gitee.com/wzy901213145499/Tunlview","id":90283,"pubDate":"2017-11-05 08:40:20","recommend":false,"title":"码云推荐 | 一个安卓端的完美视频播放条控件 Tunlview","type":0,"viewCount":7},{"author":"局长","body":"Horovod 是 Uber 开源的针对 TensorFlow 的分布式深度学习框架，旨在使分布式深度学习更快速，更易于使用。","commentCount":1,"href":"https://www.oschina.net/p/horovod","id":46207,"pubDate":"2017-11-05 08:39:46","recommend":false,"title":"Horovod \u2014\u2014 针对 TensorFlow 的分布式深度学习框架","type":1,"viewCount":836},{"author":"局长","body":"喜欢用 IDE 做开发的程序员必定不能错过 JetBrains 家族的 IDE，JetBrains 出品，必属精品，款款可谓都是 IDE 中的神兵利器。 去年 12 月，JetBrai...","commentCount":27,"href":"https://www.oschina.net/news/90281/announcing-goland-former-gogland","id":90281,"pubDate":"2017-11-05 08:38:41","recommend":false,"title":"JetBrains 的 Go 集成开发环境已确定最终名称：GoLand","type":6,"viewCount":2879},{"author":"局长","body":"尽管被称为Angular5，实际上它只是这个诞生于2012年的前端框架的的第四个版本：看起来差不多半年就发布一个新版本，不过实际上从重写的版本2开始，...","commentCount":2,"href":"https://my.oschina.net/u/2275217/blog/1560605","id":1560605,"pubDate":"2017-11-05 08:38:30","recommend":false,"title":"每日一博 | 最新发布的 Angular 5 快速入门与提高","type":3,"viewCount":1080},{"author":"局长","body":"Spring 重定向指南，本文将重点介绍在 Spring 中实现重定向（Redirect），并将讨论每个策略背后的原因。","commentCount":1,"href":"https://www.oschina.net/translate/spring-redirect-and-forward","id":10004037,"pubDate":"2017-11-05 08:35:24","recommend":false,"title":"协作翻译 | 介绍在 Spring 中实现重定向的三种不同方法","type":4,"viewCount":646},{"author":"局长","body":"张量计算从爱因斯坦时代起就是科学研究的重要内容。大数据时代，大数据和机器学习对稀疏张量（绝大多数元素为 0 的稀疏数组）的计算要求越来越高。...","commentCount":3,"href":"https://www.oschina.net/news/90278/mit-new-compiler-taco-faster","id":90278,"pubDate":"2017-11-05 08:32:38","recommend":false,"title":"比现有软件包快100倍 MIT 新型计算系统带来的编译优化","type":6,"viewCount":1235},{"author":"周其","body":"16 年的开发后 SciPy 正式发布 1.0 版本，SciPy (pronounced \"Sigh Pie\") 是一个开源的数学、科学和工程计算包。 SciPy 1.0版本的一些亮点是： 主要...","commentCount":6,"href":"https://www.oschina.net/news/90277/scipy-1-0","id":90277,"pubDate":"2017-11-05 08:28:52","recommend":false,"title":"16 年的开发后 SciPy 正式发布 1.0 版本","type":6,"viewCount":1192},{"author":"局长","body":"一年前，微软官方表示，Azure 上的所有虚拟机近三分之一都在运行着 Linux 发行版。现在这一个数据达到了 40％。微软 Azure 云中的所有虚拟机近 40...","commentCount":4,"href":"https://www.oschina.net/news/90276/linux-powers-microsofts-azure-vm","id":90276,"pubDate":"2017-11-05 08:24:38","recommend":false,"title":"微软的 Azure 虚拟机约 40% 运行着 Linux 发行版","type":6,"viewCount":989},{"author":"局长","body":"\u201c哪部电影曾经让你痛哭过？\u201d \u201c被我爸发现的那部小电影\u201d","commentCount":18,"href":"https://my.oschina.net/xxiaobian/blog/1560953","id":1560953,"pubDate":"2017-11-05 08:23:09","recommend":false,"title":"OSChina 周日乱弹 \u2014\u2014 给苹果电脑选机械键盘","type":3,"viewCount":1293},{"author":"局长","body":"前段时间比较热门的是 AlphaGo（阿法狗）的升级版：AlphaGo Zero（阿法狗零）。跟阿法狗不同，阿法狗零不依赖于任何人类对弈记录，完全从围棋的规则...","commentCount":14,"href":"https://www.oschina.net/news/90274/alphago-zero","id":90274,"pubDate":"2017-11-05 08:22:11","recommend":false,"title":"由 AlphaGo Zero 说起 我们尚未真正理解什么是人类智能","type":6,"viewCount":604},{"author":"局长","body":"罗马尼亚网络安全公司 Bitdefender 设置的蜜罐检测结果显示，黑客在互联网上大规模扫描以太坊(Ethereum)挖矿设备。黑客使用这些凭证访问矿机，并替...","commentCount":2,"href":"https://www.oschina.net/news/90273/hackers-using-default-ssh-creds-ethereum-mining-equipment","id":90273,"pubDate":"2017-11-05 08:09:23","recommend":false,"title":"挖矿手段多 黑客使用默认 SSH 凭证控制以太坊挖矿设备","type":6,"viewCount":669},{"author":"周其","body":"此次更新内容： 新版本提供了三个不同名称的独立ISO映像表明意图 \u2014 Xfce，DDE（Deepin桌面环境）和服务器。 发行说明仅以土耳其语提供，但在临时...","commentCount":1,"href":"https://www.oschina.net/news/90272/pardus-17-1","id":90272,"pubDate":"2017-11-05 08:01:54","recommend":false,"title":"Pardus 17.1 发布，基于 Debian 的 Linux 发行版","type":6,"viewCount":322},{"author":"周其","body":"JDK 10 早期试用版发布，其中做了大量改进。 JDK 10 发行说明： 自动显示Swing / AWT文本组件的触摸键盘  删除policytool安全工具 删除常见的DOM...","commentCount":16,"href":"https://www.oschina.net/news/90271/jdk-10","id":90271,"pubDate":"2017-11-05 07:43:42","recommend":false,"title":"JDK 10 早期试用版发布，Java 开发工具包","type":6,"viewCount":1969},{"author":"周其","body":"NG-ZORRO 0.6.0-rc.3 已发布，NG-ZORRO 是一个企业级的 UI 组件库，是 Ant Design 的 Angular 4.0 实现，开发和服务于企业级后台产品。 此次更...","commentCount":3,"href":"https://www.oschina.net/news/90270/ng-zorro-0-6-0-rc-3","id":90270,"pubDate":"2017-11-05 07:26:22","recommend":false,"title":"NG-ZORRO 0.6.0-rc.3 发布，Ant Design 的 Angular 4.0 实现","type":6,"viewCount":237}]
         * nextPageToken : B6735F814F088BCC
         * prevPageToken : 0997C855C600E421
         * requestCount : 20
         * responseCount : 20
         * totalResults : 82974
         */

        private String nextPageToken;
        private String prevPageToken;
        private int requestCount;
        private int responseCount;
        private int totalResults;
        private List<ItemsBean> items;

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public String getPrevPageToken() {
            return prevPageToken;
        }

        public void setPrevPageToken(String prevPageToken) {
            this.prevPageToken = prevPageToken;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }

        public int getResponseCount() {
            return responseCount;
        }

        public void setResponseCount(int responseCount) {
            this.responseCount = responseCount;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean implements BodyType {
            /**
             * author : 周其
             * body : Marionette v3.2.0 发布了，Backbone.Marionette 是 Backbone.js 的一个组合应用库，简化了大规模 JavaScript 应用的开发。包含一组常用的设计模...
             * commentCount : 0
             * href : https://www.oschina.net/news/90289/marionette-3-5-0
             * id : 90289
             * pubDate : 2017-11-06 06:37:08
             * recommend : false
             * title : Marionette v3.5.0 发布，Backbone.js 组合框架
             * type : 6
             * viewCount : 38
             */

            private String author;
            private String body;
            private int commentCount;
            private String href;
            private int id;
            private String pubDate;
            private boolean recommend;
            private String title;
            private int type;
            private int viewCount;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public boolean isRecommend() {
                return recommend;
            }

            public void setRecommend(boolean recommend) {
                this.recommend = recommend;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }
        }
    }
}
