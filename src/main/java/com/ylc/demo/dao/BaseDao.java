package com.ylc.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * @Description : 数据库操作基础类
 * <p>
 * jdbcTemplate中查询单条数据的方法：jdbcTemplate.queryForObject()、jdbcTemplate.queryForMap()
 * <p>
 * 如果返回为空或多条数据的时候回报错：Incorrect result size: expected 1, actual 0
 * <p>
 * 使用的时候需要自己处理异常，或者直接调用返回多条数据的方法
 * @Author : yulc
 * @Date : 2018/7/23 22:44
 */
@Repository
public class BaseDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 用于insert 、update和delete操作
     *
     * @param sql    sql语句
     * @param params 参数
     * @param types  参数类型
     * @return int
     */
    public int forUpdate(String sql, Object[] params, int[] types) {
        if (params == null) {
            return jdbcTemplate.update(sql);
        } else {
            return jdbcTemplate.update(sql, params, types);
        }
    }

    /**
     * 批量 插入、更新和删除
     *
     * @param sql         sql语句
     * @param batchParams 参数
     * @param types       参数类型
     * @return int[]
     */
    public int[] forBatchUpdate(String sql, List<Object[]> batchParams, int[] types) {
        if (batchParams == null) {
            return jdbcTemplate.batchUpdate(sql);
        } else {
            return jdbcTemplate.batchUpdate(sql, batchParams, types);
        }
    }

    /**
     * 根据映射返回对应实体类
     * <p>
     * 注意：如果查询的数据为0，则会出现异常：Incorrect result size: expected 1, actual 0
     *
     * @param sql       sql语句
     * @param params    参数
     * @param types     参数类型
     * @param rowMapper 映射实体
     * @return 对应实体
     */
    public Object queryForBean(String sql, Object[] params, int[] types, RowMapper<?> rowMapper) {
        try {
            if (params == null) {
                return jdbcTemplate.queryForObject(sql, rowMapper);
            } else {
                return jdbcTemplate.queryForObject(sql, params, types, rowMapper);
            }
        } catch (EmptyResultDataAccessException e) {
            System.out.println("查询异常，返回结果数目不匹配:" + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * 返回实体List<T>
     *
     * @param sql       sql语句
     * @param params    参数
     * @param types     参数类型
     * @param rowMapper 映射实体
     * @return List<?>
     */
    public List<?> queryForListBean(String sql, Object[] params, int[] types, RowMapper<?> rowMapper) {
        if (params == null) {
            return jdbcTemplate.query(sql, rowMapper);
        } else {
            return jdbcTemplate.query(sql, params, types, rowMapper);
        }
    }

    /**
     * 返回基本对象类型，Integer,Long,Double,String等
     *
     * @param sql    sql语句
     * @param params 参数
     * @param types  参数类型
     * @param bean   查询类型
     * @return Class<?>
     */
    public Object queryForObject(String sql, Object[] params, int[] types, Class<?> bean) {
        try {
            if (params == null) {
                return jdbcTemplate.queryForObject(sql, bean);
            } else {
                return jdbcTemplate.queryForObject(sql, params, types, bean);
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 返回对应类型的List
     * <p>
     * 返回实体的话查询字段名和实体属性要一致
     *
     * @param sql    sql语句
     * @param params 参数
     * @param types  参数类型
     * @param bean   查询类型
     * @return List<?>
     */
    public List<?> queryForListObject(String sql, Object[] params, int[] types, Class<?> bean) {
        if (params == null) {
            return jdbcTemplate.queryForList(sql, bean);
        } else {
            return jdbcTemplate.queryForList(sql, params, types, bean);
        }
    }

    /**
     * 返回map类型（字段名，值）
     * <p>
     * 注意：如果查询的数据为0，则会出现异常：Incorrect result size: expected 1, actual 0
     *
     * @param sql    sql语句
     * @param params 参数
     * @param types  参数类型
     * @return Map<></>
     */
    public Map<String, Object> queryForMap(String sql, Object[] params, int[] types) {
        try {
            if (params == null) {
                return jdbcTemplate.queryForMap(sql);
            } else {
                return jdbcTemplate.queryForMap(sql, params, types);
            }
        } catch (EmptyResultDataAccessException e) {
            // 只能够查询一条记录，没有数据或多条数据是报错：Incorrect result size: expected 1, actual 0
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回map集合
     *
     * @param sql    sql语句
     * @param params 参数
     * @param types  参数类型
     * @return List<Map></>
     */
    public List<Map<String, Object>> queryForListMap(String sql, Object[] params,
                                                     int[] types) {
        if (params == null) {
            return jdbcTemplate.queryForList(sql);
        } else {
            return jdbcTemplate.queryForList(sql, params, types);
        }
    }

    /**
     * 调用存储过程
     */
    public String callProcedure() {
        String tCode = "city";
        String dCode = "330100";
        return (String) this.jdbcTemplate.execute(con -> {
            String storedProc = "{call getDic (?,?,?)}";// 调用的sql
            CallableStatement cs = con.prepareCall(storedProc);
            cs.setString(1, tCode);// 设置输入参数的值
            cs.setString(2, dCode);// 设置输入参数的值
            cs.registerOutParameter(3, Types.VARCHAR);// 注册输出参数的类型
            return cs;
        }, (CallableStatementCallback) cs -> {
            cs.execute();
            return cs.getString(3);// 获取输出参数的值
        });
    }
}
