INSERT INTO pet
(name, type, gender, age)
VALUES
('Scoobie', 'Dog' , 'Female', 1 ),
('Grisou', 'Cat' , 'Female', 8 );

/* all these passwords are "sesame" */
INSERT INTO users
(user_name, password)
VALUES
('Jordan','$2a$12$f16KDstQgIvySVmvXMJJse6A3gyrx7xR8J/7KMOhXWC6iCvsBf2ba'),
('Annie','$2a$12$f16KDstQgIvySVmvXMJJse6A3gyrx7xR8J/7KMOhXWC6iCvsBf2ba'),
('Ethan','$2a$12$f16KDstQgIvySVmvXMJJse6A3gyrx7xR8J/7KMOhXWC6iCvsBf2ba'),
('Bob','$2a$12$f16KDstQgIvySVmvXMJJse6A3gyrx7xR8J/7KMOhXWC6iCvsBf2ba'),
('Jen','$2a$12$f16KDstQgIvySVmvXMJJse6A3gyrx7xR8J/7KMOhXWC6iCvsBf2ba'),
('Dave','$2a$12$f16KDstQgIvySVmvXMJJse6A3gyrx7xR8J/7KMOhXWC6iCvsBf2ba');

INSERT INTO role
(role_name)
VALUES
('ROLE_CAT'),
('ROLE_DOG');

INSERT INTO user_role
(user_id, role_id)
VALUES
(1,1),
(2,1),
(3,1),
(4,2),
(5,2),
(6,2);
