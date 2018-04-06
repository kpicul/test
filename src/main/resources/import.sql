insert into role(id,name) values(1,'Admin');
insert into role(id,name) values(2,'Student');
insert into role(id,name) values(3,'Teacher');

insert into member(id,firstname,lastname,username,password,role_id,dateofbirth) values(1,'Luke','Skywalker','jedi','f3706e5fb4e3a7b7137eb132a73f12c9dc2992a643dc6f2edd07f9dcafa79785f72aa71bfc5200f4faca8b8b42268771d3ea83c1be516a3bb5934e4d1bb6918f n�X�#�VE�x�3o�',2,to_date('01.01.1977','DD.MM.YYYY'));
insert into member(id,firstname,lastname,username,password,role_id) values(2,'Anakin','Skywalker','DarthVader','bdbcf02ee1dd979b8a69286d9ca8617724dc1fd57a7c8ef086da7632613d06e3c7f0da6c671f394223238275aa769c0bddbdfa97edda6fb32c4827503375d4d3 �/�\���g�E���f]',3);
insert into member(id,firstname,lastname,username,password,role_id) values(3,'Sheev','Palpatine','DarthSidious','abf1f795d1232aa531a8d62983b313ae5a99f5ad19a9a3d8c66c81d1ab736d1c20a11f0952aaddb0e48ad9791368def37773c96190f0b17892cc3d961b90e552 f�y*;�T�!��G���',1);
insert into member(id,firstname,lastname,username,password,role_id) values(4,'test','test','test','125d6d03b32c84d492747f79cf0bf6e179d287f341384eb5d6d3197525ad6be8e6df0116032935698f99a09e265073d1d6c32c274591bf1d0a20ad67cba921bc',1);