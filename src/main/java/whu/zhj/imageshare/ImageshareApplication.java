package whu.zhj.imageshare;

import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import whu.zhj.imageshare.config.IgniteCfg;
import whu.zhj.imageshare.dao.UserRepository;
import whu.zhj.imageshare.model.User;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

@SpringBootApplication
@EnableIgniteRepositories
public class ImageshareApplication {

    public static void main(String[] args) {

        SpringApplication.run(ImageshareApplication.class, args);
    }
}
