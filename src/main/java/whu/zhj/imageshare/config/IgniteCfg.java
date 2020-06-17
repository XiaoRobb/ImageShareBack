package whu.zhj.imageshare.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import whu.zhj.imageshare.dao.UserRepository;
import whu.zhj.imageshare.model.Role;
import whu.zhj.imageshare.model.User;
import whu.zhj.imageshare.service.IUserService;

import javax.annotation.Resource;
import javax.cache.Cache;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-05 14:21
 */
@Configuration
@EnableIgniteRepositories
public class IgniteCfg implements Serializable {

    @Resource
    IUserService userService;

    /**
     * 创建Apache Ignite 实例
     * @return
     */
    @Bean
    public Ignite igniteInstance(){
        //创建节点
        IgniteConfiguration cfg = new IgniteConfiguration();

        //设置节点名称
        cfg.setIgniteInstanceName("springDataNode");

        //启用对等类的加载
        cfg.setPeerClassLoadingEnabled(true);

        //创建User缓存
        CacheConfiguration ccfg = new CacheConfiguration("UserCache");

        //设置SQL模式
        ccfg.setIndexedTypes(Long.class, User.class);

        cfg.setCacheConfiguration(ccfg);

        return Ignition.start(cfg);
    }

//    /**
//     * Fills the repository in with sample data.
//     */
//    @Bean
//    public int addUser() {
//        userService.save(new User(1L,"Xiao","123", Role.MEMBER));
//        userService.save(new User(2L,"Zhou", "123",Role.MEMBER));
//        userService.save(new User(3L,"Robb", "123",Role.MEMBER));
//        userService.save(new User(4L,"Bob", "123",Role.MEMBER));
//        userService.save(new User(5L,"John", "123",Role.MEMBER));
//        System.out.println("\n>>> Added " + 5 + " Persons into the repository.");
//        return 0;
//    }
}
