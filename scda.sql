drop table if exists demo;

drop index Index_2 on sys_user;

drop table if exists sys_user;

drop index Index_3 on sys_user_role;

drop table if exists sys_user_role;

drop index Index_4 on sys_role;

drop table if exists sys_role;

drop index Index_6 on sys_role_menu;

drop table if exists sys_role_menu;

drop index Index_5 on sys_menu;

drop table if exists sys_menu;

drop index Index_1 on sys_organization;

drop table if exists sys_organization;

/*==============================================================*/
/* Table: demo                                                  */
/*==============================================================*/
create table demo
(
   id                   char(32) not null comment 'id',
   code                 varchar(100) not null comment '编码',
   name                 varchar(100),
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT '删除标识(1:已删除,0:正常)',
   add_time datetime NOT NULL COMMENT '创建时间',
   add_user_code varchar(64) NOT NULL COMMENT '创建者',
   add_mark varchar(250) NOT NULL COMMENT '新增备注',
   upd_time datetime DEFAULT NULL COMMENT '更新时间',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '更新者',
   upd_mark varchar(250) DEFAULT NULL COMMENT '更新备注'
);


/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   char(32) not null comment 'id',
   code                 varchar(100) not null comment '编码',
   user_name            varchar(50) not null comment '用户名',
   password             varchar(100) not null comment '密码',
   contact              varchar(20) comment '联系方式',
   work_num             varchar(20) not null comment '工号',
   full_name            varchar(20) comment '姓名',
   remark               varchar(250) comment '备注',
   src_org_code         char(32) comment '所属机构编码',
   non_expired          bit default 1 comment '没有过期',
   enabled              bit default 1 comment '启用',
   non_locked           bit default 1 comment '没有锁定',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT '删除标识(1:已删除,0:正常)',
   add_time datetime NOT NULL COMMENT '创建时间',
   add_user_code varchar(64) NOT NULL COMMENT '创建者',
   add_mark varchar(250) NOT NULL COMMENT '新增备注',
   upd_time datetime DEFAULT NULL COMMENT '更新时间',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '更新者',
   upd_mark varchar(250) DEFAULT NULL COMMENT '更新备注'
);

alter table sys_user comment '用户';

/*==============================================================*/
/* Index: Index_2                                               */
/*==============================================================*/
create unique index Index_2 on sys_user
(
   work_num
);



/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   id                   char(32) not null comment 'id',
   user_id              char(32) not null comment '用户id',
   role_id              char(32) not null comment '角色id',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT '删除标识(1:已删除,0:正常)',
   add_time datetime NOT NULL COMMENT '创建时间',
   add_user_code varchar(64) NOT NULL COMMENT '创建者',
   add_mark varchar(250) NOT NULL COMMENT '新增备注',
   upd_time datetime DEFAULT NULL COMMENT '更新时间',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '更新者',
   upd_mark varchar(250) DEFAULT NULL COMMENT '更新备注'
);

alter table sys_user_role comment '用户角色';

/*==============================================================*/
/* Index: Index_3                                               */
/*==============================================================*/
create index Index_3 on sys_user_role
(
   id
);


/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   char(32) not null comment 'id',
   code                 varchar(100) not null comment '编码',
   name                 varchar(30) not null comment '角色名称',
   status               char(1) not null default '1' comment '状态,0禁用，1启用',
   remark               varchar(256) comment '备注',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT '删除标识(1:已删除,0:正常)',
   add_time datetime NOT NULL COMMENT '创建时间',
   add_user_code varchar(64) NOT NULL COMMENT '创建者',
   add_mark varchar(250) NOT NULL COMMENT '新增备注',
   upd_time datetime DEFAULT NULL COMMENT '更新时间',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '更新者',
   upd_mark varchar(250) DEFAULT NULL COMMENT '更新备注'
);

alter table sys_role comment '角色';

/*==============================================================*/
/* Index: Index_4                                               */
/*==============================================================*/
create unique index Index_4 on sys_role
(
   name
);

/*==============================================================*/
/* Table: sys_role_menu                                         */
/*==============================================================*/
create table sys_role_menu
(
   id                   char(32) not null comment 'id',
   menu_id              char(32) not null comment '菜单id',
   role_id              char(32) not null comment '角色id',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT '删除标识(1:已删除,0:正常)',
   add_time datetime NOT NULL COMMENT '创建时间',
   add_user_code varchar(64) NOT NULL COMMENT '创建者',
   add_mark varchar(250) NOT NULL COMMENT '新增备注',
   upd_time datetime DEFAULT NULL COMMENT '更新时间',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '更新者',
   upd_mark varchar(250) DEFAULT NULL COMMENT '更新备注'
);

alter table sys_role_menu comment '角色菜单';

/*==============================================================*/
/* Index: Index_6                                               */
/*==============================================================*/
create index Index_6 on sys_role_menu
(
   id
);


/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   char(32) not null comment 'id',
   code                 varchar(100) not null comment '编码',
   name                 varchar(30) not null comment '名称',
   remark               varchar(256) comment '备注',
   url                  varchar(256) comment '链接',
   deep                 char(1) not null default '1' comment '深度,默认是1',
   sup_code             varchar(100) comment '上级编码',
   sup_name             varchar(30) comment '上级名称',
   sup_codes            varchar(1000) comment '上级编码集合',
   sup_names            varchar(300) comment '上级名称集合',
   status               char(1) not null default '1' comment '状态,0禁用，1启用',
   sort                 int default 10000 comment '排序',
   type                 char(1) comment '类型,0菜单,1api',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT '删除标识(1:已删除,0:正常)',
   add_time datetime NOT NULL COMMENT '创建时间',
   add_user_code varchar(64) NOT NULL COMMENT '创建者',
   add_mark varchar(250) NOT NULL COMMENT '新增备注',
   upd_time datetime DEFAULT NULL COMMENT '更新时间',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '更新者',
   upd_mark varchar(250) DEFAULT NULL COMMENT '更新备注'
);

alter table sys_menu comment '菜单';

/*==============================================================*/
/* Index: Index_5                                               */
/*==============================================================*/
create unique index Index_5 on sys_menu
(
   code
);


/*==============================================================*/
/* Table: sys_organization                                      */
/*==============================================================*/
create table sys_organization
(
   id                   char(32) not null comment 'id',
   code                 varchar(100) not null comment '编码',
   name                 varchar(30) not null comment '名称',
   remark               varchar(256) comment '备注',
   src_area_id          char(32) comment '归属区域',
   org_type             char(1) not null comment '机构类型，0公司，1部门，2组，3其他',
   sup_code             varchar(100) comment '上级机构编码',
   sup_name             varchar(30) comment '上级机构名称',
   sup_codes            varchar(1000) comment '上级结构编码集合',
   sup_names            varchar(300) comment '上级机构名称集合',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT '删除标识(1:已删除,0:正常)',
   add_time datetime NOT NULL COMMENT '创建时间',
   add_user_code varchar(64) NOT NULL COMMENT '创建者',
   add_mark varchar(250) NOT NULL COMMENT '新增备注',
   upd_time datetime DEFAULT NULL COMMENT '更新时间',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '更新者',
   upd_mark varchar(250) DEFAULT NULL COMMENT '更新备注'
);

alter table sys_organization comment '组织机构';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on sys_organization
(
   code
);
