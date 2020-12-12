-- DML sql
-- 初始化执行语句

-- 初始化应用设置<setting>表数据 如已经存在则不插入数据


-- 插入设置项 初始化设置<initialization> ==> <is_init> 默认<false>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'is_init','false','initialization',current_date(),'是否已经应用初始化' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='is_init' and type='initialization'
    );


-- 插入设置项 主题设置<theme> ==> <current_theme> 默认<simple>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'current_theme','simple','theme',current_date(),'主题, 默认<simple>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='current_theme' and type='theme'
    );

-- 插入设置项 主题设置<theme> ==> <theme_simple_url> 主题<simple>的zip文件地址
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'theme_simple_url','https://static.liguohao.cn/.ikaros/theme/simple.zip','theme',current_date(),'主题<simple>的zip文件地址' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='theme_simple_url' and type='theme'
    );



-- 插入设置项 磁盘文件<disk_file> ==> 上传的磁盘文件位置<disk_file_place>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'disk_file_place','local','disk_file',current_date(),'上传的磁盘文件位置<disk_file_place>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='disk_file_place' and type='disk_file'
    );


-- 插入设置项 阿里云对象存储<aliyun_oss> ==> 拥有OSS控制权限的子账号<access_key_id>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'access_key_id','','aliyun_oss',current_date(),'拥有OSS控制权限的子账号<access_key_id>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='access_key_id' and type='aliyun_oss'
    );

-- 插入设置项 阿里云对象存储<aliyun_oss> ==> 拥有OSS控制权限的子账号<access_key_secret>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'access_key_secret','','aliyun_oss',current_date(),'拥有OSS控制权限的子账号<access_key_secret>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='access_key_secret' and type='aliyun_oss'
    );

-- 插入设置项 阿里云对象存储<aliyun_oss> ==> 储存桶位置(地区)<endpoint>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'endpoint','','aliyun_oss',current_date(),'储存桶位置(地区)<endpoint>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='endpoint' and type='aliyun_oss'
    );


-- 插入设置项 阿里云对象存储<aliyun_oss> ==> 储存桶名称<bucket_name>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'bucket_name','','aliyun_oss',current_date(),'储存桶名称<bucket_name>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='bucket_name' and type='aliyun_oss'
    );

-- 插入设置项 阿里云对象存储<aliyun_oss> ==> 储存桶内储存目录前缀<object_name_prefix>, 默认为 .ikaros/upload
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'object_name_prefix','.ikaros/upload','aliyun_oss',current_date(),'储存桶内储存目录前缀<object_name_prefix>, 默认为 .ikaros/upload' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='object_name_prefix' and type='aliyun_oss'
    );

-- 插入设置项 阿里云对象存储<aliyun_oss> ==> 文件HTTP访问协议<access_protocol>，默认HTTPS
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'access_protocol','https','aliyun_oss',current_date(),'文件HTTP访问协议<access_protocol>，默认HTTPS' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='access_protocol' and type='aliyun_oss'
    );

-- 插入设置项 阿里云对象存储<aliyun_oss> ==> 文件HTTP访问域名<access_domain> 如 example.oss-cn-shanghai.aliyuncs.com
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'access_domain','','aliyun_oss',current_date(),'文件HTTP访问域名<access_domain> 如 example.oss-cn-shanghai.aliyuncs.com' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='access_domain' and type='aliyun_oss'
    );

-- 插入设置项 腾讯云对象存储<tencent_cloud_cos> ==> 拥有COS相关权限的子账号<secret_id>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'secret_id','','tencent_cloud_cos',current_date(),'拥有COS相关权限的子账号<secret_id>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='secret_id' and type='tencent_cloud_cos'
    );

-- 插入设置项 腾讯云对象存储<tencent_cloud_cos> ==> 拥有COS相关权限的子账号<secret_key>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'secret_key','','tencent_cloud_cos',current_date(),'拥有COS相关权限的子账号<secret_key>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='secret_key' and type='tencent_cloud_cos'
    );

-- 插入设置项 腾讯云对象存储<tencent_cloud_cos> ==> 储存桶的名称<bucket_name>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'bucket_name','','tencent_cloud_cos',current_date(),'储存桶的名称<bucket_name>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='bucket_name' and type='tencent_cloud_cos'
    );

-- 插入设置项 腾讯云对象存储<tencent_cloud_cos> ==> 储存桶所属地域<region>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'region','','tencent_cloud_cos',current_date(),'储存桶所属地域<region>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='region' and type='tencent_cloud_cos'
    );

-- 插入设置项 腾讯云对象存储<tencent_cloud_cos> ==> 文件储存的对象键前缀<object_name_prefix>(带目录和完整文件名, 开头不允许有/ 默认为.ikaros/upload)
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'object_name_prefix','.ikaros/upload','tencent_cloud_cos',current_date(),'文件储存的对象键前缀<object_name_prefix>(带目录和完整文件名, 开头不允许有/ 默认为.ikaros/upload)' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='object_name_prefix' and type='tencent_cloud_cos'
    );

-- 插入设置项 腾讯云对象存储<tencent_cloud_cos> ==> 文件HTTP访问协议<access_protocol>，默认为https
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'access_protocol','https','tencent_cloud_cos',current_date(),'文件储存的对象键前缀<object_name_prefix>(带目录和完整文件名, 开头不允许有/ 默认为.ikaros/upload)' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='access_protocol' and type='tencent_cloud_cos'
    );

-- 插入设置项 腾讯云对象存储<tencent_cloud_cos> ==> 文件HTTP访问域名<access_domain>
INSERT INTO setting (setting_id,name,value,type,update_time,description)
SELECT HIBERNATE_SEQUENCE.nextval,'access_domain','','tencent_cloud_cos',current_date(),'文件HTTP访问域名<access_domain>' FROM DUAL WHERE NOT EXISTS(
        SELECT * FROM setting
        WHERE name='access_domain' and type='tencent_cloud_cos'
    );




