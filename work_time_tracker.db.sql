BEGIN TRANSACTION;
DROP TABLE IF EXISTS "work_hours";
CREATE TABLE IF NOT EXISTS "work_hours" (
	"id"	INTEGER,
	"user_id"	INTEGER,
	"date"	DATE,
	"started_working"	TEXT,
	"finished_working"	TEXT,
	"work_hours"	TEXT,
	FOREIGN KEY("user_id") REFERENCES "user"("id")
);
DROP TABLE IF EXISTS "project";
CREATE TABLE IF NOT EXISTS "project" (
	"id"	INTEGER,
	"name"	TEXT,
	"activity"	INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY("id")
);
DROP TABLE IF EXISTS "position";
CREATE TABLE IF NOT EXISTS "position" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL,
	PRIMARY KEY("id")
);
DROP TABLE IF EXISTS "user";
CREATE TABLE IF NOT EXISTS "user" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL,
	"surname"	TEXT NOT NULL,
	"address"	TEXT,
	"postal_number"	INTEGER,
	"city"	TEXT,
	"position_id"	INTEGER,
	"username"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	"is_admin"	INTEGER DEFAULT 0,
	PRIMARY KEY("id"),
	FOREIGN KEY("position_id") REFERENCES "position"("id")
);
DROP TABLE IF EXISTS "project_work_hours";
CREATE TABLE IF NOT EXISTS "project_work_hours" (
	"id"	INTEGER,
	"user_id"	INTEGER,
	"project_id"	INTEGER,
	"date"	DATE,
	"work_hours"	TEXT,
	FOREIGN KEY("project_id") REFERENCES "project"("id"),
	FOREIGN KEY("user_id") REFERENCES "user"("id")
);
DROP TABLE IF EXISTS "employee_has_position";
CREATE TABLE IF NOT EXISTS "employee_has_position" (
	"user_id"	INTEGER,
	"position_id"	INTEGER,
	FOREIGN KEY("position_id") REFERENCES "position"("id"),
	FOREIGN KEY("user_id") REFERENCES "user"("id")
);
INSERT INTO "project" VALUES (1,'Mobile Banking System',0);
INSERT INTO "project" VALUES (2,'Video Store Software',1);
INSERT INTO "project" VALUES (3,'gaga',0);
INSERT INTO "position" VALUES (1,'owner');
INSERT INTO "position" VALUES (2,'director');
INSERT INTO "position" VALUES (3,'executive director');
INSERT INTO "position" VALUES (4,'manager');
INSERT INTO "position" VALUES (5,'team leader');
INSERT INTO "position" VALUES (6,'analyst/designer');
INSERT INTO "position" VALUES (7,'programmer');
INSERT INTO "position" VALUES (8,'tester');
INSERT INTO "position" VALUES (9,'help-desk');
INSERT INTO "user" VALUES (1,'Haris','Kičin','Adresa',71000,'Sarajevo',1,'admin','password',1);
INSERT INTO "user" VALUES (2,'Adnan','Kičin','Grbavička',71000,'Sarajevo',2,'akicin1','pass123',0);
INSERT INTO "user" VALUES (3,'Fate','Kičin','Demiševci',79260,'Sanski Most',3,'fkicin1','fate123',0);
COMMIT;
