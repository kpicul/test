insert into role(id,name) values(1,'Admin');
insert into role(id,name) values(2,'Student');
insert into role(id,name) values(3,'Teacher');

insert into member(id,firstname,lastname,username,password,role_id,dateofbirth) values(1,'Luke','Skywalker','jedi','6f37a545750496540e4dfde00d597ef7e1ed7cc077c14052c738873982055f5bfc165815823e70af4a422650a30e2e228b781772ca4a20771ba4c4dee9769b62',2,to_date('01.01.1977','DD.MM.YYYY'));
insert into member(id,firstname,lastname,username,password,role_id) values(2,'Anakin','Skywalker','DarthVader','7aea5e4d0c73a33b3af537ac8c2ea211e959d4d702ef5250117a69a5501aedbae421cad3fee20b52cbc8745f6e40c29b636be84cca2024364e1ecbefceceb4f7',3);
insert into member(id,firstname,lastname,username,password,role_id) values(3,'Sheev','Palpatine','DarthSidious','85810e4f054f0a4fc78bec15511a5e9616065d3cac7cc68cd8adcd3f054bf82b393bf7bde4be182ea8091396d9d09ee14debcb9f57289b5948880bf7bc1620b2',1);
insert into member(id,firstname,lastname,username,password,role_id) values(4,'test','test','test','125d6d03b32c84d492747f79cf0bf6e179d287f341384eb5d6d3197525ad6be8e6df0116032935698f99a09e265073d1d6c32c274591bf1d0a20ad67cba921bc',1);