INSERT INTO chat.user (login, password)
VALUES ('sun', 'red');
INSERT INTO chat.user (login, password)
VALUES ('quark', 'enigmatic');
INSERT INTO chat.user (login, password)
VALUES ('psi', 'wave');
INSERT INTO chat.user (login, password)
VALUES ('frip', 'side');
INSERT INTO chat.user (login, password)
VALUES ('fools', 'gold');

INSERT INTO chat.chatroom (name, owner)
VALUES ('school', 1);
INSERT INTO chat.chatroom (name, owner)
VALUES ('asylum', 2);
INSERT INTO chat.chatroom (name, owner)
VALUES ('escape', 3);
INSERT INTO chat.chatroom (name, owner)
VALUES ('ever gaol', 4);
INSERT INTO chat.chatroom (name, owner)
VALUES ('refugee', 5);

INSERT INTO chat.message (author, room, text, timestamp)
VALUES (
            1, 1, 'Hello, world!', to_timestamp('02-08-2022 23:42:01', 'DD-MM-YYYY HH24:MI:SS')
        );
INSERT INTO chat.message (author, room, text, timestamp)
VALUES (
           2, 2, 'shall accept!', to_timestamp('02-08-2022 23:42:01', 'DD-MM-YYYY HH24:MI:SS')
       );
INSERT INTO chat.message (author, room, text, timestamp)
VALUES (
           3, 3, ' considerably expedite!', to_timestamp('02-08-2022 23:42:01', 'DD-MM-YYYY HH24:MI:SS')
       );
INSERT INTO chat.message (author, room, text, timestamp)
VALUES (
           4, 4, 'the use of storage!', to_timestamp('02-08-2022 23:42:01', 'DD-MM-YYYY HH24:MI:SS')
       );
INSERT INTO chat.message (author, room, text, timestamp)
VALUES (
           5, 5, 'domain knowledge models!', to_timestamp('02-08-2022 23:42:01', 'DD-MM-YYYY HH24:MI:SS')
       );
