# PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE person (
   rowid integer primary key,
   name VARCHAR(255),
   phone VARCHAR(12),
   email VARCHAR(255),
   can_deliver boolean,
   updated	date,
   committees   VARCHAR(10),
   login   varchar(16),
   password varchar(16),
   hint VARCHAR(255),
   Success date,
   failure date,
   fail_count integer,
   permission integer
);
INSERT INTO person VALUES(1,'Administrator','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','1,2,3,4,5','administrator','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,16);
INSERT INTO person VALUES(2,'Thomas Bodine','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','1,2,3,4,5','tbodine','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,16);
INSERT INTO person VALUES(3,'Fancy Girl','808-202-1702','fancygirl@tommythegeek.com',TRUE,'2016-12-05 01:45:36','1,2,3,4,5','fancygirl','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,8);
INSERT INTO person VALUES(4,'Fred First','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','1,','ffirst','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,8);
INSERT INTO person VALUES(5,'Susan Second','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','2,','ssecond','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,8);
INSERT INTO person VALUES(6,'Thurston Third','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','3,','tthird','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,8);
INSERT INTO person VALUES(7,'Francine Fourth','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','4,','ffourth','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,8);
INSERT INTO person VALUES(8,'Fiala Fifth','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','5,','ffifth','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,8);
INSERT INTO person VALUES(9,'First Regular','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','1,','fregular','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,1);
INSERT INTO person VALUES(10,'Second Regular','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','2,','sregular','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,1);
INSERT INTO person VALUES(11,'Third Regular','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','3,','tregular','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,1);
INSERT INTO person VALUES(12,'Fourth Regular','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','4,','foreg','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,1);
INSERT INTO person VALUES(13,'Fifth Regular','808-202-1702','tom@tommythegeek.com',TRUE,'2016-12-05 01:45:36','5,','fireg','javapass','Coffee walk','2016-12-05 01:45:36','2016-12-05 01:45:36',0,1);
CREATE TABLE items_volunteered (
	rowid integer primary key,
	meetingId integer,
	userid integer,
	itemid integer
);
CREATE TABLE items (
	rowid integer primary key,
	name VARCHAR(255)
);
CREATE TABLE meetings (
	rowid integer primary key,
	time date,
	team integer
);
CREATE TABLE team (
	teamid integer primary key,
	name VARCHAR(255)
);
INSERT INTO team VALUES(1,'First Monday');
INSERT INTO team VALUES(2,'Second Monday');
INSERT INTO team VALUES(3,'Third Monday');
INSERT INTO team VALUES(4,'Fourth Monday');
INSERT INTO team VALUES(5,'Fifth Monday');
CREATE TABLE history (
	rowid integer primary key,
	time date,
	itemid integer,
	memberid integer,
	teamid integer
);
COMMIT;
