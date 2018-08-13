package com.ylc.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictServiceTest {

    private DictService dictService;

    @Autowired
    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    @Test
    public void forUpdate() {
        dictService.forUpdate();
    }

    @Test
    public void forBatchUpdate() {
        dictService.forBatchUpdate();
    }

    @Test
    public void queryForBean() {
        dictService.queryForBean();
    }

    @Test
    public void queryForListBean() {
        dictService.queryForListBean();
    }

    @Test
    public void queryForObject() {
        dictService.queryForObject();
    }

    @Test
    public void queryForListObject() {
        dictService.queryForListObject();
    }

    @Test
    public void queryForMap() {
        dictService.queryForMap();
    }

    @Test
    public void queryForListMap() {
        dictService.queryForListMap();
    }

    @Test
    public void procedureTest() {
        dictService.callProcedure();
    }
}