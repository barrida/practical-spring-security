INSERT INTO user (id, username, password, algorithm, role)
VALUES ('1', '0oa4kna87nlw366tT5d7', 'CnzWDHNSrWj93s-_n4TCBAFvkpmefWpo62SEeCBf', 'BCRYPT', 'user' );

INSERT INTO authority (id, name, user) VALUES ('1', 'READ', '1');
INSERT INTO authority (id, name, user) VALUES ('2', 'WRITE', '1');

INSERT INTO product (id, name, price, currency, owner)
VALUES ('1', 'Chicken', '5', 'GBP' ,'suleymanube@gmail.com'),
 ('2', 'Beef', '15', 'GBP','canan'),
 ('3', 'Apple', '3', 'GBP','fatma'),
 ('4', 'Rice', '4', 'GBP','suleymanube@gmail.com'),
 ('5', 'Fish', '8', 'GBP','suleymanube@gmail.com');
