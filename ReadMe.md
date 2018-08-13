#### JdbcTemplate的基本使用
* mapper下的位映射文件，用于查询返回实体类型
* dao中的BaseDao中包含了基本的方法
* 需要注意查询单条数据的时候，如果返回为空的时候回出现异常：Incorrect result size: expected 1, actual 0，需要自己处理

#### demo测试表
```
CREATE TABLE `t_dict` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_code` varchar(20) NOT NULL COMMENT '类别编码',
  `type_name` varchar(20) NOT NULL COMMENT '类别名称',
  `dict_code` varchar(20) NOT NULL COMMENT '字典编码',
  `dict_name` varchar(20) NOT NULL COMMENT '字典名称',
  `pid` int(4) DEFAULT NULL COMMENT '父类ID',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态，0无效，1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
```

#### 存储过程
```
DROP PROCEDURE IF EXISTS `getDic`;
CREATE PROCEDURE getDic (
	IN tCode VARCHAR (20),
	IN dCode VARCHAR (20),
	OUT dName VARCHAR (20)
)
BEGIN
	SELECT
		dict_name INTO dName
	FROM
		t_dict
	WHERE
		type_code = tCode
	AND dict_code = dCode
	LIMIT 1;
END;
```