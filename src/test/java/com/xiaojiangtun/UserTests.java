package com.xiaojiangtun;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaojiangtun.entity.User;
import com.xiaojiangtun.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author fdy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    private static final Logger log = LoggerFactory.getLogger(UserTests.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() throws Exception {
        final int row1 = userMapper.insert(new User("u1", "p1"));
        log.info("[添加结果] - [{}]", row1);
        final int row2 = userMapper.insert(new User("u2", "p2"));
        log.info("[添加结果] - [{}]", row2);
        final int row3 = userMapper.insert(new User("u1", "p3"));
        log.info("[添加结果] - [{}]", row3);
        final List<User> u1 = userMapper.findByUsername("u1");
        log.info("[根据用户名查询] - [{}]", u1);
    }

    @Test
    public void test2() throws Exception {
        User user1 = new User("u1", "p1");
        User user2 = new User("u1", "p2");
        User user3 = new User("u3", "p3");
        int res1 = userMapper.insertSelective(user1);
        log.info("[user1回写主键] - [{}]", user1.getId());
        int res2 = userMapper.insertSelective(user2);
        log.info("[user2回写主键] - [{}]", user2.getId());
        int res3 = userMapper.insertSelective(user3);
        log.info("[user3回写主键] - [{}]", user3.getId());
        final int count = userMapper.countByUsername("u1");
        log.info("[调用自己写的SQL] - [{}]", count);

        // TODO 模拟分页
        userMapper.insertSelective(new User("u6", "p1"));
        userMapper.insertSelective(new User("u7", "p1"));
        userMapper.insertSelective(new User("u5", "p1"));
        userMapper.insertSelective(new User("u4", "p1"));
        userMapper.insertSelective(new User("u9", "p1"));
        userMapper.insertSelective(new User("u8", "p1"));
        userMapper.insertSelective(new User("u10", "p1"));
        userMapper.insertSelective(new User("u11", "p1"));
        userMapper.insertSelective(new User("u12", "p1"));
        userMapper.insertSelective(new User("u13", "p1"));
        // TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.userMapper.selectAll());
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<User> userPageInfo = new PageInfo<>(this.userMapper.selectAll());
        log.info("[普通写法] - [{}]", userPageInfo);
    }
}