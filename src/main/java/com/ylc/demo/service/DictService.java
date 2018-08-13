package com.ylc.demo.service;

import com.ylc.demo.dao.BaseDao;
import com.ylc.demo.entity.Dict;
import com.ylc.demo.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description : service层
 * @Author : yulc
 * @Date :2018/8/12 14:21
 */
@Service
public class DictService {

    private BaseDao baseDao;

    @Autowired
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    /* #################### 查询测试参数############################## */
    // 新增操作
    private String insertSql = "insert into t_dict (type_code,type_name,dict_code,dict_name,pid) values (?,?,?,?,?)";
    private Object[] paramsForInsert = new Object[]{"province", "省份", "330000", "浙江省", null};
    private int[] typesForInsert = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
    // 返回单个实体
    private String sqlForSingle = "SELECT * FROM t_dict WHERE type_code = ? AND dict_code = ? ORDER BY type_code,dict_code";
    private Object[] paramsForSingle = new Object[]{"province", "330000"};
    private int[] typesForSingle = new int[]{Types.VARCHAR, Types.VARCHAR};
    // 返回多个实体
    private String sqlForList = "SELECT * FROM t_dict WHERE type_code = ? ORDER BY type_code,dict_code";
    private Object[] paramsForList = new Object[]{"city"};
    private int[] typesForList = new int[]{Types.VARCHAR};
    // 返回单条基本类型
    private String sqlForString = "SELECT dict_name FROM t_dict WHERE type_code = ? AND dict_code = ? ORDER BY type_code,dict_code";
    private Object[] paramsForString = new Object[]{"province", "330000"};
    private int[] typesForString = new int[]{Types.VARCHAR, Types.VARCHAR};
    // 返回多条基本类型
    private String sqlForStringList = "SELECT dict_name FROM t_dict WHERE type_code = ? ORDER BY type_code,dict_code";
    private Object[] paramsForStringList = new Object[]{"city"};
    private int[] typesForStringList = new int[]{Types.VARCHAR};


    public void forUpdate() {
        System.out.println("\r\n################ forUpdate ###########################");
        int res = baseDao.forUpdate(insertSql, paramsForInsert, typesForInsert);
        System.out.println("rows:" + res);
    }

    public void forBatchUpdate() {
        System.out.println("\r\n################ forUpdate ###########################");
        List<Object[]> paramsForBatch = new ArrayList<>();
        paramsForBatch.add(new Object[]{"city", "市级", "330100", "杭州市", 1});
        paramsForBatch.add(new Object[]{"city", "市级", "330400", "嘉兴市", 1});
        int[] res = baseDao.forBatchUpdate(insertSql, paramsForBatch, typesForInsert);
        for (int nu : res) {
            System.out.println("rows:" + nu);
        }
    }

    public void queryForBean() {
        System.out.println("\r\n################ queryForBean ###########################");
        Dict dict = (Dict) baseDao.queryForBean(sqlForSingle, paramsForSingle, typesForSingle, new DictMapper());
        System.out.println(dict.toString());
    }

    public void queryForListBean() {
        System.out.println("\r\n################ queryForListBean ###########################");
        List<Dict> dictList = (List<Dict>) baseDao.queryForListBean(sqlForList, paramsForList, typesForList, new DictMapper());
        for (Dict dict : dictList) {
            System.out.println(dict.toString());
        }
    }

    public void queryForObject() {
        System.out.println("\r\n################ queryForObject ###########################");
        String dictName = (String) baseDao.queryForObject(sqlForString, paramsForString, typesForString, String.class);
        System.out.println("基本类型测试:" + dictName);
    }

    public void queryForListObject() {
        System.out.println("\r\n################ queryForListObject ###########################");
        System.out.println("基本类型测试：");
        List<String> sList = (List<String>) baseDao.queryForListObject(sqlForStringList, paramsForStringList, typesForStringList, String.class);
        for (String dictName : sList) {
            System.out.println(dictName);
        }
    }

    public void queryForMap() {
        System.out.println("\r\n################ queryForMap ###########################");
        Map<String, Object> res = baseDao.queryForMap(sqlForSingle, paramsForSingle, typesForSingle);
        System.out.println(res);
    }

    public void queryForListMap() {
        System.out.println("\r\n################ queryForListMap ###########################");
        List<Map<String, Object>> res = baseDao.queryForListMap(sqlForList, paramsForList, typesForList);
        for (Map<String, Object> map : res) {
            System.out.println(map);
        }
    }

    public void callProcedure() {
        System.out.println("\r\n######## procedure ###############");
        String res = baseDao.callProcedure();
        System.out.println("callProcedure:" + res);
    }

}
