DROP TABLE IF EXISTS organization;
CREATE TABLE IF NOT EXISTS organization (id SERIAL PRIMARY KEY, name VARCHAR(255), description VARCHAR(255), status BOOLEAN);
insert into organization(name, description, status) values('organization#1name', 'descr#1', 'true');
insert into organization(name, description, status) values('organization#2name', 'descr#2', 'true');
insert into organization(name, description, status) values('organization#3name', 'descr#3', 'true');