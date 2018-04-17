insert into role(id,name) values(1,'Admin');
insert into role(id,name) values(2,'Student');
insert into role(id,name) values(3,'Teacher');

insert into member(id,firstname,lastname,username,password,role_id,dateofbirth) values(1,'Luke','Skywalker','jedi','6f37a545750496540e4dfde00d597ef7e1ed7cc077c14052c738873982055f5bfc165815823e70af4a422650a30e2e228b781772ca4a20771ba4c4dee9769b62',2,to_date('01.01.1977','DD.MM.YYYY')); /*geslo: jediareover */
insert into member(id,firstname,lastname,username,password,role_id) values(2,'Anakin','Skywalker','DarthVader','7aea5e4d0c73a33b3af537ac8c2ea211e959d4d702ef5250117a69a5501aedbae421cad3fee20b52cbc8745f6e40c29b636be84cca2024364e1ecbefceceb4f7',3); /*geslo: iamyourfather */
insert into member(id,firstname,lastname,username,password,role_id) values(3,'Sheev','Palpatine','emperor','f3c7fb659c15dc9116383ceb3358b68cd70ffb6bffa19d83af593f153ef9b4217bffb0078cb2ee4d351efc36d37b5da458dfb22c9af720475c5ae4795e7c5a8f',1); /*geslo: order66 */
insert into member(id,firstname,lastname,username,password,role_id) values(4,'test','test','test','125d6d03b32c84d492747f79cf0bf6e179d287f341384eb5d6d3197525ad6be8e6df0116032935698f99a09e265073d1d6c32c274591bf1d0a20ad67cba921bc',1); /*geslo: test */
insert into member(id,firstname,lastname,username,password,role_id) values(5,'Jože','Ščuka','jscuka','74803cf66c6995a54b25a9065beede67c1e614c1891896c58ba82a1c2848c034aec2b30e7032e040794234abdd5fd55bf04b71942919e0b79fb1f34354019ba1',3); /*geslo: riba */
insert into member(id,firstname,lastname,username,password,role_id) values(6,'Janez','Novak','jnovak','b200d39496af05f53e35890d976cbf34a8511287f22594e8aca8cdffb4a69af4c17c367b80a42e570118a88bae162237d5221c8a9f0623573793b57033606e96',3); /*geslo: novak */
insert into member(id,firstname,lastname,username,password,role_id,dateofbirth) values(7,'Martin','Struna','mstruna','01706b5f13e677483655731d4b9eef1c7d7eb2f0e7356aff20d8829d89ef002dcdd688bf3155c4e05505ee66d5bbf02ee4f92db070374402433e3be1046e27f8',2,to_date('28.02.2003','DD.MM.YYYY')); /* geslo: instrument */
insert into member(id,firstname,lastname,username,password,role_id,dateofbirth) values(8,'Jože','Kočnik','jkocnik','caa266b02f406469f59c3f88f854be69c1f630e9e8a32a0f764acecd7a865cd3122181b860760e97b97912642280b1ffea16c5c655112cce1d55591ac3930b9a',2,to_date('05.03.2004','DD.MM.YYYY')); /* geslo: odlicnjak */
insert into member(id,firstname,lastname,username,password,role_id) values(9,'Martin','Demšar','mdemsar','9db643b9b6b99c01ce30fa6632a5b1faa2f71e3d938bb53f078b5a1526b3dd01badea8b252987d6edb1f4a3fba9822c8aa761213eaedcb05f858b0e4f0beada2',3); /*geslo: geograf */
insert into member(id,firstname,lastname,username,password,role_id) values(10,'Luka','Zajec','lzajec','b100d228416bcf09d164fcda81c82d605ceae8b7b51f07ca95bfd5f90839c85588a712e36628a5a78724ae86ac3673b1c4be7ca1f66bc96d91e78c79bb7847d4',3); /*geslo: korencek */
insert into member(id,firstname,lastname,username,password,role_id) values(11,'Andrej','Lovec','alovec','3aa734544ed0424ef5a5e3a0ac0964acda8c7c678d11da9634a6de24f89cdf111d01548a3a60bdd80388d538f45cba21c57e7111e8484f5e109651912cb67758',3); /*geslo: ustrelizajca */
insert into member(id,firstname,lastname,username,password,role_id) values(12,'Tommy','Wiseau','twiseau','283040b2493945ef6ac15d50f0cd92eea0d5e92a14a701167658cd744e85ba9fa26d882d03bfd3222289200a182cf51b568980256b299456e598b9b1679ed9cf',1); /*geslo: ididnothither */

insert into groups(id,name,year) values (1,'7a',7)
insert into groups(id,name,year) values (2,'8a',8)
insert into groups(id,name,year) values (3,'9a',9)

insert into course(id,name,description) values (1,'Math','Basic math');
insert into course(id,name,description) values (2,'Physics','Basic physics');
insert into course(id,name,description) values (3,'Chemistry','Just chemistry');
insert into course(id,name,description) values (4,'Gymnastics','');
insert into course(id,name,description) values (5,'Geography','');
insert into course(id,name,description) values (6,'Slovenian language','');
insert into course(id,name,description) values (7,'English language','');
insert into course(id,name,description) values (8,'Math 8','Advanced math');
insert into course(id,name,description) values (9,'Physics 8 ','Advanced physics');
insert into course(id,name,description) values (10,'Chemistry 8','Advanced chemistry');
insert into course(id,name,description) values (11,'Gymnastics 8','');
insert into course(id,name,description) values (12,'Geography 8','');
insert into course(id,name,description) values (13,'Slovenian language 8','');
insert into course(id,name,description) values (14,'English language 8','');

insert into year(id,year,startdate,enddate) values (1,'2015/2016',to_date('01.09.2015','DD.MM.YYYY'),to_date('25.06.2016','DD.MM.YYYY'))
insert into year(id,year,startdate,enddate) values (2,'2016/2017',to_date('01.09.2016','DD.MM.YYYY'),to_date('25.06.2017','DD.MM.YYYY'))
insert into year(id,year,startdate,enddate) values (3,'2017/2018',to_date('01.09.2017','DD.MM.YYYY'),to_date('25.06.2018','DD.MM.YYYY'))

insert into teaches(id,course_id,member_id,year_id) values(1,1,5,2)  /*Math : jscuka : 2016*/
insert into teaches(id,course_id,member_id,year_id) values(2,1,6,2) /*Math : jnovak*/
insert into teaches(id,course_id,member_id,year_id) values(3,2,2,2) /*Physics : DarthVader*/
insert into teaches(id,course_id,member_id,year_id) values(4,3,2,2) /*Chemistry : DarthVader*/
insert into teaches(id,course_id,member_id,year_id) values(5,3,6,2) /*Chemistry : jnovak*/
insert into teaches(id,course_id,member_id,year_id) values(6,8,5,2)  /*Math8 : jscuka*/
insert into teaches(id,course_id,member_id,year_id) values(7,9,2,2) /*Physics8 : DarthVader*/
insert into teaches(id,course_id,member_id,year_id) values(8,11,2,2) /*Gymnastics8 : DarthVader*/
insert into teaches(id,course_id,member_id,year_id) values(9,10,6,2) /*chemistry8 : jnovak*/
insert into teaches(id,course_id,member_id,year_id) values(10,12,9,2) /*Geography8 : mdemsar*/
insert into teaches(id,course_id,member_id,year_id) values(11,13,10,2) /*Slovenian8 : lzajec*/
insert into teaches(id,course_id,member_id,year_id) values(12,14,11,2) /*English8 : alovec*/

insert into groupcourse(id,group_id,teaches_id) values(1,2,6) /* 8a : math8*/
insert into groupcourse(id,group_id,teaches_id) values(2,2,7)/* 8a : physics8 */
insert into groupcourse(id,group_id,teaches_id) values(3,2,8) /* 8a : gymnastics8*/
insert into groupcourse(id,group_id,teaches_id) values(4,2,9) /*8a : chemistry8*/
insert into groupcourse(id,group_id,teaches_id) values(5,2,10) /* 8a : geography8 */
insert into groupcourse(id,group_id,teaches_id) values(6,2,11) /* 8a : slovenian 8*/
insert into groupcourse(id,group_id,teaches_id) values(7,2,12)/* 8a : english 8*/

insert into performance(id,groupcourse_id,student_id,finished) values (1,1,7,true); /* 8a,math:  mstruna */
insert into performance(id,groupcourse_id,student_id,finished) values (2,2,7,true); /* 8a,physics8:  mstruna */
insert into performance(id,groupcourse_id,student_id) values (3,3,7); /* 8a,gymnastics 8:  mstruna */
insert into performance(id,groupcourse_id,student_id,finished) values (4,4,7,true); /* 8a,chemistry8:  mstruna */
insert into performance(id,groupcourse_id,student_id,finished) values (5,5,7,true); /* 8a,geography8:  mstruna */
insert into performance(id,groupcourse_id,student_id,finished) values (6,6,7,true); /* 8a,slovenian8:  mstruna */
insert into performance(id,groupcourse_id,student_id,finished) values (7,7,7,true); /* 8a,english8:  mstruna */


insert into grade(id,grade,performance_id) values(1,1,1) /* 1 : math8 : mstruna */
insert into grade(id,grade,performance_id) values(2,3,1) /* 3 : math8 : mstruna */
insert into grade(id,grade,performance_id) values(3,3,1) /* 3 : math8 : mstruna */
insert into grade(id,grade,performance_id) values(4,2,1) /* 3 : math8 : mstruna */
insert into grade(id,grade,performance_id) values(5,5,2) /* 5 : physics8 : mstruna */
insert into grade(id,grade,performance_id) values(6,5,2) /* 5 : physics8 : mstruna */
insert into grade(id,grade,performance_id) values(7,5,2) /* 5 : physics8 : mstruna */
insert into grade(id,grade,performance_id) values(8,1,3) /* 1 : gymnastics8 : mstruna */
insert into grade(id,grade,performance_id) values(9,1,3) /* 1 : gymnastics8 : mstruna */
insert into grade(id,grade,performance_id) values(10,2,3) /* 2 : gymnastics8 : mstruna */
insert into grade(id,grade,performance_id) values(11,5,4) /* 1 : chemistry8 : mstruna */
insert into grade(id,grade,performance_id) values(12,5,4) /* 1 : chemistry8 : mstruna */
insert into grade(id,grade,performance_id) values(13,5,4) /* 1 : chemistry8 : mstruna */
insert into grade(id,grade,performance_id) values(14,1,5) /* 1 : geography8 : mstruna */
insert into grade(id,grade,performance_id) values(15,2,5) /* 1 : geography8 : mstruna */
insert into grade(id,grade,performance_id) values(16,2,5) /* 1 : geography8 : mstruna */
insert into grade(id,grade,performance_id) values(17,4,5) /* 1 : geography8 : mstruna */
insert into grade(id,grade,performance_id) values(18,4,6) /* 1 : slovenian8 : mstruna */
insert into grade(id,grade,performance_id) values(19,4,6) /* 1 : slovenian8 : mstruna */
insert into grade(id,grade,performance_id) values(20,5,6) /* 1 : slovenian8 : mstruna */
insert into grade(id,grade,performance_id) values(21,1,7) /* 1 : english8 : mstruna */
insert into grade(id,grade,performance_id) values(22,2,7) /* 1 : english8 : mstruna */
insert into grade(id,grade,performance_id) values(23,2,7) /* 1 : english8 : mstruna */
insert into grade(id,grade,performance_id) values(24,2,7) /* 1 : english8 : mstruna */


