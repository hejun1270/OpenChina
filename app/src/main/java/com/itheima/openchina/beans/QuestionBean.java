package com.itheima.openchina.beans;

import com.itheima.openchina.interfaces.BodyType;

import java.util.List;

/**
 * Created by 佘本民
 * When:  --- 2017/11/7---
 * Time:  --- 0:44---
 * Function:
 */

public class QuestionBean {
    /**
     * code : 1
     * message : success
     * result : {"items":[{"author":"局长","authorId":2720166,"authorPortrait":"https://static.oschina.net/uploads/user/1360/2720166_50.jpg?t=1470892376000","body":"OSCHINA 本期高手问答(11 月 1 日 - 11 月 7 日)我们请来了@iKcamp团队为大家解答关于移动 Web 前端高效开发方面的问题。 \u201ciKcamp团队\u201d由沪江...","commentCount":25,"id":2268914,"pubDate":"2017-10-31 18:44:16","title":"高手问答第 175 期 | 沪江 iKcamp 团队前端高效开发实践分享","type":0,"viewCount":1633},{"author":"宋庆离","authorId":2663968,"authorPortrait":"https://static.oschina.net/uploads/user/1331/2663968_50.png?t=1469078818000","body":"    当别人在讨论：人工智能到底用 GPU，还是用 FPGA的时候你竟然感觉自己插不上嘴？别人讨论：如何用IOT Fundation搭建物联网应用最高效时你发...","commentCount":2,"id":2269047,"pubDate":"2017-11-02 14:24:12","title":"进击的程序员！4周带你玩转人工智能、区块链、物联网","type":0,"viewCount":1215},{"author":"鲨鱼代码","authorId":3653835,"authorPortrait":"https://static.oschina.net/uploads/user/1826/3653835_50.jpg?t=1502958004000","body":"现在很想问问，一般的公司里面的系统中有多系统才用到单点登陆，但是我很好奇，究竟一个系统中会用到哪些子系统呢？请有 经验的大神指点指点，说个...","commentCount":3,"id":2269203,"pubDate":"2017-11-06 10:18:01","title":"想问问关于网站中的子系统的事情做过的进来看看，指点下。。","type":0,"viewCount":54},{"author":"汪超","authorId":945123,"authorPortrait":"https://static.oschina.net/uploads/user/472/945123_50.jpg?t=1392349103000","body":"xtype:textfield，regex:/^\\d*$/ 用正则表达式做限制，在英文输入模式下只能输入数字，但输入法切到中文时，直接回车可以带入中文和字母； 需求是无...","commentCount":2,"id":2269198,"pubDate":"2017-11-06 09:41:19","title":"ext输入框只输入数字做限制了，但在中文输入法下失效","type":0,"viewCount":39},{"author":"succeedchz","authorId":3668970,"authorPortrait":"https://static.oschina.net/uploads/user/1834/3668970_50.jpg?t=1504228694000","body":"本来刚写好代码测试是可以的，后来加了一个登录后回调的代码，登录功能就不行了，实在是找不到问题在哪","commentCount":0,"id":2269253,"pubDate":"2017-11-06 21:40:32","title":"微信登录没反应","type":0,"viewCount":6},{"author":"助哥的后花园","authorId":2756663,"authorPortrait":"https://static.oschina.net/uploads/user/1378/2756663_50.jpg?t=1491285736000","body":"c++中的const_cast当他去掉一个对象引用的const属性的时候，然后对该对象的属性进行修改，是对原来的对象修改的吗？？ #include &lt;iostream&gt;\nusing ...","commentCount":0,"id":2269252,"pubDate":"2017-11-06 19:51:57","title":"const_cast的问题","type":0,"viewCount":4},{"author":"墨缘","authorId":1785061,"authorPortrait":"https://static.oschina.net/uploads/user/892/1785061_50.png?t=1461116790000","body":"我java中执行命令行根据进程号查看进程是否存在，做一个守护进程，然后再用javaService 注册为服务，注册后无法执行windows的命令tasklist，报错t...","commentCount":0,"id":2269250,"pubDate":"2017-11-06 18:50:33","title":"javaService注册服务后执行windows命令获取不到环境变量","type":0,"viewCount":12},{"author":"liangqf","authorId":3392853,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"报错 the relative module was not found * ./vuex/store in ./src/main.js 该怎么解决  我刚开始以为是没有install vuex   求解答...","commentCount":1,"id":2269191,"pubDate":"2017-11-05 22:43:18","title":"vuex 报错  the relative module was not found","type":0,"viewCount":37},{"author":"我是欢欢啊","authorId":3716115,"authorPortrait":"https://static.oschina.net/uploads/user/1858/3716115_50.jpg?t=1509017934000","body":"","commentCount":4,"id":2269183,"pubDate":"2017-11-05 17:16:24","title":"安装python出现了什么问题，不怎么懂，求大神帮帮","type":0,"viewCount":94},{"author":"等风来___","authorId":1996289,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"PDO使用的持久链接,为了测试方便,mysql的wait_timeout和interactive_timeout的超时时间都设置为5. 当出现 Warning: PDO::__construct(): MySQL se...","commentCount":1,"id":2269240,"pubDate":"2017-11-06 17:16:17","title":"为什么try cache 捕获不到 pdo 长链接下mysql server gone awary错误","type":0,"viewCount":23},{"author":"Leo_88","authorId":2447088,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"C# winform桌面应用程序，运行一段时间后弹出a problem caused the program to stop working，时间大概10多个小时，20多个小时不等，程序里都作了异...","commentCount":2,"id":2269230,"pubDate":"2017-11-06 15:47:30","title":"C# winform程序，运行一段时间后弹出a problem caused the program to stop working","type":0,"viewCount":57},{"author":"盒子同学","authorId":3547789,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"最近接盘一个老项目，要实现跨域看pdf，发现只能看第一页。但其他页的swf已经正确加载到了，但就是无法在控件上显示。不跨域一切正常  ","commentCount":2,"id":2269185,"pubDate":"2017-11-05 19:14:22","title":"flexpaper跨域查看pdf只能看第一页！","type":0,"viewCount":23},{"author":"爱吃大肉包","authorId":867417,"authorPortrait":"https://static.oschina.net/uploads/user/433/867417_50.jpeg?t=1504517543000","body":"问下 spring config 的， 如果我一个项目的文件变化了， 会导致所有文件都更新配置。 如果我只想更新单个项目配置，不希望所有项目都刷新，如何解决...","commentCount":0,"id":2269246,"pubDate":"2017-11-06 17:33:47","title":"问下 spring config 的， 如果我一个项目的文件变化了， 会导致所有文件都更新配置项么","type":0,"viewCount":26},{"author":"天篷丿元帅","authorId":3008007,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"刚刚入行，麻烦大神们写些注释啊。最主要是感觉的自己脑子不够使。有点伤心了\u2026\u2026","commentCount":8,"id":2269190,"pubDate":"2017-11-05 22:08:42","title":"某大公司面试前端题目求解？","type":0,"viewCount":454},{"author":"蜀黍凯","authorId":2693650,"authorPortrait":"https://static.oschina.net/uploads/user/1346/2693650_50.jpg?t=1459335104000","body":"怎样使用t-io解决Android和Web端推送问题，推送（已内置API）啥意思？","commentCount":2,"id":2269236,"pubDate":"2017-11-06 16:26:22","title":"怎样使用t-io解决Android和Web端推送问题，推送（已内置API）啥意思？","type":0,"viewCount":60},{"author":"起个昵称吧","authorId":2531269,"authorPortrait":"https://static.oschina.net/uploads/user/1265/2531269_50.gif?t=1448246415000","body":"因为业务需求，使用elasticsearch-5.5.1框架作为搜索引擎使用。 现在遇到一个问题，就是想实现定期删除索引文档的数据，然后重新建立索引数据。 但...","commentCount":2,"id":2269206,"pubDate":"2017-11-06 10:48:31","title":"请教一个关于elasticsearch清除索引数据文件的方法，谢谢！","type":0,"viewCount":29},{"author":"Jihonson","authorId":3619240,"authorPortrait":"https://static.oschina.net/uploads/user/1809/3619240_50.jpeg?t=1509894027000","body":"如何实现点击关于按钮，获得APP的版本号，APP的签名信息，APP的权限申请信息 如何实现点击关于按钮，获得APP的版本号，APP的签名信息，APP的权限申...","commentCount":1,"id":2269220,"pubDate":"2017-11-06 13:19:17","title":"如何实现点击关于按钮，获得APP的版本号，APP的签名信息，APP的权限申请信息","type":0,"viewCount":22},{"author":"暴猿","authorId":3051362,"authorPortrait":"https://static.oschina.net/uploads/user/1525/3051362_50.jpeg?t=1483585671000","body":"  十一月 06, 2017 3:42:24 下午 org.apache.tomcat.util.digester.SetPropertiesRule begin 警告: [SetPropertiesRule]{Server/Service/Engine/H...","commentCount":0,"id":2269231,"pubDate":"2017-11-06 15:48:50","title":"eclipse下tomcat启动不了项目（gradle项目）  有哪位大大看看是啥米原因  在线等","type":0,"viewCount":21},{"author":"我有一头小毛驴","authorId":725289,"authorPortrait":"https://static.oschina.net/uploads/user/362/725289_50.jpg?t=1347520457000","body":"使用dbcp连接池不能读取oracle驱动","commentCount":0,"id":2269227,"pubDate":"2017-11-06 15:22:32","title":"dbcp连接池支持p6spy么?","type":0,"viewCount":11},{"author":"hookover","authorId":940240,"authorPortrait":"https://static.oschina.net/uploads/user/470/940240_50.jpg?t=1439020300000","body":"最终目标 由于数据库数据每天可能会达到2000～4000万条，所以按天划分分区仍然有很大的优化空间 我当前是按天分区的，希望可以帮我在这个基础上再改...","commentCount":2,"id":2269201,"pubDate":"2017-11-06 10:03:41","title":"MYSQL如何按天分区，再按小时分划分子分区？","type":0,"viewCount":172}],"nextPageToken":"DBA816934CD0AA59","prevPageToken":"0997C855C600E421","requestCount":20,"responseCount":20,"totalResults":1000}
     * time : 2017-11-07 00:19:53
     */

    private int code;
    private String message;
    private ResultBean result;
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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class ResultBean {
        /**
         * items : [{"author":"局长","authorId":2720166,"authorPortrait":"https://static.oschina.net/uploads/user/1360/2720166_50.jpg?t=1470892376000","body":"OSCHINA 本期高手问答(11 月 1 日 - 11 月 7 日)我们请来了@iKcamp团队为大家解答关于移动 Web 前端高效开发方面的问题。 \u201ciKcamp团队\u201d由沪江...","commentCount":25,"id":2268914,"pubDate":"2017-10-31 18:44:16","title":"高手问答第 175 期 | 沪江 iKcamp 团队前端高效开发实践分享","type":0,"viewCount":1633},{"author":"宋庆离","authorId":2663968,"authorPortrait":"https://static.oschina.net/uploads/user/1331/2663968_50.png?t=1469078818000","body":"    当别人在讨论：人工智能到底用 GPU，还是用 FPGA的时候你竟然感觉自己插不上嘴？别人讨论：如何用IOT Fundation搭建物联网应用最高效时你发...","commentCount":2,"id":2269047,"pubDate":"2017-11-02 14:24:12","title":"进击的程序员！4周带你玩转人工智能、区块链、物联网","type":0,"viewCount":1215},{"author":"鲨鱼代码","authorId":3653835,"authorPortrait":"https://static.oschina.net/uploads/user/1826/3653835_50.jpg?t=1502958004000","body":"现在很想问问，一般的公司里面的系统中有多系统才用到单点登陆，但是我很好奇，究竟一个系统中会用到哪些子系统呢？请有 经验的大神指点指点，说个...","commentCount":3,"id":2269203,"pubDate":"2017-11-06 10:18:01","title":"想问问关于网站中的子系统的事情做过的进来看看，指点下。。","type":0,"viewCount":54},{"author":"汪超","authorId":945123,"authorPortrait":"https://static.oschina.net/uploads/user/472/945123_50.jpg?t=1392349103000","body":"xtype:textfield，regex:/^\\d*$/ 用正则表达式做限制，在英文输入模式下只能输入数字，但输入法切到中文时，直接回车可以带入中文和字母； 需求是无...","commentCount":2,"id":2269198,"pubDate":"2017-11-06 09:41:19","title":"ext输入框只输入数字做限制了，但在中文输入法下失效","type":0,"viewCount":39},{"author":"succeedchz","authorId":3668970,"authorPortrait":"https://static.oschina.net/uploads/user/1834/3668970_50.jpg?t=1504228694000","body":"本来刚写好代码测试是可以的，后来加了一个登录后回调的代码，登录功能就不行了，实在是找不到问题在哪","commentCount":0,"id":2269253,"pubDate":"2017-11-06 21:40:32","title":"微信登录没反应","type":0,"viewCount":6},{"author":"助哥的后花园","authorId":2756663,"authorPortrait":"https://static.oschina.net/uploads/user/1378/2756663_50.jpg?t=1491285736000","body":"c++中的const_cast当他去掉一个对象引用的const属性的时候，然后对该对象的属性进行修改，是对原来的对象修改的吗？？ #include &lt;iostream&gt;\nusing ...","commentCount":0,"id":2269252,"pubDate":"2017-11-06 19:51:57","title":"const_cast的问题","type":0,"viewCount":4},{"author":"墨缘","authorId":1785061,"authorPortrait":"https://static.oschina.net/uploads/user/892/1785061_50.png?t=1461116790000","body":"我java中执行命令行根据进程号查看进程是否存在，做一个守护进程，然后再用javaService 注册为服务，注册后无法执行windows的命令tasklist，报错t...","commentCount":0,"id":2269250,"pubDate":"2017-11-06 18:50:33","title":"javaService注册服务后执行windows命令获取不到环境变量","type":0,"viewCount":12},{"author":"liangqf","authorId":3392853,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"报错 the relative module was not found * ./vuex/store in ./src/main.js 该怎么解决  我刚开始以为是没有install vuex   求解答...","commentCount":1,"id":2269191,"pubDate":"2017-11-05 22:43:18","title":"vuex 报错  the relative module was not found","type":0,"viewCount":37},{"author":"我是欢欢啊","authorId":3716115,"authorPortrait":"https://static.oschina.net/uploads/user/1858/3716115_50.jpg?t=1509017934000","body":"","commentCount":4,"id":2269183,"pubDate":"2017-11-05 17:16:24","title":"安装python出现了什么问题，不怎么懂，求大神帮帮","type":0,"viewCount":94},{"author":"等风来___","authorId":1996289,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"PDO使用的持久链接,为了测试方便,mysql的wait_timeout和interactive_timeout的超时时间都设置为5. 当出现 Warning: PDO::__construct(): MySQL se...","commentCount":1,"id":2269240,"pubDate":"2017-11-06 17:16:17","title":"为什么try cache 捕获不到 pdo 长链接下mysql server gone awary错误","type":0,"viewCount":23},{"author":"Leo_88","authorId":2447088,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"C# winform桌面应用程序，运行一段时间后弹出a problem caused the program to stop working，时间大概10多个小时，20多个小时不等，程序里都作了异...","commentCount":2,"id":2269230,"pubDate":"2017-11-06 15:47:30","title":"C# winform程序，运行一段时间后弹出a problem caused the program to stop working","type":0,"viewCount":57},{"author":"盒子同学","authorId":3547789,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"最近接盘一个老项目，要实现跨域看pdf，发现只能看第一页。但其他页的swf已经正确加载到了，但就是无法在控件上显示。不跨域一切正常  ","commentCount":2,"id":2269185,"pubDate":"2017-11-05 19:14:22","title":"flexpaper跨域查看pdf只能看第一页！","type":0,"viewCount":23},{"author":"爱吃大肉包","authorId":867417,"authorPortrait":"https://static.oschina.net/uploads/user/433/867417_50.jpeg?t=1504517543000","body":"问下 spring config 的， 如果我一个项目的文件变化了， 会导致所有文件都更新配置。 如果我只想更新单个项目配置，不希望所有项目都刷新，如何解决...","commentCount":0,"id":2269246,"pubDate":"2017-11-06 17:33:47","title":"问下 spring config 的， 如果我一个项目的文件变化了， 会导致所有文件都更新配置项么","type":0,"viewCount":26},{"author":"天篷丿元帅","authorId":3008007,"authorPortrait":"https://www.oschina.net/img/portrait.gif","body":"刚刚入行，麻烦大神们写些注释啊。最主要是感觉的自己脑子不够使。有点伤心了\u2026\u2026","commentCount":8,"id":2269190,"pubDate":"2017-11-05 22:08:42","title":"某大公司面试前端题目求解？","type":0,"viewCount":454},{"author":"蜀黍凯","authorId":2693650,"authorPortrait":"https://static.oschina.net/uploads/user/1346/2693650_50.jpg?t=1459335104000","body":"怎样使用t-io解决Android和Web端推送问题，推送（已内置API）啥意思？","commentCount":2,"id":2269236,"pubDate":"2017-11-06 16:26:22","title":"怎样使用t-io解决Android和Web端推送问题，推送（已内置API）啥意思？","type":0,"viewCount":60},{"author":"起个昵称吧","authorId":2531269,"authorPortrait":"https://static.oschina.net/uploads/user/1265/2531269_50.gif?t=1448246415000","body":"因为业务需求，使用elasticsearch-5.5.1框架作为搜索引擎使用。 现在遇到一个问题，就是想实现定期删除索引文档的数据，然后重新建立索引数据。 但...","commentCount":2,"id":2269206,"pubDate":"2017-11-06 10:48:31","title":"请教一个关于elasticsearch清除索引数据文件的方法，谢谢！","type":0,"viewCount":29},{"author":"Jihonson","authorId":3619240,"authorPortrait":"https://static.oschina.net/uploads/user/1809/3619240_50.jpeg?t=1509894027000","body":"如何实现点击关于按钮，获得APP的版本号，APP的签名信息，APP的权限申请信息 如何实现点击关于按钮，获得APP的版本号，APP的签名信息，APP的权限申...","commentCount":1,"id":2269220,"pubDate":"2017-11-06 13:19:17","title":"如何实现点击关于按钮，获得APP的版本号，APP的签名信息，APP的权限申请信息","type":0,"viewCount":22},{"author":"暴猿","authorId":3051362,"authorPortrait":"https://static.oschina.net/uploads/user/1525/3051362_50.jpeg?t=1483585671000","body":"  十一月 06, 2017 3:42:24 下午 org.apache.tomcat.util.digester.SetPropertiesRule begin 警告: [SetPropertiesRule]{Server/Service/Engine/H...","commentCount":0,"id":2269231,"pubDate":"2017-11-06 15:48:50","title":"eclipse下tomcat启动不了项目（gradle项目）  有哪位大大看看是啥米原因  在线等","type":0,"viewCount":21},{"author":"我有一头小毛驴","authorId":725289,"authorPortrait":"https://static.oschina.net/uploads/user/362/725289_50.jpg?t=1347520457000","body":"使用dbcp连接池不能读取oracle驱动","commentCount":0,"id":2269227,"pubDate":"2017-11-06 15:22:32","title":"dbcp连接池支持p6spy么?","type":0,"viewCount":11},{"author":"hookover","authorId":940240,"authorPortrait":"https://static.oschina.net/uploads/user/470/940240_50.jpg?t=1439020300000","body":"最终目标 由于数据库数据每天可能会达到2000～4000万条，所以按天划分分区仍然有很大的优化空间 我当前是按天分区的，希望可以帮我在这个基础上再改...","commentCount":2,"id":2269201,"pubDate":"2017-11-06 10:03:41","title":"MYSQL如何按天分区，再按小时分划分子分区？","type":0,"viewCount":172}]
         * nextPageToken : DBA816934CD0AA59
         * prevPageToken : 0997C855C600E421
         * requestCount : 20
         * responseCount : 20
         * totalResults : 1000
         */

        private String nextPageToken;
        private String prevPageToken;
        private int requestCount;
        private int responseCount;
        private int totalResults;
        private List<QuestiontemsBean> items;

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

        public List<QuestiontemsBean> getItems() {
            return items;
        }

        public void setItems(List<QuestiontemsBean> items) {
            this.items = items;
        }

        public static class QuestiontemsBean implements BodyType{
            /**
             * author : 局长
             * authorId : 2720166
             * authorPortrait : https://static.oschina.net/uploads/user/1360/2720166_50.jpg?t=1470892376000
             * body : OSCHINA 本期高手问答(11 月 1 日 - 11 月 7 日)我们请来了@iKcamp团队为大家解答关于移动 Web 前端高效开发方面的问题。 “iKcamp团队”由沪江...
             * commentCount : 25
             * id : 2268914
             * pubDate : 2017-10-31 18:44:16
             * title : 高手问答第 175 期 | 沪江 iKcamp 团队前端高效开发实践分享
             * type : 0
             * viewCount : 1633
             */

            private String author;
            private int authorId;
            private String authorPortrait;
            private String body;
            private int commentCount;
            private int id;
            private String pubDate;
            private String title;
            private int type;
            private int viewCount;

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public int getAuthorId() {
                return authorId;
            }

            public void setAuthorId(int authorId) {
                this.authorId = authorId;
            }

            public String getAuthorPortrait() {
                return authorPortrait;
            }

            public void setAuthorPortrait(String authorPortrait) {
                this.authorPortrait = authorPortrait;
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
