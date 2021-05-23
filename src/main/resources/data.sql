insert into user (id, username, password, user_role) values (1, 'super_user', '$2a$10$KOrl1YKvNju2PBTeT9YKGu3VyRLNHGUgFWhuMsQBIQPTRP9bYd1sC', 'OWNER');
insert into user (id, username, password, user_role) values (2, 'reader_user', '$2a$10$M7TwVoiVkoc6t6yNnFoMmOomGVulV0JYZ7qS9TUWaG0HGFM0DdFym', 'VIEWER');

insert into family (id, genre) values (1, 'comedy');
insert into family (id, genre) values (2, 'thriller');

insert into book (id, title, author, family_id) values (1, 'Cien anos de soledad', 'Gabriel Garcia Marquez', 2);
insert into book (id, title, author, family_id) values (2, 'La fiesta del chivo', 'Mario Vargas Llosa', 2);