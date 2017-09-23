ALTER TABLE `people_user` DROP COLUMN `PU_APP_ID`;
ALTER TABLE `people` MODIFY COLUMN `people_app_id` int(11) NOT NULL COMMENT '应用编号' AFTER `people_datetime`;
ALTER TABLE `model` ADD COLUMN `model_parent_ids` varchar(300) NULL COMMENT '父级编号集合，从小到大排序' AFTER `model_ismenu`;
update model  set model_modelid = null where model_modelid=0;
ALTER TABLE `model` ADD FOREIGN KEY (`model_modelid`) REFERENCES `model` (`model_id`) ON DELETE CASCADE ON UPDATE NO ACTION;