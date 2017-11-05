package com.itheima.openchina.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by jiang on 2017/11/5.
 * 用于存储页面中从网络中请求下来的数据
 */
@XStreamAlias("oschina")
public class RecommondBean {
    @XStreamAlias("softwarecount")
    public int softwarecount;
    @XStreamAlias("pagesize")
    public int pagesize;
    @XStreamAlias("softwares")
    public TypeBean typeBean;
    public class TypeBean{
        @XStreamImplicit(itemFieldName = "software")
        public List<Software> list;
        public class Software{
            @XStreamAlias("id")
            public int id;
            @XStreamAlias("name")
            public String name;
            @XStreamAlias("description")
            public String description;
            @XStreamAlias("url")
            public String url;
        }
    }

}
