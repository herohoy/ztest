

create database tree DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use tree;

drop table if exists tree_node;
create table if not exists tree_node (
  id bigint(19) not null comment 'id',
  parent_id bigint(19) default null comment '父节点 Id',
  name varchar(96) not null comment '节点名称',
  created_at datetime not null default CURRENT_TIMESTAMP comment '创建时间',
  created_by bigint(19) not null default 0 comment '特指后台创建人(公司员工 id)',
  updated_at timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  updated_by bigint(19) not null default 0 comment '特指后台更新人(公司员工 id)',
  remark char(255) not null default '' comment '备注',
  primary key (`id`)
)engine=InnoDB default charset=utf8 comment=' 树节点表';

select * from tree_node;

insert into tree_node(id,name,parent_id) values
(1,'中国(中华人民共和国)',null),
(2,'美国(美利坚合众国)',null),
(3,'法国(法兰西共和国)',null),
(4,'南非(南非共和国)',null),
(5,'澳大利亚(澳大利亚联邦)',null),
(6,'俄罗斯(俄罗斯联邦)',null),
(7,'墨西哥(墨西哥合众国)',null),
(8,'埃及(阿拉伯埃及共和国)',null),
(9,'巴西(巴西联邦共和国)',null),
(10,'北京',1),
(11,'天津',1),
(12,'上海',1),
(13,'重庆',1),
(14,'湖北',1),
(15,'陕西',1),
(16,'四川',1),
(17,'辽宁',1),
(18,'广东',1),
(19,'江苏',1),
(20,'东城区',10),
(21,'西城区',10),
(22,'朝阳区',10),
(23,'海淀区',10),
(24,'通州区',10),
(25,'崇文区',10),
(26,'宣武区',10),
(27,'燕郊区',10),
(28,'科技新区',10),
(29,'雄安新区',10),
(30,'王府井街道',20),
(31,'地坛街道',20),
(32,'什刹海街道',20),
(33,'东门街道',20),
(34,'火车站街道',20),
(35,'动物园街道',20),
(36,'八大胡同街道',20),
(37,'西斜街街道',20),
(38,'东单街道',20),
(39,'电视台街道',20),
(40,'王府井大街',30),
(41,'王府井大楼',30),
(42,'地坛公园',30),
(43,'什刹海公园',30),
(44,'东单大楼',30),
(45,'北京动物园',30),
(46,'北京站',30),
(47,'西斜街',30),
(48,'央视大楼(大裤衩)',30),
(49,'八大胡同',30)
;
