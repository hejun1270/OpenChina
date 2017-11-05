package com.itheima.openchina.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by jiang on 2017/11/5.
 */
@XStreamAlias("oschina")
public class CategoryBean {
    @XStreamAlias("softwarecount")
    public int softwarecount;
    @XStreamAlias("softwareTypes")
    public TypeBean typeBean;

    public class TypeBean {
        @XStreamImplicit(itemFieldName = "softwareType")
        public List<SoftwareType> list;
        public class SoftwareType {
            @XStreamAlias("name")
            public String name;
            @XStreamAlias("tag")
            public int tag;
        }

    }


}
