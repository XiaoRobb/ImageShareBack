package whu.zhj.imageshare.util;

import whu.zhj.imageshare.model.BaseCodeEnum;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-06 09:23
 */
public class CodeEnumUtil {
    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumContants = enumClass.getEnumConstants();
        for(E e:enumContants){
            if(e.getCode() == code)
                return e;
        }
        return null;
    }
}
