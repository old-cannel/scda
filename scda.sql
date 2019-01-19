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
   code                 varchar(100) not null comment '����',
   name                 varchar(100),
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT 'ɾ����ʶ(1:��ɾ��,0:����)',
   add_time datetime NOT NULL COMMENT '����ʱ��',
   add_user_code varchar(64) NOT NULL COMMENT '������',
   add_mark varchar(250) NOT NULL COMMENT '������ע',
   upd_time datetime DEFAULT NULL COMMENT '����ʱ��',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '������',
   upd_mark varchar(250) DEFAULT NULL COMMENT '���±�ע'
);


/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   char(32) not null comment 'id',
   code                 varchar(100) not null comment '����',
   user_name            varchar(50) not null comment '�û���',
   password             varchar(100) not null comment '����',
   contact              varchar(20) comment '��ϵ��ʽ',
   work_num             varchar(20) not null comment '����',
   full_name            varchar(20) comment '����',
   remark               varchar(250) comment '��ע',
   src_org_code         char(32) comment '������������',
   non_expired          bit default 1 comment 'û�й���',
   enabled              bit default 1 comment '����',
   non_locked           bit default 1 comment 'û������',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT 'ɾ����ʶ(1:��ɾ��,0:����)',
   add_time datetime NOT NULL COMMENT '����ʱ��',
   add_user_code varchar(64) NOT NULL COMMENT '������',
   add_mark varchar(250) NOT NULL COMMENT '������ע',
   upd_time datetime DEFAULT NULL COMMENT '����ʱ��',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '������',
   upd_mark varchar(250) DEFAULT NULL COMMENT '���±�ע'
);

alter table sys_user comment '�û�';

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
   user_id              char(32) not null comment '�û�id',
   role_id              char(32) not null comment '��ɫid',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT 'ɾ����ʶ(1:��ɾ��,0:����)',
   add_time datetime NOT NULL COMMENT '����ʱ��',
   add_user_code varchar(64) NOT NULL COMMENT '������',
   add_mark varchar(250) NOT NULL COMMENT '������ע',
   upd_time datetime DEFAULT NULL COMMENT '����ʱ��',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '������',
   upd_mark varchar(250) DEFAULT NULL COMMENT '���±�ע'
);

alter table sys_user_role comment '�û���ɫ';

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
   code                 varchar(100) not null comment '����',
   name                 varchar(30) not null comment '��ɫ����',
   status               char(1) not null default '1' comment '״̬,0���ã�1����',
   remark               varchar(256) comment '��ע',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT 'ɾ����ʶ(1:��ɾ��,0:����)',
   add_time datetime NOT NULL COMMENT '����ʱ��',
   add_user_code varchar(64) NOT NULL COMMENT '������',
   add_mark varchar(250) NOT NULL COMMENT '������ע',
   upd_time datetime DEFAULT NULL COMMENT '����ʱ��',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '������',
   upd_mark varchar(250) DEFAULT NULL COMMENT '���±�ע'
);

alter table sys_role comment '��ɫ';

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
   menu_id              char(32) not null comment '�˵�id',
   role_id              char(32) not null comment '��ɫid',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT 'ɾ����ʶ(1:��ɾ��,0:����)',
   add_time datetime NOT NULL COMMENT '����ʱ��',
   add_user_code varchar(64) NOT NULL COMMENT '������',
   add_mark varchar(250) NOT NULL COMMENT '������ע',
   upd_time datetime DEFAULT NULL COMMENT '����ʱ��',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '������',
   upd_mark varchar(250) DEFAULT NULL COMMENT '���±�ע'
);

alter table sys_role_menu comment '��ɫ�˵�';

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
   code                 varchar(100) not null comment '����',
   name                 varchar(30) not null comment '����',
   remark               varchar(256) comment '��ע',
   url                  varchar(256) comment '����',
   deep                 char(1) not null default '1' comment '���,Ĭ����1',
   sup_code             varchar(100) comment '�ϼ�����',
   sup_name             varchar(30) comment '�ϼ�����',
   sup_codes            varchar(1000) comment '�ϼ����뼯��',
   sup_names            varchar(300) comment '�ϼ����Ƽ���',
   status               char(1) not null default '1' comment '״̬,0���ã�1����',
   sort                 int default 10000 comment '����',
   type                 char(1) comment '����,0�˵�,1api',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT 'ɾ����ʶ(1:��ɾ��,0:����)',
   add_time datetime NOT NULL COMMENT '����ʱ��',
   add_user_code varchar(64) NOT NULL COMMENT '������',
   add_mark varchar(250) NOT NULL COMMENT '������ע',
   upd_time datetime DEFAULT NULL COMMENT '����ʱ��',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '������',
   upd_mark varchar(250) DEFAULT NULL COMMENT '���±�ע'
);

alter table sys_menu comment '�˵�';

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
   code                 varchar(100) not null comment '����',
   name                 varchar(30) not null comment '����',
   remark               varchar(256) comment '��ע',
   src_area_id          char(32) comment '��������',
   org_type             char(1) not null comment '�������ͣ�0��˾��1���ţ�2�飬3����',
   sup_code             varchar(100) comment '�ϼ���������',
   sup_name             varchar(30) comment '�ϼ���������',
   sup_codes            varchar(1000) comment '�ϼ��ṹ���뼯��',
   sup_names            varchar(300) comment '�ϼ��������Ƽ���',
   primary key (id)
   ,   
   del_flag char(1)  NOT NULL COMMENT 'ɾ����ʶ(1:��ɾ��,0:����)',
   add_time datetime NOT NULL COMMENT '����ʱ��',
   add_user_code varchar(64) NOT NULL COMMENT '������',
   add_mark varchar(250) NOT NULL COMMENT '������ע',
   upd_time datetime DEFAULT NULL COMMENT '����ʱ��',
   upd_user_code varchar(64) DEFAULT NULL COMMENT '������',
   upd_mark varchar(250) DEFAULT NULL COMMENT '���±�ע'
);

alter table sys_organization comment '��֯����';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on sys_organization
(
   code
);
