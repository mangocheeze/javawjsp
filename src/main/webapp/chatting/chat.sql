show tables;

create table chat(
	idx int not null auto_increment primary key,		/*채팅 내용 고유번호*/
	nickName varchar(20) not null, 				    /* 별명*/
	content   varchar(200) not null,				    /* 채팅 내용*/
	cDate 	 datetime not null default now(),	/* 채팅 올린날짜와 시간*/
	avatar	 char(1) not null default '1' 		/* 아바타*/
);

insert into chat values (default, '홍장군', '안녕!!', default,default);
insert into chat values (default, '김장미', '반가워!!', default,'2');

select * from chat order by idx desc;
drop table chat;