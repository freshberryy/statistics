```sql
-- 1)월별 접속자 수

select substr(create_date ,6, 2) 월, count(*) 접속횟수
from request_info
where request_code = 'S' -- S: 접속
group by 1;


-- 2)일자별 접속자 수
select substr(create_date ,9) 일, count(*) 접속횟수
from request_info
where request_code = 'S' 
group by 1;


-- 3) 평균 하루 로그인 수
select avg(a.cnt) 로그인횟수
from(select create_date 
		  , request_code 
		  , count(*) over(partition by create_date) as cnt 
	 from request_info) a 
where a.request_code = 'L'; -- L: 로그인
	  
-- 4) 휴일을 제외한 로그인 수
-- 공휴일을 저장하는 테이블 hol 생성
CREATE TABLE hol (
    hol_date VARCHAR(10) PRIMARY KEY,  
    hol_name VARCHAR(20) NOT NULL   
);

-- hol에 공휴일 삽입
insert into hol(hol_date, hol_name)
values
('2025-01-01', '신정'),
('2025-03-01', '삼일절'),
('2025-05-05', '어린이날'),
('2025-06-06', '현충일'),
('2025-08-15', '광복절'),
('2025-10-03', '개천절'),
('2025-10-09', '한글날'),
('2025-12-25', '성탄절');

-- 휴일을 제외한 로그인 수
select count(*) 로그인수
from request_info a
where not exists(select hol_date
				  from hol b
				  where a.create_date = b.hol_date);
-- 이 방식의 문제: 음력을 반영하기 어려움. 
-- 4차에서 api 활용한 방법으로 바꾸겠습니다.
				 
				 
-- 5. 부서별 월별 로그인 수
select b.hr_organ 부서, substr(a.create_date, 6, 2) 월, count(*) 로그인수
from request_info a join user b 
on a.user_id = b.user_id
where a.request_code = 'L'
group by 1, 2;
```



				 