show user;

select *
from tbl_member_login;

select *
from tbl_member;

select *
from tbl_login_record;

select *
from tbl_product
order by product_num desc;

select *
from tbl_address;


insert into tbl_member(userid , username, mobile) values('jangjy@gmail.com','?��진영','01088665389');
insert into tbl_member(userid ,  username, mobile) values('eomjh@gmail.com','?��?��?��' ,'01012311235');
insert into tbl_member(userid ,  username, mobile) values('kangkc@gmail.com','강감�?', '01096331236');
insert into tbl_member(userid , username, mobile) values('josh@gmail.com','조상?��', '01021781237');

commit;

insert into tbl_member_login(userid , passwd , UpdATE_PASSWD_DATE) values('jangjy@gmail.com', 'qwer1234$', sysdate);
insert into tbl_member_login(userid , passwd , UpdATE_PASSWD_DATE) values('eomjh@gmail.com', 'qwer1234$', sysdate);
insert into tbl_member_login(userid , passwd , UpdATE_PASSWD_DATE) values('kangkc@gmail.com', 'qwer1234$', sysdate);
insert into tbl_member_login(userid , passwd , UpdATE_PASSWD_DATE) values('josh@gmail.com', 'qwer1234$', sysdate);




commit;




select userid , passwd , secession , rest_member , update_passwd_date
from tbl_member_login
where userid ='josh@gmail.com' and passwd = 'qwer1234$';

-- 멤버 로그?�� ?��?���? select *
select *
from tbl_member_login;


select *
from tbl_member;

-- 멤버 ?��?���? select *

 




update tbl_member set membership = 0
where userid = 'less@gmail.com';

update tbl_member_login set passwd = 'qwer1234$'
where userid = 'jangjy@gmail.com';

update tbl_member set membership = 0
where userid = 'less@gmail.com';

commit;


select* from tbl_member_login;



-- ?��?�� ?��?��블에 ?��?�� insert 
insert into tbl_product(product_num  , product_name, product_image,category,detail_category, price,discount_rate,gender,product_size,sale,product_cnt ) 
                values(1  ,'DB?��?��?��?��?��1','product_1.png','category1','detail_cat1','10000','10','?��','?��?��',0,100);
insert into tbl_product(product_num  , product_name, product_image,category,detail_category, price,discount_rate,gender,product_size,sale,product_cnt ) 
                values(2  ,'DB?��?��?��?��?��2','product_2.png','category2','detail_cat2','15000','5','?��','?��?��',0,50);


commit;

select *
from tbl_member_login;

select *
from tbl_login_record;

-- 로그?�� 기록?��?��블에 insert 
insert into tbl_login_record (login_num, userid,client_up) values (seq_login_history.nextval, ?, ? );







-- 로그?�� 기록?�� ?��?�� ?��???��

create sequence seq_login_history
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_LOGIN_HISTORY?��(�?) ?��?��?��?��?��?��?��.



select*
from tbl_login_record
where userid = 'less@gmail.com';



select *
from tbl_member;

select *
from tbl_member_login;


update tbl_member_login set update_passwd_date = '21/09/25' 
where userid ='josh@gmail.com';

commit;



update tbl_member set joindate = '21/01/01'
where userid = 'kangkc@gmail.com';

commit;

-- 로그?�� 기록 ?��?���? 초기?�� 


rollback;

commit;








select userid , passwd , secession , rest_member ,update_passwd_date, trunc( months_between(sysdate, update_passwd_date) ) AS update_passwd_date
from tbl_member_login;

---------------------------------------------------------------------
-- 구매?��?�� ?��?�� 
select *
from tbl_buylist;

select *
from tbl_address;


select * 
from tbl_member;
-- 구매?��?�� 기록?�� ?��?�� ?��???��

select *
from tbl_product;



--구매?��?�� ?��?��?�� 100�? ?���? ?��로시?? ?��?��
create or replace procedure pcd_bulist_insert_shippingend_2
is
begin
     for i in 1..100 loop
         insert into tbl_buylist(order_num, userid , product_num, buy_cnt, buy_date, shipping) 
         values(SEQ_BUYLIST.nextval,'eomjh@gmail.com',i,1,sysdate-i,0);
     end loop;
end pcd_bulist_insert_shippingend_2;

--?�� 컴파?�� ?�� ?��로시?? ?��?��?���?
exec pcd_bulist_insert_shippingend_2();
commit;
-- ?��로시??�? ?��?? ?��?��?�� ?�� ?��?��갔나 ?��?��
select *
from tbl_buylist;

select order_num, userid, product_num, buy_cnt , buy_date , shipping
from tbl_buylist
where userid = 'josh@gmail.com' and shipping = 0 
and buy_date between TO_DATE('2022/01/01', 'YYYY/MM/DD') AND TO_DATE('2022/10/30', 'YYYY/MM/DD')
order by buy_date ;









-- 구매목록 ?��???�� ?��?�� 
create sequence seq_buylist
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

------------------------------------------------------------------------


select *
from tbl_member;

select *
from tbl_buylist
where userid = 'karina@gmail.com' and shipping = 0
order by order_num desc;


update tbl_member_login set passwd ='9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382';

commit;







select order_num, userid, product_num, buy_cnt , buy_date , shipping
from tbl_buylist
where userid = 'josh@gmail.com' and shipping = 0 
and buy_date between TO_DATE('2022/07/28', 'YYYY/MM/DD') AND TO_DATE('2022/09/28', 'YYYY/MM/DD')
order by buy_date desc;

select count(*)
from 
(
select rownum as rno
from tbl_buylist B left join tbl_product P
on B.product_num = P.product_num
where userid = 'josh@gmail.com' and shipping between 0 and 1   
and buy_date between TO_DATE( '2022/7/30' , 'YYYY/MM/DD') AND TO_DATE( '2022/9/30' , 'YYYY/MM/DD') 
) V;
                       
                       
select *
from tbl_product;


select * 
from tbl_buylist;


insert into tbl_buylist(order_num, userid , product_num, buy_cnt, buy_date, shipping) 
values(SEQ_BUYLIST.nextval,'josh@gmail.com',177,3,sysdate-1,2);

commit;

select order_num, userid, product_num, buy_cnt , buy_date , shipping, product_name, product_image, rno
from 
(
select order_num, userid, B.product_num as product_num, buy_cnt , buy_date , shipping, product_name, product_image, rownum as rno
from tbl_buylist B left join tbl_product P
on B.product_num = P.product_num
where userid = 'josh@gmail.com' and shipping between 0 and 1
and buy_date between TO_DATE( '2022/08/01' , 'YYYY/MM/DD') AND TO_DATE( '2022/10/01' , 'YYYY/MM/DD') 
)v
where rno between 1 and 10;



select *
from tbl_product;


select *
from tbl_buylist;



update tbl_member set membership_regist_date = '22/07/26'
where userid = 'eomjh@gmail.com';

commit;


String sql = "select userid, joindate, membership, username, membership_regist_date, mobile, trunc( months_between(sysdate, membership_regist_date) ) AS membershipregistgap\n"+
"from tbl_member\n"+
"where userid = 'eomjh@gmail.com';";


select *
from tbl_member_login;

select *
from tbl_member;

select *
from tbl_login_record
order by login_date desc;


update tbl_member set membership = 1, membership_regist_date = sysdate-32
where userid='josh@gmail.com';

commit;




select userid, joindate, membership, username, membership_regist_date, mobile, trunc( months_between(sysdate, membership_regist_date),3 ) AS membershipregistgap
from tbl_member
where userid = 'josh@gmail.com';



select order_num, userid, product_num, buy_cnt , buy_date , shipping
from tbl_buylist
where buy_date between TO_DATE('2022/09/25', 'YYYY/MM/DD') AND TO_DATE('2022/09/30', 'YYYY/MM/DD')
order by buy_date desc;



-- 구매?��?�� ?��?���? insert

insert into tbl_buylist(order_num, userid , product_num, buy_cnt, buy_date, shipping) 
values(SEQ_BUYLIST.nextval,'josh@gmail.com',1,3,'22/06/25',0);


commit;


select order_num, userid, product_num, buy_cnt , buy_date , shipping, product_name, product_image, rno
from
( 
select order_num, userid, B.product_num as product_num, buy_cnt , buy_date , shipping, product_name, product_image, rownum as rno 
 from tbl_buylist B left join tbl_product P 
on B.product_num = P.product_num 
where userid = 'josh@gmail.com'  and shipping = 0  
 and buy_date between TO_DATE( '2022/01/01' , 'YYYY/MM/DD') AND TO_DATE( '2022/10/20' , 'YYYY/MM/DD') 
 )v 
where rno between 1 and  10
order by order_num desc;

select rownum,buy_date from tbl_buylist;























select order_num, userid, product_num, buy_cnt , buy_date , shipping, product_name, product_image, rno 
from
(
    select order_num, userid, B.product_num as product_num, buy_cnt , buy_date , shipping, product_name, product_image, rownum as rno 
    from tbl_buylist B left join tbl_product P 
    on B.product_num = P.product_num
    where userid = 'josh@gmail.com' and shipping  between 0 and 1
    and buy_date between TO_DATE( '2022/01/01' , 'YYYY/MM/DD') AND TO_DATE( '2022/10/10' , 'YYYY/MM/DD')
)v 
where rno between 1 and 10
order by buy_date asc  ;






select *
from tbl_buylist 
where userid = 'josh@gmail.com' and shipping = 2;



select *
from tbl_product;

--------------------------------------------------------------------------------------
-- data insert -----------------------------------------------------------------------
--------------------------------------------------------------------------------------
-- data insert -----------------------------------------------------------------------
--------------------------------------------------------------------------------------
-- data insert -----------------------------------------------------------------------
--------------------------------------------------------------------------------------
-- data insert -----------------------------------------------------------------------

--//////////////////////////// 침구�?-?���? ?��?�� ///////////////////////////////---
--?���?1
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '맨살?�� ?�� �??��?��?�� 모달 차렵?���?', '?���?1.PNG', '침구�?', '?���?', 46900, 0, '?��?��', '?????�� 바람?�� 맨살?�� ?��치는 ?��, �?볍고 ?��?��?�� 촉감. ?�� 말린 ?��미�?? ?��긋하�? ?��?��?�� 차의 ?���?, ?��로즈');

--?���?2
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '먼�??��?�� 차렵?���?', '?���?2.PNG', '침구�?', '?���?',  49900 , 0.2, '?��?��', '?�� 분리�? ?���? ?��?�� ?��?��체형 침구?���?, 베개커버?�� 베개?��?�� 별도�? 구매?��?�� ?��?�� 커버?��?��?��?��?��. ?��?��?��?��?�� ?��?�� ?��?��?�� 교환, 반품?�� ?���? ?��?��?�� ?��?��?�� , ?��배송�? 불량?���?�? �? ?��?��바랍?��?��. ??, ?�� 겸용 ?��?���? ?���?, ?�� 침�??��?��?��?��?�� ?��?�� ?��?��?��?�� ?��?���?, ?��?��?���? ?��빔선?�� ?��치하�? ?��?�� ?�� ?��?��?��?��. ?��불과 ?��?��?�� 측정방법?�� ?��?�� +-3cm 차이�? ?��?�� ?�� ?��?���?�? ?���? ?��?�� 무상 반품 교환?? 불�??��?��?��. ?��?��광으�? 촬영?��?�� 빛의 ?��, �?�?, ?��코에 ?��?�� 컬러�? ?��?��?�� 보일 ?�� ?���? 모니?�� ?��?��?�� ?��?�� ?��?��?�� ?���? ?�� ?��?��?�� ?��?��?�� ?��진을 참조바랍?��?��. ');

--?���?3
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?���? 60?�� 고�??��?���? 차렵?���?', '?���?3.PNG', '침구�?', '?���?',  116500 , 0.5, '?��?��', '?��빛을 �??�� 받아 ?��?��?��?��?���? ?��불의 촉감 ');

--?���?4
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?���? ?��?���?�??�� 기능?��M2 차렵?���?', '?���?4.PNG', '침구�?', '?���?',  36500 , 0, '?��?��', '미세먼�? ?��?��, ?��?��?�� ?��?�� ?�� ?��?�� 먼�?. 침실 먼�??�� M2?��불로 �??��?��?��?��. ');

--?���?5
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '비비?�� 고�??��?���? 차렵?���?', '?���?5.PNG', '침구�?', '?���?',  55900 , 0, '?��?��', '?��?�� 고요?���? ?��?��감이 ?��껴�??�� ?��그린?���? ?��?��?��?�� 미드?��추리 무드�? ?��출해보세?��. ');

--?���?6
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��근한 ?��링이�? ?��그�?? 먼�??��?�� 차렵?���?', '?���?6.PNG', '침구�?', '?���?',  49900 , 0, '?��?��', '?��?��?���? 발랄?�� ?��리곰?�� 만나보세?��. ?��리�?�한 ?��감의 곰들?�� ?��근하�? ?��?��?��?��?��. ');

--?���?7
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '머쉬멜로?�� ?��?���?�??�� M2?��?�� 차렵?���?', '?���?7.PNG', '침구�?', '?���?',  83200 , 0, '?��?��', ' 말랑 보송?�� 촉감?�� ?��?���?�??��까�? ?��번에! ');

--?���?8
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '[?��착이�?] 말랑�??�� ?��?���? 차렵?���?-?��몬드   ', '?���?8.PNG', '침구�?', '?���?',  66500 , 0, '?��?��', ' ?��?��?��?�� ?���? ?��?�� ?��만의 ?��착이�?, 말로 ?��?��?�� ?�� ?��?�� �??��?��??�? ?��?��?��?�� ?��?�� ?�� ?��?��?��?��. ');

--?���?9
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��근한 ?��링이�? ?��그�?? 먼�??��?�� 차렵?���?-?��?��?��?��   ', '?���?9.PNG', '침구�?', '?���?',  49900 , 0, '?��?��', ' 빈티�??�� ?��감의 꽃이 �??��?�� ?��?��?��?�� ?��?��. ?��?��?��?���? ?��?��?�� 침실?�� 만들?��보세?��. ');

--?���?10
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '무브 ?��?��?�� ?���? 극세?�� 차렵?���?-코코베이�?', '?���?10.PNG', '침구�?', '?���?',  66500 , 0, '?��?��', ' 몽�?몽�? 기분좋�? 촉감?�� �?�? 극세?�� ?���?, �??��?��?�� 베이�?컬러?�� 깅엄체크 ');

--?���?11
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?���? ?��?���?�??�� 기능?��M2 차렵?���?-?��?��?��', '?���?11.PNG', '침구�?', '?���?',  66500 , 0.45, '?��?��', ' 진드기�? ?��?��물질?�� 차단?��?�� 침구, ?????���? �?초한 ?��?��?�� ');

--?���?12
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��근한 ?��링이�? ?��그�?? 먼�??��?�� 차렵?���?-미스?���?', '?���?12.PNG', '침구�?', '?���?',  29900 , 0, '?��?��', '?��?��?���? ?��로잉?�� ?���? ?��?���? 모던?�� 그레?�� 체크?�� 조화. ?��?��?�� 민트 컬러?�� 미스?��기입?��?��. ');

--?���?13
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� 먼�??��?�� 차렵?���?-?��?��보리', '?���?13.PNG', '침구�?', '?���?',  49900 , 0, '?��?��', '보들보들 기분 좋�? 촉감?�� 침구, ?��?��?�� 빛을 ?��?? 컬러 ');

--?���?14
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?��?�� 고�??�� ?���? ?���? 차렵?���?-공원?��?��리스', '?���?14.PNG', '침구�?', '?���?',  59900 , 0, '?��?��', '?��?��?�� 즐거?�� ?��?��?�� 기�??���? ?��복을 꿈꾸�? ?�� 고�??��?���? ?���? 차렵?���? ');

--?���?15
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?���? 60?�� 고�??��?���? 차렵?���?-버터', '?���?15.PNG', '침구�?', '?���?',  116500 , 0.46, '?��?��', '?��빛을 �??�� 받아 ?��?��?��?��?���? ?��불의 촉감');

--?���?16
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '[?��착이�?] 말랑�??�� ?��?���? 차렵?���?-블루베리', '?���?16.PNG', '침구�?', '?���?',  66500 , 0, '?��?��', '?��?��?��?�� ?���? ?��?�� ?��만의 ?��착이�?, 말로 ?��?��?�� ?�� ?��?�� �??��?��??�? ?��?��?��?�� ?��?�� ?�� ?��?��?��?��. ');

--?���?17
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��근한 ?��링이�? ?��그�?? 먼�??��?�� 차렵?���?-체리봉봉', '?���?17.PNG', '침구�?', '?���?',  49900 , 0, '?��?��', '?��?�� ?��콤함?�� ?��?��, 체리봉봉 ?�� ?��?? 체리?�� ?��?��?�� ?��감이 기분 좋�? 발랄?��?�� ?��?��?��?��?��. ');

--?���?18
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?�� ?���? 극세?�� 차렵?���?-?????��?���?', '?���?18.PNG', '침구�?', '?���?',  49900 , 0, '?��?��', '?��리없?�� 고요?���? 캐시미어처럼 �??��?��?�� 극세?�� ?��?�� 침구 ');

--?���?19
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '�??�� M2 ?��?���?�??�� 극세?�� 차렵?���?-?��??베어', '?���?19.PNG', '침구�?', '?���?',  83200 , 0, '?��?��', '진드기�? ?��?��물질?�� 차단?��?�� 기능?�� ?��?���? 극세?��?�� 만남 ');

--?���?20
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '비비?�� 고�??��?���? 차렵?���?-?��?��?��?��?��?��', '?���?20.PNG', '침구�?', '?���?',  55900 , 0, '?��?��', '바다?? ?��?�� ?��?? 컬러, ?��?��?��?��?��?���? ?��?��?��?�� 미드?��추리 무드�? ?��출해보세?��. ');

--?���?21
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '코튼?��?��?�� 60?��광목 롱프�? 차렵?���?-?��추럴', '?���?21.PNG', '침구�?', '?���?',  69900 , 0, '?��?��', '보들보들 �??��?��?�� 목화�? ?��?��?�� ?��?�� 기분?�� ?��껴보?��?��. ');

--?���?22
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��근한 ?��링이�? ?��그�?? 먼�??��?�� 차렵?���?', '?���?22.PNG', '침구�?', '?���?',  27900 , 0, '?��?��', '?��?��?�� ?��?�� ?���? ?��?��, ?��?��베어 ');

--?���?23
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?���? 60?�� 고�??��?���? 차렵?���?-?��?��?��블루   ', '?���?23.PNG', '침구�?', '?���?',  62900 , 0.3, '?��?��', '?��빛을 �??�� 받아 ?��?��?��?��?���? ?��불의 촉감 ');

--?���?24
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '비비?�� 고�??��?���? 차렵?���?-?��보카?��', '?���?24.PNG', '침구�?', '?���?',  55900 , 0, '?��?��', '?��그러???�� ?��껴�??�� ?��?��?�� ?���?,?��보카?���? ?��?��?��?�� 미드?��추리 무드�? ?��출해보세?��. ');

--?���?25
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��???�� 먼�??��?�� 차렵?���?-코코그레', '?���?25.PNG', '침구�?', '?���?',  29900 , 0, '?��?��', '?��콤한 ?��감이 �??��?�� 공간 ?��???�� 캐주?��?���? ?��???�� 깅엄체크 ');

--?���?26
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?�� ?���? 극세?�� 차렵?���?-?��?��?��?��?��', '?���?26.PNG', '침구�?', '?���?',  83200 , 0.3, '?��?��', '?��리없?�� 고요?���? 캐시미어처럼 �??��?��?�� 극세?�� ?��?�� 침구 ');

--?���?27
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '[?��착이�?] 말랑�??�� ?��?���? 차렵?���?-?��차크�?', '?���?27.PNG', '침구�?', '?���?',  33900 , 0, '?��?��', '?��?��?��?�� ?���? ?��?�� ?��만의 ?��착이�?, 말로 ?��?��?�� ?�� ?��?�� �??��?��??�? ?��?��?��?�� ?��?�� ?�� ?��?��?��?��. ');

--?���?28
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '머쉬멜로?�� ?��?���?�??�� M2?��?�� 차렵?���?-?��?��차콜', '?���?28.PNG', '침구�?', '?���?',  44900 , 0, '?��?��', '말랑 보송?�� 촉감?�� ?��?���?�??��까�? ?��번에! ');

--?���?29
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '무브 ?��?��?�� ?���? 극세?�� 차렵?���?-미스?���?', '?���?29.PNG', '침구�?', '?���?',  35900 , 0.1, '?��?��', '몽�?몽�? 기분 좋�? 촉감?�� �?�? 극세?��, ?��?��?���? ?��?�� 그린 ?��?���? ?��???�� ');

--?���?30
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?�� 60?�� 고�??��?���? 차렵?���?-?��기시?��', '?���?30.PNG', '침구�?', '?���?',  99900 , 0.4, '?��?��', '?��?��?�� 뿌린 ?��?�� ?��?��?�� ?��?��?��, ?��?���? 보송보송?�� 고�??�� 60?�� ?���? ');
--//////////////////////////// 침구�?-?���? ?�� ///////////////////////////////---

commit;

select count(*)
from tbl_product;

delete from tbl_buylist;
where detail_category ='?���?';

commit;







--------------------------------


--//////////////////////////// 침구�?-침�? ?��?��///////////////////////////////--

--침�?1
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '모션 ?��?��?�� LED침�? ', '침�?1.PNG', '침구�?', '침�?',  618000, 0.2, '?��?��', '좋�? ?��?�� ?��?��?�� 명품 침�?  ');

--침�?2
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '리디?�� LED ?��목침??', '침�?2.PNG', '침구�?', '침�?',  773000, 0.15, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?3
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� LED ?��?��?�� 침�?', '침�?3.PNG', '침구�?', '침�?',  658000, 0.2, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?4
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� ?��?��?�� ?��?��침�?', '침�?4.PNG', '침구�?', '침�?',  599000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?5
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?�� 간접조명 ?��?��침�?', '침�?5.PNG', '침구�?', '침�?',  411250, 0.2, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?6
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��비언?�� LED ?��?��침�?', '침�?6.PNG', '침구�?', '침�?',  666250, 0.2, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?7
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '매들�? ?��?��침�?', '침�?7.PNG', '침구�?', '침�?',  298750, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?8
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '베드리�? ?��?��?�� 빅도?�� 침�? ', '침�?8.PNG', '침구�?', '침�?',  737750, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?9
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '???�� ?��?��침�?', '침�?9.PNG', '침구�?', '침�?',  311250, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?10
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '벤티 빅수?��침�?', '침�?10.PNG', '침구�?', '침�?',  179000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?11
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '채�? ???��?�� ?��?��침�? 빅서?�� 2?��', '침�?11.PNG', '침구�?', '침�?',  219000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?12
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '채�? ???��?�� ?��?��침�? 빅서?��?��?�� ', '침�?12.PNG', '침구�?', '침�?',  209000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?13
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� 간접조명 ?��?��침�? ', '침�?13.PNG', '침구�?', '침�?',  329000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?14
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?�� 간접조명 ?��?��침�?', '침�?14.PNG', '침구�?', '침�?',  459000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?15
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '모던 3?��/4?�� ?��?��침�?', '침�?15.PNG', '침구�?', '침�?',  189000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?16
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '츄즈 ?��?��침�?', '침�?16.PNG', '침구�?', '침�?',  219000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?17
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '코넬�? LED 3?��/4?�� ?��?��침�? ', '침�?17.PNG', '침구�?', '침�?',  249000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?18
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?�� 책장?�� ?��?��침�? ', '침�?18.PNG', '침구�?', '침�?',  259000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?19
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?�� 조명침�? ', '침�?19.PNG', '침구�?', '침�?',  289000, 0.2, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?20
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��로망 LED 침�? ', '침�?20.PNG', '침구�?', '침�?',  299000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?21
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� �?�? LED 4?�� ?��?��침�? ', '침�?21.PNG', '침구�?', '침�?',  319000, 0.15, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?22
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?? 벙커?��?�� 침�? ', '침�?22.PNG', '침구�?', '침�?',  149000, 0.2, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?23
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��비언?�� 간접조명 ?��?��침�? ', '침�?23.PNG', '침구�?', '침�?',  533000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?24
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� 침�? ', '침�?24.PNG', '침구�?', '침�?',  728000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?25
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?�� ?��?�� 침�? ', '침�?25.PNG', '침구�?', '침�?',  259000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?26
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '메이?�� ?��?��?��?�� ?��?��침�? ', '침�?26.PNG', '침구�?', '침�?',  233000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?27
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '리밍 LED ?��?��침�? ', '침�?27.PNG', '침구�?', '침�?',  289000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?28
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?���? 2?�� ?��?��침�? ', '침�?28.PNG', '침구�?', '침�?',  329000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?29
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� LED 4?�� ?��?��침�? ', '침�?29.PNG', '침구�?', '침�?',  135000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--침�?30
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?�� ?��마트 무선충전 침�? ', '침�?30.PNG', '침구�?', '침�?',  279000, 0, '?��?��', ' 좋�? ?��?�� ?��?��?�� 명품 침�? ');

--//////////////////////////// 침구�?-침�? ?��///////////////////////////////--

commit;


select count(*)
from tbl_product;




----------------------------------------------------------------------------




--//////////////////////////// 침구�?-침�?커버 ?��?�� ///////////////////////////////---

--침�?커버1
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '2022 ?��리�?�엄 ?��?�� 침�??��?��', '침�?커버1.PNG', '침구�?', '침�?커버',  41500 , 0, '?��?��', ' 고�??��?��면�??�� ?��?���?�??��까�?, ?��벽한 ?��?��?��?�� 69�? 침�??��?�� ');

--침�?커버2
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��브정?�� ?��그먼?�� ?��불겸?��?��', '침�?커버2.PNG', '침구�?', '침�?커버',  58200 , 0.4, '?��?��', '보송보송?�� 촉감?�� ?��?��?��까�?. ?���? 60?�� �?100%�? 쾌적?�� 촉감?�� ?��?��?��까�? 겸비?�� ?��브정?�� ?��그먼?�� ?��불겸?��?���? ?��개합?��?��.

 ');

--침�?커버3
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?��?��?�� 먼�??��?�� ?��불겸?��?��', '침�?커버3.PNG', '침구�?', '침�?커버',  34900 , 0, '?��?��', '맨살?�� ?��?��?�� ?�� ?�� �??��?���? 보송보송?�� ?��계절 ?��?�� ?��?��?��?�� ?��불겸?��?�� ');

--침�?커버4
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?���? 고정밴드 침�??��?��', '침�?커버4.PNG', '침구�?', '침�?커버',  49900 , 0, '?��?��', '�??���??��?�� 카스?��?�� ?��?��?�� ?��?�� ?��?�� ?��?��, ?��?���? ?��미워?�� 침�??��?�� ');

--침�?커버5
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� 먼�??��?�� 침�??��?��', '침�?커버5.PNG', '침구�?', '침�?커버',  24900 , 0, '?��?��', '먼�?걱정 ?��?�� ?��?��?��, ?��리�?�엄 ?��미화?���? 침�??��?�� ');

--침�?커버6
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?���? M2 ?��?���?�??�� 침�??��?��', '침�?커버6.PNG', '침구�?', '침�?커버',  29900 , 0, '?��?��', '미세먼�? ?��?��, ?��?��?�� ?��?�� ?�� ?��?�� 먼�?. 침실 먼�??�� M2?��?���? �??��?��?��?��. ');

--침�?커버7
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?���? 60?�� 고�??��?���? 고정밴드 침�??��?��', '침�?커버7.PNG', '침구�?', '침�?커버',  66500 , 0.4, '?��?��', '깔끔?���? ?��?��?�� ?��면패?�� , ?���? 60?�� 고�??��?���? 침�??��?��');

--침�?커버8
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?���? 먼�??��?�� ?��불겸?��?��', '침�?커버8.PNG', '침구�?', '침�?커버',  34900 , 0, '?��?��', '맨살?�� ?��?��?�� ?�� ?�� �??��?���? 보송보송?�� ?��계절 ?��?�� ?��?��?��?�� 차렵?���? ');

--침�?커버9
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '머쉬멜로?�� ?��?���?�??�� M2?��?�� 침�??��?��', '침�?커버9.PNG', '침구�?', '침�?커버',  58200 , 0.2, '?��?��', '말랑보송?�� 촉감?�� ?��?���?�??��까�?! ?��?�� ?���? 침�??��?�� ');

--침�?커버10
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��브리?���? ?���? 침�??��?��', '침�?커버10.PNG', '침구�?', '침�?커버',  39900 , 0, '?��?��', '99.9% ?���? 기능?���? 매일 ?��?��?�� ?�� ?��쾌한 침�??��?�� ');

--침�?커버11
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��???�� 먼�??��?�� 침�??��?��', '침�?커버11.PNG', '침구�?', '침�?커버',  24900 , 0, '?��?��', '먼�?걱정 ?��?�� ?��?��?��, ?��리�?�엄 ?��미화?���? 침�??��?�� 체크버전 ');

--침�?커버12
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��리셔?�� 고�??�� ?���? ?���? 고정밴드 침�??��?��', '침�?커버12.PNG', '침구�?', '침�?커버',  49900 , 0, '?��?��', '?��?��?�� 즐거?�� ?��?��?�� 기�??���? ?��복을 꿈꾸게할 60?�� ?���? ?���? 침�??��?�� ');

--침�?커버13
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��그�?? 먼�??��?�� 침�??��?��', '침�?커버13.PNG', '침구�?', '침�?커버',  41500 , 0, '?��?��', '마음?�� ?��링시켜줄 ?��?��?? ?��매의 ?��?��?��?�� �??��?�� 침�??��?�� ');

--침�?커버14
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��100?�� ?��리�?�엄 코마?�� 고정밴드 침�??��?��', '침�?커버14.PNG', '침구�?', '침�?커버',  83200 , 0, '?��?��', '최상�? ?��?��?��?�� ?��?�� ?�� ?��?�� ?��?��처럼 매끄?���? 고운 ?��?��기처?�� ?��?��?�� 침�??��?�� ');

--침�?커버15
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?���??��?��?��?�� ?���? 침�??��?��', '침�?커버15.PNG', '침구�?', '침�?커버',  66500 , 0.4, '?��?��', '?��?���? 깨끗?�� ?��기농 ?��?�� ?��?��, ?���??�� ?���? 침�??��?�� ');

--침�?커버16
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '커밍?�� 60?�� ?��그먼?�� 침�??��?��', '침�?커버16.PNG', '침구�?', '침�?커버',  42800 , 0, '?��?��', ' 보드?���? 보송?�� ?��면으�?, ?���? 종일 ?�� ?��?�� ?���? ?��?�� 만큼 ?��?��?�� 침�??��?�� ');

--침�?커버17
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� ?��?��모달 침�??��?��', '침�?커버17.PNG', '침구�?', '침�?커버',  39900 , 0, '?��?��', '?????�� 바람?�� 맨살?�� ?��치는 ?��, �?볍고 ?��?��?�� 촉감 ');

--침�?커버18
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?��?�� ?��?���?�??�� ?���? 고정밴드 침�??��?��', '침�?커버18.PNG', '침구�?', '침�?커버',  45900 , 0, '?��?��', '?��?�� ?��분으로�??�� ?��?��?��?�� ?��?��?���? ?��면할 ?�� ?��?���?, �?증된 ?��?���?�??�� 침�??��?�� ');

--침�?커버19
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?�� 80?�� ?��면누�? 침�??��?��', '침�?커버19.PNG', '침구�?', '침�?커버',  167000 , 0.3, '?��?��', '고급?��?��?�� 80?�� 고�??��면의 �??��?��?�� ?��치감�? ?��?��?��?��?�� ?��?��?�� ?��?�� ');

--침�?커버20
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '???�� 맨션 모달 침�??��?��', '침�?커버20.PNG', '침구�?', '침�?커버',  138000 , 0.2, '?��?��', 'LENZING MODAL(친환�? MOC) 친환�? 공정?�� ?��?��바이?�� 기술�? ?��?��?�� �??��?���? ?��?��?�� 모달?? ?��?��?��?��?�� �??��?��?�� 감촉�? 고급?��?��?�� 광택감이 ?��?��?��?��.???��?��?�� ?��?��?���? ?��?��?��?�� ?��기성?�� 좋고, ?��?��?�� 좋아 ?��?�� ?��?��?�� 복원?��?�� 좋습?��?��. ');

--침�?커버21
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '리플 ?��름패?��', '침�?커버21.PNG', '침구�?', '침�?커버',  66000 , 0, '?��?��', '?��?��?�� �? 리플?��?���? ?��?��메이?���? ?��?��?��?�� ?��구성?�� 좋으�? 맞춤?��?��?�� �??��?�� ?��?��?��?��?��. 리플 �?�? ?��?��?�� �??���??��?���? ?��?��?�� ?��?��?���? ?? ?��?��?��?�� ?��?��?��?��. ');

--침�?커버22
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '리프?��?�� 면리?�� 침�??��?��', '침�?커버22.PNG', '침구�?', '침�?커버',  69000 , 0, '?��?��', '?��?��?�� �? 리플?��?���? ?��?��메이?���? ?��?��?��?�� ?��구성?�� 좋으�? 맞춤?��?��?�� �??��?�� ?��?��?��?��?��. 리플 �?�? ?��?��?�� �??���??��?���? ?��?��?�� ?��?��?���? ?? ?��?��?��?�� ?��?��?��?��. ');

--침�?커버23
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '마이코튼 ?��그먼?��?��?�� 침�??��?��', '침�?커버23.PNG', '침구�?', '침�?커버',  54900 , 0, '?��?��', '차분?�� 컬러?? �??��?��?�� ?��치감?�� 마이코튼 ?��?�� ');

--침�?커버24
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '메이?�� ?��기인�? 침�??��?��', '침�?커버24.PNG', '침구�?', '침�?커버',  132000 , 0.3, '?��?��', '?��?��?���? �??��?��?�� 메이?�� ?��기인�? ?��름침�? ');

--침�?커버25
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?�� ?��?��?��?�� 침�??��?��', '침�?커버25.PNG', '침구�?', '침�?커버',  49000 , 0, '?��?��', '촘촘?�� ?��빔이 ?��?���? ?��그먼?�� ?��?���? 침�??��?�� ');

--침�?커버26
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��?��?��?��?��?��?�� ?��면누�? 침�??��?��', '침�?커버26.PNG', '침구�?', '침�?커버',  125000 , 0, '?��?��', '모던?�� ?��?��?��?��?��?�� ?��면누�? 침�??��?�� ');

--침�?커버27
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��리에 ?���? ?��면누�? 침�??��?��', '침�?커버27.PNG', '침구�?', '침�?커버',  169000 , 0, '?��?��', '쾌적?���? ?��?��?���? ?��?��?�� ?��?��리의 ?���? ?���? 침�??��?�� ');

--침�?커버28
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��리�?�엄 ?��그먼?�� ?��면누�? 침�??��?��', '침�?커버28.PNG', '침구�?', '침�?커버',  139000 , 0, '?��?��', '?��?��?��?��?���? ?��츄럴?�� 컬러감에 �??��?��?�� ?��치감 ');

--침�?커버29
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '?��머모?��-?��?��메이?�� 리플 ?��름패?��', '침�?커버29.PNG', '침구�?', '침�?커버',  94000 , 0, '?��?��', '?��?��?�� �? 리플?��?���? ?��?��메이?���? ?��?��?��?�� ?��구성?�� 좋으�? 맞춤?��?��?�� �??��?�� ?��?��?��?��?��. 리플 �?�? ?��?��?�� �??���??��?���? ?��?��?�� ?��?��?���? ?? ?��?��?��?�� ?��?��?��?��. ');

--침�?커버30
insert into tbl_product(PRODUCT_NUM, REGISTER_DATE, PRODUCT_NAME, PRODUCT_IMAGE, CATEGORY, DETAIL_CATEGORY,PRICE,DISCOUNT_RATE,GENDER,PRODUCT_CONTENT) 
values(seq_product_num.nextval, sysdate, '???��리스 3중거�? 침�??��?��', '침�?커버30.PNG', '침구�?', '침�?커버',  92000 , 0, '?��?��', '�??��?���? 보송보송?�� 거즈�? 3중으�? ?��?��?��?�� 만든 ?��?��?��?��?��. ');

--//////////////////////////// 침구�?-침�?커버 ?�� ///////////////////////////////--

commit;

select *
from tbl_product;


select *
from tbl_buylist;


select *
from tbl_address;



-- ?? 카테고리 구하�?
select DISTINCT category 
from tbl_product;
order by category;



-- ?�� 카테고리 구하�? 
select DISTINCT detail_category
from tbl_product
where category ='침구�?';

delete from tbl_product
where product_num between 979 and 983;


delete from tbl_product_stock
where product_num between 965 and 978;

commit;

select *
from tbl_product
order by product_num desc;

insert into tbl_product(product_num , register_date, product_name, product_image, category, detail_category, price, discount_rate, gender, product_content, bestyn) 
values(SEQ_PRODUCT_NUM.nextval,sysdate,'?��?���?' , '?��?��?��미�?', '??카테고리', '?��카테고리',�?�?,?��?��?�� ,'?���?','?��?��?��?��?���?','N');



insert into tbl_product(product_num , register_date, product_name, product_image, category, detail_category, price, discount_rate, gender, product_content, bestyn) 
values(SEQ_PRODUCT_NUM.nextval,sysdate,'진영?��?��?��?�� ?��?��' , '진영.jpg', '?��면용?��', '?��로우미스?��', 100 , 0.5 ,'?��?��','?��?��?���? insert ?��?��?��?��?��?��.','N');

commit;



-- ?��?�� ?��?���? 조회
select *
from tbl_product
order by product_num desc;


-- ?��?�� ?���? ?��?���? 조회
select *
from tbl_product_stock;

SELECT *
FROM TBL_BUYLIST;   

select *
from tbl_member;



select *
from tbl_address;

commit;

-- delete from tbl_buylist;


insert into tbl_buylist(order_num, userid , product_num, buy_cnt, buy_date, shipping, fk_address_num) 
values(SEQ_BUYLIST.nextval,'josh@gmail.com',2,3,sysdate-8,1, 351);

commit;


----------------------------------------------------------------





select product_num, product_name, product_image, price, discount_rate
from tbl_product;



select address_num , address, detail_address , order_name , mobile , A.userid
      ,order_num, product_num, buy_cnt , buy_date, shipping, fk_address_num
      , product_image, price, discount_rate
from 
(
    SELECT address_num , address, detail_address , order_name , mobile , A.userid
          ,order_num, product_num, buy_cnt , buy_date, shipping, fk_address_num
    FROM tbl_address A join tbl_buylist B
    ON A.userid = b.userid
) V join tbl_product P
on V.userid = P.userid;


select *
from tbl_buylist;

select *
from tbl_product;

select *
from tbl_address
where userid = 'josh@gmail.com';


select order_num, userid, product_num, but_cnt, buy_date, shipping, fk_address_num  
from tbl_buylist;

select *
from tbl_address;



SELECT order_num, address_num , address, detail_address , order_name , mobile , A.userid
       , B.product_num, buy_cnt , buy_date, shipping, fk_address_num
       , product_image, product_name , price, discount_rate, post_code
FROM tbl_address A 
join tbl_buylist B
ON A.address_num = b.fk_address_num
JOIN tbl_product P
ON B.product_num = P.product_num
where order_num = 603;

select *
from tbl_address;




select shipping, count(shipping) as cnt
from tbl_buylist
group by shipping
order by shipping ;




SELECT B.order_num , B.userid, B.product_num, B.buy_cnt, B.buy_date, B.shipping,
       P.product_num , P.product_name, P.product_image, P.price, P.discount_rate
FROM tbl_buylist B LEFT JOIN tbl_product P
ON B.product_num = P.product_num
where shipping = 1;

select *
from tbl_product;

select *
from tbl_buylist
order by order_num;


select *
from tbl_login_record
order by login_date desc;





select count(*)
from tbl_buylist
where shipping = 1;



select *
from tbl_product
where product_name = 'asdf';

delete from tbl_product where product_name='asdf';

commit;


select *
from tbl_member_login
where userid = 'drlegojy@naver.com';


update tbl_admin_login set admin_passwd = '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382';


select *
from tbl_admin_login;

insert into tbl_admin_login(admin_id , admin_passwd) values('admin2','9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382');


commit;










select order_num, userid, product_num, buy_cnt , buy_date , shipping, product_name, product_image, rno 
 from 
  ( 
select order_num, userid, B.product_num as product_num, buy_cnt , buy_date , shipping, product_name, product_image,
	  row_number() over(order by order_num desc) as rno 
 from tbl_buylist B left join tbl_product P 
 on B.product_num = P.product_num 
where userid = 'karina@gmail.com'  
and shipping between 0 and 1
 and buy_date between TO_DATE( '2022/08/09' , 'YYYY/MM/DD') AND TO_DATE( '2022/10/09 23:59' , 'YYYY/MM/DD hh24:mi:ss')
)v 
 where rno between 1 and 20;
 
 
 
 
 select userid
 from tbl_buylist
 where order_num = 602;
 
 
 select *
 from tbl_cart;
 
 
delete from tbl_buylist
where fk_address_num is null;
 

commit; 
 
 
 select *
 from tbl_member_login;
 
 
select *
from tbl_login_record;
where userid = 'eomjh@gmail.com';