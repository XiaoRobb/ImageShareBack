package whu.zhj.imageshare.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import whu.zhj.imageshare.model.BaseCodeEnum;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-06 09:27
 */
@MappedTypes(BaseCodeEnum.class)
public class CodeEnumTypeHandler<E extends  Enum<?> & BaseCodeEnum> extends BaseTypeHandler<BaseCodeEnum> {
    private Class<E> type;

    public CodeEnumTypeHandler(Class<E> type){
        if(type == null){
            throw new IllegalArgumentException("Type argument cannot be null.");
        }
        this.type = type;
    }

    //用于定义设置参数时，该如何把Java类型的参数转换为对应的数据库类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseCodeEnum baseCodeEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, baseCodeEnum.getCode());
    }
    //用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        return resultSet.wasNull() ? null : codeOf(code);
    }
    //用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        return resultSet.wasNull() ? null : codeOf(code);
    }

    //用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        return callableStatement.wasNull() ? null : codeOf(code);
    }

    private E codeOf(int code){
        try{
            return CodeEnumUtil.codeOf(type, code);
        }catch (Exception ex){
            throw new IllegalArgumentException("Cannot convert" + code + " to " + type.getSimpleName() + " by code value.", ex);
        }
    }
}
