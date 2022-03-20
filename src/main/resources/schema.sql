CREATE TABLE pet (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(30),
     type VARCHAR(30),
     gender VARCHAR(30),
     age INTEGER
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL
);

CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE user_role (
   user_id INT NOT NULL,
   role_id INT NOT NULL,

   PRIMARY KEY (user_id, role_id),
   FOREIGN KEY(user_id) REFERENCES users(id),
   FOREIGN KEY(role_id) REFERENCES role(id)
);

CREATE TABLE persistent_logins (
   username varchar(100) not null,
   series varchar(64) primary key,
   token varchar(64) not null,
   last_used timestamp not null
);
