/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018-05-11 16:41:18                          */
/*==============================================================*/


drop index Index_5 on sys_menu;

drop table if exists sys_menu;

drop index Index_1 on sys_organization;

drop table if exists sys_organization;

drop index Index_4 on sys_role;

drop table if exists sys_role;

drop index Index_6 on sys_role_menu;

drop table if exists sys_role_menu;

drop index Index_2 on sys_user;

drop table if exists sys_user;

drop index Index_3 on sys_user_role;

drop table if exists sys_user_role;

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   char(32) not null comment 'id',
   name                 varchar(30) not null comment '����',
   remark               varchar(256) comment '��ע',
   url                  varchar(256) comment '����',
   deep                 char(1) not null default '1' comment '���,Ĭ����1',
   code                 varchar(20) not null comment '����',
   sup_code             varchar(100) comment '�ϼ�����',
   sup_name             varchar(30) comment '�ϼ�����',
   sup_codes            varchar(350) comment '�ϼ����뼯��',
   sup_names            varchar(300) comment '�ϼ����Ƽ���',
   status               char(1) not null default '1' comment '״̬,0���ã�1����',
   sort                 int default 10000 comment '����',
   primary key (id),
   delFlag              char(1) not null comment 'ɾ����ʶ(0:δɾ����1����ɾ��)',
   addTime              datetime not null comment '����ʱ��',
   addUserId            varchar(32) not null comment '������',
   addMark              varchar(10) null comment '����ҳ��ID',
   updTime              datetime comment '����ʱ��',
   updUserId            varchar(32) comment '������',
   updMark              varchar(10) comment '����ҳ��ID',
   rec_code              decimal(20,0) comment '��̬����'

)
;

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
   name                 varchar(30) not null comment '����',
   remark               varchar(256) comment '��ע',
   src_area_id          char(32) comment '��������',
   org_type             char(1) not null comment '�������ͣ�0��˾��1���ţ�2�飬3����',
   code                 varchar(20) not null comment '��������',
   sup_code             varchar(20) comment '�ϼ���������',
   sup_name             varchar(30) comment '�ϼ���������',
   sup_codes            varchar(350) comment '�ϼ��ṹ���뼯��',
   sup_names            varchar(300) comment '�ϼ��������Ƽ���',
   primary key (id),
   delFlag              char(1) not null comment 'ɾ����ʶ(0:δɾ����1����ɾ��)',
   addTime              datetime not null comment '����ʱ��',
   addUserId            varchar(32) not null comment '������',
   addMark              varchar(10) null comment '����ҳ��ID',
   updTime              datetime comment '����ʱ��',
   updUserId            varchar(32) comment '������',
   updMark              varchar(10) comment '����ҳ��ID',
   rec_code              decimal(20,0) comment '��̬����'

)
;

alter table sys_organization comment '��֯����';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on sys_organization
(
   code
);

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   char(32) not null comment 'id',
   name                 varchar(30) not null comment '��ɫ����',
   status               char(1) not null default '1' comment '״̬,0���ã�1����',
   remark               varchar(256) comment '��ע',
   primary key (id),
   delFlag              char(1) not null comment 'ɾ����ʶ(0:δɾ����1����ɾ��)',
   addTime              datetime not null comment '����ʱ��',
   addUserId            varchar(32) not null comment '������',
   addMark              varchar(10) null comment '����ҳ��ID',
   updTime              datetime comment '����ʱ��',
   updUserId            varchar(32) comment '������',
   updMark              varchar(10) comment '����ҳ��ID',
   rec_code              decimal(20,0) comment '��̬����'

)
;

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
   primary key (id),
   delFlag              char(1) not null comment 'ɾ����ʶ(0:δɾ����1����ɾ��)',
   addTime              datetime not null comment '����ʱ��',
   addUserId            varchar(32) not null comment '������',
   addMark              varchar(10) null comment '����ҳ��ID',
   updTime              datetime comment '����ʱ��',
   updUserId            varchar(32) comment '������',
   updMark              varchar(10) comment '����ҳ��ID',
   rec_code              decimal(20,0) comment '��̬����'

)
;

alter table sys_role_menu comment '��ɫ�˵�';

/*==============================================================*/
/* Index: Index_6                                               */
/*==============================================================*/
create index Index_6 on sys_role_menu
(
   id
);

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   char(32) not null comment 'id',
   user_name            varchar(50) not null comment '�û���',
   src_org_code         char(32) comment '������������',
   password             varchar(30) not null comment '����',
   contact              varchar(20) comment '��ϵ��ʽ',
   work_num             varchar(20) not null comment '����',
   full_name            varchar(20) comment '����',
   status               char(1) not null default '1' comment '״̬,0���ã�1����',
   super_user           char(1) not null default '0' comment '�Ƿ񳬼��û���0��1��',
   remark               varchar(250) comment '��ע',
   primary key (id),
   delFlag              char(1) not null comment 'ɾ����ʶ(0:δɾ����1����ɾ��)',
   addTime              datetime not null comment '����ʱ��',
   addUserId            varchar(32) not null comment '������',
   addMark              varchar(10) null comment '����ҳ��ID',
   updTime              datetime comment '����ʱ��',
   updUserId            varchar(32) comment '������',
   updMark              varchar(10) comment '����ҳ��ID',
   rec_code              decimal(20,0) comment '��̬����'

)
;

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
   primary key (id),
   delFlag              char(1) not null comment 'ɾ����ʶ(0:δɾ����1����ɾ��)',
   addTime              datetime not null comment '����ʱ��',
   addUserId            varchar(32) not null comment '������',
   addMark              varchar(10) null comment '����ҳ��ID',
   updTime              datetime comment '����ʱ��',
   updUserId            varchar(32) comment '������',
   updMark              varchar(10) comment '����ҳ��ID',
   rec_code              decimal(20,0) comment '��̬����'

)
;

alter table sys_user_role comment '�û���ɫ';

/*==============================================================*/
/* Index: Index_3                                               */
/*==============================================================*/
create index Index_3 on sys_user_role
(
   id
);

alter table sys_role_menu add constraint FK_Reference_4 foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;

alter table sys_role_menu add constraint FK_Reference_5 foreign key (menu_id)
      references sys_menu (id) on delete restrict on update restrict;

alter table sys_user add constraint FK_Reference_1 foreign key (src_org_code)
      references sys_organization (id) on delete restrict on update restrict;

alter table sys_user_role add constraint FK_Reference_2 foreign key (user_id)
      references sys_user (id) on delete restrict on update restrict;

alter table sys_user_role add constraint FK_Reference_3 foreign key (role_id)
      references sys_role (id) on delete restrict on update restrict;

