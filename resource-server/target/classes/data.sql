INSERT INTO user (id, username, password, algorithm, role)
VALUES ('1', 'suleyman', '$2a$12$fmJyjMzVvrGFbt4.wWhqvuq5iE.Z.xXRL3y1YRidxk89NpsOXHUN.', 'BCRYPT', 'user' );

INSERT INTO authority (id, name, user) VALUES ('1', 'READ', '1');
INSERT INTO authority (id, name, user) VALUES ('2', 'WRITE', '1');

INSERT INTO product (id, name, price, currency, owner)
VALUES ('1', 'Chicken', '5', 'GBP' ,'suleyman'),
 ('2', 'Beef', '15', 'GBP','canan'),
 ('3', 'Apple', '3', 'GBP','fatma'),
 ('4', 'Rice', '4', 'GBP','suleyman'),
 ('5', 'Fish', '8', 'GBP','suleyman');
