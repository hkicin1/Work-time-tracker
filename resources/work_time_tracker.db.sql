BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "employee" (
	"id"	INTEGER,
	"person_id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("person_id") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "work_hours" (
	"id"	INTEGER,
	"employee_id"	INTEGER,
	"date"	DATE,
	"work_hours"	REAL,
	FOREIGN KEY("employee_id") REFERENCES "employee"("id")
);
CREATE TABLE IF NOT EXISTS "project_work_hours" (
	"id"	INTEGER,
	"employee_id"	INTEGER,
	"project_id"	INTEGER,
	"date"	DATE,
	"work_hours"	REAL,
	FOREIGN KEY("employee_id") REFERENCES "employee"("id"),
	FOREIGN KEY("project_id") REFERENCES "project"("id")
);
CREATE TABLE IF NOT EXISTS "admin" (
	"id"	INTEGER,
	"person_id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("person_id") REFERENCES "person"("id")
);
CREATE TABLE IF NOT EXISTS "employee_has_position" (
	"employee_id"	INTEGER,
	"position_id"	INTEGER,
	FOREIGN KEY("position_id") REFERENCES "position"("id"),
	FOREIGN KEY("employee_id") REFERENCES "employee"("id")
);
CREATE TABLE IF NOT EXISTS "person" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL,
	"surname"	TEXT NOT NULL,
	"address"	TEXT,
	"postal_number"	INTEGER,
	"city"	TEXT,
	"username"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "project" (
	"id"	INTEGER,
	"name"	TEXT,
	"start_date"	DATE,
	"finish_date"	DATE,
	"activity"	INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "position" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL,
	"beginning_date"	DATE,
	"end_date"	DATE,
	PRIMARY KEY("id")
);
INSERT INTO "employee" VALUES (1,2);
INSERT INTO "employee" VALUES (2,3);
INSERT INTO "admin" VALUES (1,1);
INSERT INTO "person" VALUES (1,'Haris','Kičin','Adresa',71000,'Sarajevo','admin','password');
INSERT INTO "person" VALUES (2,'Adnan','Kičin
','Grbavička',71000,'Sarajevo','akicin1','pass123');
INSERT INTO "person" VALUES (3,'Fate','Kičin','Demiševci',79260,'Sanski Most','fkicin1','fate123');
INSERT INTO "project" VALUES (1,'Mobile Banking System','1/1/2016',NULL,0);
INSERT INTO "position" VALUES (1,'owner
',NULL,NULL);
INSERT INTO "position" VALUES (2,'director',NULL,NULL);
INSERT INTO "position" VALUES (3,'executive director',NULL,NULL);
INSERT INTO "position" VALUES (4,'manager',NULL,NULL);
INSERT INTO "position" VALUES (5,'team leader',NULL,NULL);
INSERT INTO "position" VALUES (6,'analyst/designer',NULL,NULL);
INSERT INTO "position" VALUES (7,'programmer',NULL,NULL);
INSERT INTO "position" VALUES (8,'tester',NULL,NULL);
INSERT INTO "position" VALUES (9,'help-desk',NULL,NULL);
COMMIT;
