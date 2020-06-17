package whu.zhj.imageshare.dao;


import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import whu.zhj.imageshare.model.User;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-05 14:14
 */
@RepositoryConfig(cacheName = "UserCache")
public interface UserRepository extends IgniteRepository<User, Long> {
    public User findByUsername(String username);
}
