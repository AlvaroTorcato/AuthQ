
insert into users (id, username,email, password) values (1,'Álvaro','alvaro@gmail.com', 'gostei123');
insert into users (id, username,email, password) values (2,'Tiago','tiago@gmail.com', 'gostei123');
insert into users (id, username,email, password) values (3,'Mariana','mariana@gmail.com', 'gostei123');

insert into roles(id,authority) values (1,'COSTUMER')
insert into roles(id,authority) values (2,'MODERATOR')

insert into user_roles(user_id,role_id) values(1,1)
insert into user_roles(user_id,role_id) values(2,2)
insert into user_roles(user_id,role_id) values(3,2)
