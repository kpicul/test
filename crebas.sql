/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     4.4.2018 14:47:19                            */
/*==============================================================*/

drop index Grade_PK;

drop table Grade;

drop index Performance_PK;

drop table Performance;


drop index Teaches_FK2;

drop index Teaches_FK;

drop index Teaches_PK;

drop table Teaches;

drop index Has_FK;


drop index IS_FK;

drop index scored_FK;

drop index IsHaving_FK;

drop index Has_FK2;

drop index Course_PK;

drop table Course;


drop index Year_PK;

drop table Year;

drop index Member_PK;

drop table Member;

drop index Role_PK;

drop table Role;

/*==============================================================*/
/* Table: Course                                                */
/*==============================================================*/
create table Course (
   ID                   INT8                 not null,
   Name                 VARCHAR(30)          not null,
   Description          VARCHAR(50)          null,
   StartDate            DATE                 not null,
   EndDate              DATE                 not null,
   constraint PK_COURSE primary key (ID)
);

/*==============================================================*/
/* Index: Course_PK                                             */
/*==============================================================*/
create unique index Course_PK on Course (
ID
);

/*==============================================================*/
/* Table: Grade                                                 */
/*==============================================================*/
create table Grade (
   ID                   INT8                 not null,
   Per_ID               INT8                 not null,
   Grade                INT4                 not null,
   constraint PK_GRADE primary key (ID)
);

/*==============================================================*/
/* Index: Grade_PK                                              */
/*==============================================================*/
create unique index Grade_PK on Grade (
ID
);

/*==============================================================*/
/* Index: Has_FK                                                */
/*==============================================================*/
create  index Has_FK on Grade (
Per_ID
);

/*==============================================================*/
/* Table: Member                                                */
/*==============================================================*/
create table Member (
   ID                   INT8                 not null,
   Rol_ID               INT4                 not null,
   FirstName            VARCHAR(30)          not null,
   LastName             VARCHAR(30)          not null,
   Password             VARCHAR(50)          not null,
   Username             VARCHAR(30)          not null,
   DateOfBirth          DATE                 null,
   constraint PK_MEMBER primary key (ID)
);

/*==============================================================*/
/* Index: Member_PK                                             */
/*==============================================================*/
create unique index Member_PK on Member (
ID
);

/*==============================================================*/
/* Index: IS_FK                                                 */
/*==============================================================*/
create  index IS_FK on Member (
Rol_ID
);

/*==============================================================*/
/* Table: Performance                                           */
/*==============================================================*/
create table Performance (
   ID                   INT8                 not null,
   Yea_ID               INT8                 not null,
   Mem_ID               INT8                 not null,
   Cou_ID               INT8                 not null,
   StudyYear            INT4                 not null,
   constraint PK_PERFORMANCE primary key (ID)
);

/*==============================================================*/
/* Index: Performance_PK                                        */
/*==============================================================*/
create unique index Performance_PK on Performance (
ID
);

/*==============================================================*/
/* Index: Has_FK2                                               */
/*==============================================================*/
create  index Has_FK2 on Performance (
Cou_ID
);

/*==============================================================*/
/* Index: IsHaving_FK                                           */
/*==============================================================*/
create  index IsHaving_FK on Performance (
Yea_ID
);

/*==============================================================*/
/* Index: scored_FK                                             */
/*==============================================================*/
create  index scored_FK on Performance (
Mem_ID
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role (
   ID                   INT4                 not null,
   Name                 VARCHAR(30)          not null,
   constraint PK_ROLE primary key (ID)
);

/*==============================================================*/
/* Index: Role_PK                                               */
/*==============================================================*/
create unique index Role_PK on Role (
ID
);

/*==============================================================*/
/* Table: Teaches                                               */
/*==============================================================*/
create table Teaches (
   ID                   INT8                 not null,
   Cou_ID               INT8                 not null,
   constraint PK_TEACHES primary key (ID, Cou_ID)
);

/*==============================================================*/
/* Index: Teaches_PK                                            */
/*==============================================================*/
create unique index Teaches_PK on Teaches (
ID,
Cou_ID
);

/*==============================================================*/
/* Index: Teaches_FK                                            */
/*==============================================================*/
create  index Teaches_FK on Teaches (
ID
);

/*==============================================================*/
/* Index: Teaches_FK2                                           */
/*==============================================================*/
create  index Teaches_FK2 on Teaches (
Cou_ID
);

/*==============================================================*/
/* Table: Year                                                  */
/*==============================================================*/
create table Year (
   ID                   INT8                 not null,
   StartDate            DATE                 not null,
   EndDate              DATE                 not null,
   constraint PK_YEAR primary key (ID)
);

/*==============================================================*/
/* Index: Year_PK                                               */
/*==============================================================*/
create unique index Year_PK on Year (
ID
);

alter table Grade
   add constraint FK_GRADE_HAS_PERFORMA foreign key (Per_ID)
      references Performance (ID)
      on delete restrict on update restrict;

alter table Member
   add constraint FK_MEMBER_IS_ROLE foreign key (Rol_ID)
      references Role (ID)
      on delete restrict on update restrict;

alter table Performance
   add constraint FK_PERFORMA_HAS_COURSE foreign key (Cou_ID)
      references Course (ID)
      on delete restrict on update restrict;

alter table Performance
   add constraint FK_PERFORMA_ISHAVING_YEAR foreign key (Yea_ID)
      references Year (ID)
      on delete restrict on update restrict;

alter table Performance
   add constraint FK_PERFORMA_SCORED_MEMBER foreign key (Mem_ID)
      references Member (ID)
      on delete restrict on update restrict;

alter table Teaches
   add constraint FK_TEACHES_TEACHES_COURSE foreign key (Cou_ID)
      references Course (ID)
      on delete restrict on update restrict;

alter table Teaches
   add constraint FK_TEACHES_TEACHES_MEMBER foreign key (ID)
      references Member (ID)
      on delete restrict on update restrict;

