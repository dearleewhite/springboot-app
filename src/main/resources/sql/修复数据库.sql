use business;
-- 添加巡检类型字段
ALTER TABLE device_inspect ADD type tinyint(1) DEFAULT NULL COMMENT '巡检类型(0:机械,1:电气,2:工艺)' ;
-- 添加点位字段
ALTER TABLE device_model_mantain ADD points int(11) DEFAULT NULL COMMENT '润滑点数' ;
ALTER TABLE device_model_mantain ADD functional_id bigint(64) DEFAULT NULL COMMENT '功能属性' ;
ALTER TABLE device_model_mantain ADD oil_value tinyint(1) DEFAULT NULL COMMENT '油位采集值' ;
-- 注释勿动
-- alter  table device_model_mantain change oil_value functional_id bigint(64) DEFAULT NULL COMMENT '功能属性'
-- alter table device_model_mantain drop column points;
-- ALTER TABLE device_model_mantain ADD points int(11) DEFAULT NULL COMMENT '润滑点数' ;
-- 修复老润滑油数据
-- UPDATE device_model_mantain A, cm_materiel B  SET A.tool_id = B.id  WHERE A.tool_id = B.relation_id AND A.deleted = 0
-- 修复数据
-- UPDATE device_model_mantain A SET A.type = 0  WHERE deleted = 0  AND A.type IS NULL;
