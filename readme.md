```sql
-- 1)월별 접속자 수

select substr(create_date ,6, 2) 월, count(distinct user_id) 접속자수
from request_info
where request_code = 'S' -- S: 접속
group by substr(create_date ,6, 2);


-- 2)일자별 접속자 수
select substr(create_date ,9) 일, count(distinct user_id) 접속자수
from request_info
where request_code = 'S' 
group by substr(create_date ,9);


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
-- 서비스단에서 처리하는 것으로 변경

-- 휴일을 제외한 로그인 수
select count(*)
        from request_info a
        where a.request_code = 'L'
        <if test="holidays != null and holidays.size > 0">
            and a.create_date not in
            <foreach collection="holidays" item="hol" open="(" separator="," close=")">
                #{hol}
            </foreach>
        </if>

				 
				 
-- 5. 부서별 월별 로그인 수
select b.hr_organ 부서, substr(a.create_date, 6, 2) 월, count(*) 로그인수
from request_info a join user b 
on a.user_id = b.user_id
where a.request_code = 'L'
group by b.hr_organ, substr(a.create_date, 6, 2);
```



				 