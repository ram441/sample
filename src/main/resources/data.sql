create table roles(role_id bigint(11) NOT NULL AUTO_INCREMENT, role_name varchar(45),description varchar(45),created_by varchar(45),created_date datetime, is_delete boolean, primary key(role_id));

create table roles(role_id bigint(11) NOT NULL AUTO_INCREMENT, role_name varchar(45), primary key(role_id));

INSERT INTO roles VALUES (1, 'ADMIN' , 'testRole Description', 'admin', CURDATE(), 0);
INSERT INTO roles VALUES (2, 'USER' , 'testRole Description', 'user', CURDATE(), 0);
INSERT INTO roles VALUES (3, 'GUEST' , 'testRole Description', 'user', CURDATE(), 0);

create table user(user_id bigint(50) NOT NULL AUTO_INCREMENT, user_name varchar(20), first_name varchar(45),last_name varchar(45),email varchar(45),phone varchar(45),created_by varchar(45),created_date datetime,role_id bigint(11), password varchar(45),is_delete boolean, primary key(user_id),foreign key (role_id) references roles(role_id));

INSERT INTO user VALUES (1,'user1', 'FirstName' , 'LastName', 'email','phone','user', CURDATE(),1,'password', 0);

create table project(proj_id bigint(11) NOT NULL AUTO_INCREMENT, proj_name varchar(45),proj_type varchar(45),maturity varchar(45),loan_purpose varchar(45),min_rate bigint(50),max_rate bigint(50),auction_begining_date datetime, amount bigint(100),rating_agency_score varchar(50),is_willing_to_participate boolean, add_favorate boolean, primary key(proj_id));


INSERT INTO project VALUES (1, 'Investment Test Project' , 'Loan', '20 years','school',1.0,3.5, CURDATE(),37000, 'Test' , 0, 0);


create table user_project_xref(user_project_id bigint(50) NOT NULL AUTO_INCREMENT, user_id bigint(50), project_id bigint(50), primary key(user_project_id),foreign key (user_id) references user(user_id), foreign key(project_id) references project(proj_id));
