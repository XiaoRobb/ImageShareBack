package whu.zhj.imageshare.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-05-30 15:54
 */
public class User {

    /**
     * 自增长长整形生成器
     */
    private static final AtomicLong ID_GEN = new AtomicLong();


    /**
     * 用户IgniteID
     */
    @QuerySqlField(index = true)
    private long igniteId;

    /**
     * 用户ID
     */
    @QuerySqlField(index = true)
    private int id;

    /**
     * 用户名
     */
    @QuerySqlField(index = true)
    private String username;

    /**
     * 密码
     */
    @QuerySqlField
    private String password;

    /**
     * 身份
     */
    @QuerySqlField
    private Role role;

    /**
     * 构造函数
     * @param id 用户ID
     * @param username 用户名
     * @param password 用户密码
     */
    public User(long igniteId, String username, String password, Role role){
        this.igniteId = igniteId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * 构造函数
     * @param username 用户名
     * @param password 用户密码
     */
    public User(String username, String password, Role role){
        this.igniteId = ID_GEN.incrementAndGet();
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, int role){
        this.igniteId = ID_GEN.incrementAndGet();
        this.username = username;
        this.password = password;
        this.role = Role.getEnum(role);
    }
    public long getIgniteId() {
        return igniteId;
    }

    public void setIgniteId(long igniteId) {
        this.igniteId = igniteId;
    }

    public void setId(int id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * 重写toString函数
     * @return user信息
     */
    @Override
    public String toString() {
        return "User{" +
                "igniteId=" + igniteId +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
