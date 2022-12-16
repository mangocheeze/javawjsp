show tables;

create table schedule (
  idx   int not null auto_increment primary key,
  mid   varchar(20) not null,					/* 회원 아이디(일정검색시 필요) */
  sDate datetime not null,						/* 일정 등록 날짜 */
  part  varchar(10) not null,					/* 1.모임, 2.업무, 3.학습, 4.여행, 5:기타 */
  content text not null								/* 일정 상세 내역 */
);
/* 예약됐는지 안됐는지까지하려면 필드 하나만 더 추가해주면됨 (예약필드)*/

desc schedule;

insert into schedule values (default,'hkd1234','2022-12-08','학습','프로젝트 계획서 완료');
insert into schedule values (default,'hkd1234','2022-12-09','기타','집안 청소');
insert into schedule values (default,'hkd1234','2022-12-12','학습','관리자화면설계');
insert into schedule values (default,'hkd1234','2022-12-25','학습','오전10시 스터디카페');
insert into schedule values (default,'hkd1234','2022-12-25','모임','크리스마스 모임');
insert into schedule values (default,'hkd1234','2022-12-25','모임','가족조찬모임');
insert into schedule values (default,'hkd1234','2022-12-26','모임','오전 10시 스터디카페1');
insert into schedule values (default,'hkd1234','2022-12-26','모임','오후12시 스터디카페2');
insert into schedule values (default,'hkd1234','2022-12-31','여행','제주도출발');
insert into schedule values (default,'hkd1234','2022-01-05','학습','Spring설정공부');
insert into schedule values (default,'hkd1234','2023-01-05','학습','Spring설정공부');
insert into schedule values (default,'kms1234','2022-12-15','학습','프로젝트 회원관리 완료');
insert into schedule values (default,'kms1234','2022-12-25','모임','크리스마스 모임');

select * from schedule order by sDate desc;
select * from schedule where mid='hkd1234' order by sDate desc;
select * from schedule where mid='hkd1234' and sDate='2022-12-25' order by sDate desc;
select * from schedule where mid='hkd1234' and date_format(sDate,'%Y-%m')='2022-12' order by sDate desc;
-- select * from schedule where mid='hkd1234' and date_format(sDate,'%Y-%m')='2023-1' order by sDate desc;  01이 아닌 1이 오면 안됨 - 그래서 이처리를 해줘야함 
select * from schedule where mid='hkd1234' and date_format(sDate,'%Y-%m')='2023-01' order by sDate desc;
select * from schedule where mid='hkd1234' and date_format(sDate,'%Y-%m')='2022-12' group by part order by sDate desc; /* 12월만 그룹지어서보여줌*/
select * from schedule where mid='hkd1234' and sDate='2022-12-25' group by part order by sDate desc; /* 12월만 그룹지어서보여줌*/
select *,count(*) as partCnt from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m')='2022-12' group by part order by sDate desc;

select * from schedule where mid='hkd1234' and date_format(sDate,'%Y-%m')='2022-12' order by sDate,part desc; -- 처음은 날짜별로 그룹, 그다음 항목(파트)별로 그룹지어져있음

