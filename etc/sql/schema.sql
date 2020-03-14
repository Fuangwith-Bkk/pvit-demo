DROP TABLE IF EXISTS FREELANCER_SKILLS;
DROP TABLE IF EXISTS FREELANCER;
CREATE TABLE FREELANCER (
  id integer primary key,
  firstname varchar(50),
  lastname varchar(50),
  email varchar(50)
);
CREATE TABLE FREELANCER_SKILLS (
  id integer not null,
  skill varchar(50),
  detail varchar(100),
  freelancer_id integer not null,
  FOREIGN KEY (freelancer_id) REFERENCES freelancer(id)
);

