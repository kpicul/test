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

insert into course(id,name,description) values (1,'Math','Basic math');
insert into course(id,name,description) values (2,'Physics','Basic physics');
insert into course(id,name,description) values (3,'Chemistry','Just chemistry');
insert into course(id,name,description) values (4,'Gymnastics','');
insert into course(id,name,description) values (5,'Geography','');
insert into course(id,name,description) values (6,'Slovenian language','');
insert into course(id,name,description) values (7,'English language','');

insert into teaches(course_id,member_id) values(1,5)  /*Math : jscuka*/
insert into teaches(course_id,member_id) values(1,6) /*Math : jnovak*/
insert into teaches(course_id,member_id) values(2,2) /*Physics : DarthVader*/
insert into teaches(course_id,member_id) values(3,2) /*Chemistry : DarthVader*/
insert into teaches(course_id,member_id) values(3,6) /*Chemistry : jnovak*/

insert into year(id,year,startdate,enddate) values (1,'2015/2016',to_date('01.09.2015','DD.MM.YYYY'),to_date('25.06.2016','DD.MM.YYYY'))
insert into year(id,year,startdate,enddate) values (2,'2016/2017',to_date('01.09.2016','DD.MM.YYYY'),to_date('25.06.2017','DD.MM.YYYY'))
insert into year(id,year,startdate,enddate) values (3,'2017/2018',to_date('01.09.2017','DD.MM.YYYY'),to_date('25.06.2018','DD.MM.YYYY'))

insert into coursedates(id,year_id,course_id) values(1,1,1) /*2015 : Math*/
insert into coursedates(id,year_id,course_id) values(2,1,2) /*2015 : Physics*/
insert into coursedates(id,year_id,course_id) values(3,2,2) /*2016 : Physics*/
insert into coursedates(id,year_id,course_id) values(4,2,6) /*2016 : Slovenian language*/
insert into coursedates(id,year_id,course_id) values(5,2,3) /*2016 : Chemistry*/
insert into coursedates(id,year_id,course_id) values(6,3,3) /*2017 : Chemistry*/
insert into coursedates(id,year_id,course_id) values(7,2,1) /*2016 : Math*/

insert into performance(id,studyyear,cdates_id,student_id,teacher_id) values (1,7,1,7,5); /* Math2015 : mstruna : jscuka*/
insert into performance(id,studyyear,cdates_id,student_id,teacher_id) values (2,9,5,8,2); /* Chemistry2016 : jkocnik : DarthVader*/
insert into performance(id,studyyear,cdates_id,student_id,teacher_id) values (3,9,7,8,6); /* Math2016 : jkocnik : jnovak*/
insert into performance(id,studyyear,cdates_id,student_id,teacher_id) values (4,7,6,1,2); /* Chemistry2017 : jedi : DarthVader*/

insert into grade(id,grade,performance_id) values(1,5,1) /* 5 : Math2015 : mstruan */
insert into grade(id,grade,performance_id) values(2,4,2) /* 4 : Chemistry2017 : jedi*/
insert into grade(id,grade,performance_id) values(3,3,3) /* 3 : Math2016 : jkocnik */
insert into grade(id,grade,performance_id) values(4,4,3) /* 4 : Math2016 : jkocnik*/
insert into grade(id,grade,performance_id) values(5,4,1) /* 4 : Math2015 : mstruna*/