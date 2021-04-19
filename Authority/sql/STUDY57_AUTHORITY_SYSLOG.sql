create table AUTHORITY_SYSLOG
(
    ID            VARCHAR2(32) default SYS_GUID() not null
        primary key,
    VISITTIME     TIMESTAMP(6),
    USERNAME      VARCHAR2(50),
    IP            VARCHAR2(30),
    URL           VARCHAR2(50),
    EXECUTIONTIME NUMBER,
    METHOD        VARCHAR2(200)
)
/

comment on column AUTHORITY_SYSLOG.ID is '主键uuid'
/

comment on column AUTHORITY_SYSLOG.VISITTIME is '访问时间'
/

comment on column AUTHORITY_SYSLOG.USERNAME is '操作者用户名'
/

comment on column AUTHORITY_SYSLOG.IP is '访问ip'
/

comment on column AUTHORITY_SYSLOG.URL is '访问资源url'
/

comment on column AUTHORITY_SYSLOG.EXECUTIONTIME is '执行时长'
/

comment on column AUTHORITY_SYSLOG.METHOD is '访问方法'
/

