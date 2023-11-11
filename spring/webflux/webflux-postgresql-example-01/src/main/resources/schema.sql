DROP TABLE IF EXISTS organization;
CREATE TABLE IF NOT EXISTS organization (id SERIAL PRIMARY KEY, name VARCHAR(255), description VARCHAR(255), status BOOLEAN);
insert into organization(id, name, description, status) values(1, 'organization#1name', 'descr#1', 'true');
insert into organization(id, name, description, status) values(2, 'organization#2name', 'descr#2', 'true');