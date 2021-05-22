ALTER TABLE BANNED_TOKEN
ALTER COLUMN token
varchar(1024);


--insert into todo values (1,'compras');

--insert into item values (1, 'tomate', 'OPEN', 1);
--insert into item values (2, 'aguacate', 'OPEN', 1);

insert into user (id, username, password, user_role) values (1, 'super_user', '$2a$10$KOrl1YKvNju2PBTeT9YKGu3VyRLNHGUgFWhuMsQBIQPTRP9bYd1sC', 'OWNER');
insert into user (id, username, password, user_role) values (2, 'reader_user', '$2a$10$M7TwVoiVkoc6t6yNnFoMmOomGVulV0JYZ7qS9TUWaG0HGFM0DdFym', 'VIEWER');