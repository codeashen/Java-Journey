create table AUTHORITY_MEMBER
(
    ID       VARCHAR2(32) default SYS_GUID() not null
        primary key,
    NAME     VARCHAR2(20),
    NICKNAME VARCHAR2(20),
    PHONENUM VARCHAR2(20),
    EMAIL    VARCHAR2(20)
)
/

comment on table AUTHORITY_MEMBER is '会员表'
/

comment on column AUTHORITY_MEMBER.ID is '主键uuid'
/

comment on column AUTHORITY_MEMBER.NAME is '姓名'
/

comment on column AUTHORITY_MEMBER.NICKNAME is '昵称'
/

comment on column AUTHORITY_MEMBER.PHONENUM is '电话号码'
/

comment on column AUTHORITY_MEMBER.EMAIL is '邮箱'
/

INSERT INTO STUDY57.AUTHORITY_MEMBER (ID, NAME, NICKNAME, PHONENUM, EMAIL) VALUES ('E61D65F673D54F68B0861025C69773DB', '张三', '小三', '18888888888', 'zs@163.com');