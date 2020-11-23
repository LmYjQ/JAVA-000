CREATE TABLE USER (ID INT(11)  primary key not null unique auto_increment, 
user_id int(11) not null,
gender tinyint(2) unsigned not null default 0 comment '0：未知；\\n 1：男；\\n 2：女。',
mobile_phone INT(11) UNSIGNED COMMENT '手机号',
address VARCHAR(200) NOT NULL COMMENT '地址',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

create table product(ID INT(11)  primary key not null unique auto_increment, 
cate_id1 smallint not null default 0 comment '一级类目id',
cate_id2 smallint not null default 0 comment '二级类目id',
cate_id3 mediumint not null default 0 comment '三级类目id',
price DECIMAL(8,2) NOT NULL COMMENT '商品销售价格',
status tinyint not null default 0 comment '商品状态',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 comment '商品信息';



create table order (
 ID INT(11)  primary key not null unique auto_increment, 
 USER_id INT,
 product_id INT,
 order_status tinyint comment '订单状态',
 create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 update_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 '订单最终状态';

create table order_snapshot (
 ID INT(11)  primary key not null unique auto_increment, 
 USER_id INT,
 product_id INT,
 order_status tinyint comment '订单状态',
 create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
 ) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 '订单快照';