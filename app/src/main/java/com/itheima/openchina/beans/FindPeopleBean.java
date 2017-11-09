package com.itheima.openchina.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by jiang on 2017/11/8.
 */
@XStreamAlias("oschina")
public class FindPeopleBean {
    @XStreamAlias("users")
    public Users users;
    public class Users {
        @XStreamImplicit(itemFieldName = "user")
        public List<User> list;
        public class User {
            @XStreamAlias("name")
            public String name;
            @XStreamAlias("uid")
            public int uid;
            @XStreamAlias("portrait")
            public String portrait;
            @XStreamAlias("gender")
            public String gender;
            @XStreamAlias("from")
            public String from;
        }
    }
}


