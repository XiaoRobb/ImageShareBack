package whu.zhj.imageshare.model;

/**
 * @program: ImageShare
 * @description: 身份枚举类
 * @author: 周宏俊
 * @create: 2020-06-05 20:27
 */
public enum Role implements BaseCodeEnum{
    ADMIN(1), MEMBER(2);
    int value;

    Role(int value){
        this.value = value;
    }

    @Override
    public int getCode(){
        return this.value;
    }
    public String authority(){
        return "ROLE_" + this.name();
    }

    @Override
    public String toString(){
        return this.name();
    }

    public static Role getEnum(int value){
        switch (value){
            case 1:
                return ADMIN;
            default:
                return MEMBER;
        }
    }
}
