package com.ylc.demo.mapper;

import com.ylc.demo.entity.Dict;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description : 数据字典映射类
 * @Author : yulc
 * @Date : 2018/8/12 14:15
 */
public class DictMapper implements RowMapper<Dict> {
    @Override
    public Dict mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dict dict = new Dict();
        dict.setId(rs.getInt("id"));
        dict.setTypeCode(rs.getString("type_code"));
        dict.setTypeName(rs.getString("type_name"));
        dict.setDictCode(rs.getString("dict_code"));
        dict.setDictName(rs.getString("dict_name"));
        dict.setPid(rs.getInt("pid"));
        dict.setStatus(rs.getInt("status"));
        return dict;
    }
}
