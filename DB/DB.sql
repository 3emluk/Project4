
	drop trigger MY_USER_ID_GENERATOR;
	drop trigger CAR_ID_GENERATOR;
	drop trigger CAR_ORDER_ID_GENERATOR;
	drop trigger CAR_ORDER_DATA_CHANGE;


	DROP TABLE 	car_order;
	DROP TABLE 	my_user;
	DROP TABLE 	my_user_role;
	DROP TABLE 	car;

	drop sequence MY_USER_UNIQUE_ID;
	drop sequence CAR_UNIQUE_ID;
	drop sequence CAR_ORDER_UNIQUE_ID;




	--users
	CREATE TABLE my_user (
		id				 				NUMBER(8) 	NOT NULL,
		id_role 					NUMBER(8) NOT NULL,
		email 							VARCHAR2(120) NOT NULL,
		password 						VARCHAR2(40) NOT NULL,
		first_name 						VARCHAR2(120) NOT NULL,
		last_name 						VARCHAR2(120) NOT NULL,
		passport_Id						VARCHAR2(120) NOT NULL,
		location						VARCHAR2(120) NOT NULL,
		phone							VARCHAR2(20) NOT NULL
	);
	ALTER TABLE my_user ADD CONSTRAINT my_user_pk PRIMARY KEY(id);
	ALTER TABLE my_user ADD CONSTRAINT my_user_login UNIQUE(email,password);

	CREATE TABLE my_user_role (
		id				 				NUMBER(8) 	NOT NULL,
		role							VARCHAR2(20) NOT NULL
	);
	ALTER TABLE my_user_role ADD CONSTRAINT my_user_role_pk PRIMARY KEY(id);

	CREATE TABLE car (
		id				 				NUMBER(8)		NOT NULL,
		model							VARCHAR2(120)		NOT NULL,
		manufacturer					VARCHAR2(120)		NOT NULL,
		sign							VARCHAR2(120),
		price				 			NUMBER(8)		NOT NULL,
		is_avaliable					NUMBER(2)		NOT NULL

	);
	ALTER TABLE car ADD CONSTRAINT car_pk PRIMARY KEY(id);

	CREATE TABLE car_order (
		id				 				NUMBER(8)		NOT NULL,
		car_order_number				VARCHAR2(120)	NOT NULL,
		id_car							NUMBER(8)		NOT NULL,
		start_date						DATE            NOT NULL,
		duration 						NUMBER(3)		NOT NULL,
		end_date 						DATE,
		id_user							NUMBER(8)		NOT NULL,
		rent_price						NUMBER(8)		NOT NULL,
		charges							NUMBER(8)		NOT NULL,
		full_price						NUMBER(8)		NOT NULL,
		is_confirmed					NUMBER(1)		NOT NULL,
		coment							VARCHAR2(200)
	);
	ALTER TABLE car_order ADD CONSTRAINT car_order_pk PRIMARY KEY(id);


	-- constraints
	ALTER TABLE my_user ADD FOREIGN KEY (id_role) REFERENCES my_user_role (id);

	ALTER TABLE car_order ADD FOREIGN KEY (id_car) REFERENCES car (id);
	ALTER TABLE car_order ADD FOREIGN KEY (id_user) REFERENCES my_user (id);



	-- SEQUENCES

	CREATE SEQUENCE  MY_USER_UNIQUE_ID
	START WITH 1
	INCREMENT BY 1
	NOCACHE
	NOCYCLE ;
	/
	CREATE SEQUENCE  CAR_UNIQUE_ID
	START WITH 1
	INCREMENT BY 1
	NOCACHE
	NOCYCLE ;
	/
	CREATE SEQUENCE  CAR_ORDER_UNIQUE_ID
	START WITH 1
	INCREMENT BY 1
	NOCACHE
	NOCYCLE ;
	/


	create or replace TRIGGER MY_USER_ID_GENERATOR
	  before insert on my_user
	  for each row
	begin
	    select MY_USER_UNIQUE_ID.nextval into :NEW.ID from dual;
	end;
	/
	create or replace TRIGGER CAR_ORDER_ID_GENERATOR
	  before insert on car_order
	  for each row
	begin
	    select CAR_ORDER_UNIQUE_ID.nextval into :NEW.ID from dual;
	    select 'CO#'||:NEW.ID into :NEW.CAR_ORDER_NUMBER from dual;
	    select price * :NEW.duration into :NEW.rent_price from CAR where id =:NEW.id_car;
  		select :NEW.rent_price + :NEW.charges into :NEW.full_price from dual;
	end;
	/
	create or replace TRIGGER CAR_ID_GENERATOR
	  before insert on car
	  for each row
	begin
	    select  CAR_UNIQUE_ID.nextval into :NEW.ID from dual;
	end;
	/
	create or replace TRIGGER CAR_ORDER_DATA_CHANGE
	  before update on car_order
	  for each row
	begin
		if   :NEW.is_confirmed = 1 then
			update car set is_avaliable = 0 where id=:NEW.id_car;
		end if;
  		select price * :NEW.duration into :NEW.rent_price from CAR where id =:NEW.id_car;
  		select :NEW.rent_price + :NEW.charges into :NEW.full_price from dual;
	end;
	/


	INSERT INTO my_user_role (id, role)
	VALUES (1, 'Administrator');

	INSERT INTO my_user_role (id, role)
	VALUES (2, 'Customer');
-- pass: admin
	INSERT INTO my_user (ID_ROLE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, PASSPORT_ID, LOCATION, PHONE) VALUES ('1', 'admin', '6ee3d219b7bdaff83a5fc76d2880bfee21aae6e8', 'admin', 'admin', 'admin', 'admin', 'admin');

	COMMIT;