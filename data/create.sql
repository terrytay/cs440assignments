DROP DATABASE IF EXISTS UNIVERSITY;
CREATE DATABASE UNIVERSITY;

create table UNIVERSITY.DEPARTMENT (
    id int NOT NULL auto_increment,
    name text,
    building text,

    PRIMARY KEY (id)
);

create table UNIVERSITY.SEMESTER (
    id int NOT NULL auto_increment,
    year int,
    season text,

    PRIMARY KEY (id)
);

create table UNIVERSITY.COURSE (
    id int NOT NULL auto_increment,
    department int,
    abbreviatino text,
    number int,
    title text,
    credits int,

    PRIMARY KEY (id),
    FOREIGN KEY (department) REFERENCES DEPARTMENT(id)
);

create table UNIVERSITY.LOCATION (
    id int NOT NULL auto_increment,
    building text,
    room int,
    purpose text,

    PRIMARY KEY (id)
);

create table UNIVERSITY.MAJOR (
    id int NOT NULL auto_increment,
    department int,
    name text,

    PRIMARY KEY (id),
    FOREIGN KEY (department) REFERENCES DEPARTMENT(id)

);

create table UNIVERSITY.FACULTY (
    id int NOT NULL auto_increment,
    name text,
    department int,
    startDate int,
    endDate int null,
    office int,

    PRIMARY KEY (id),
    FOREIGN KEY (department) REFERENCES DEPARTMENT(id),
    FOREIGN KEY (startDate) REFERENCES SEMESTER(id),
    FOREIGN KEY (endDate) REFERENCES SEMESTER(id),
    FOREIGN KEY (office) REFERENCES LOCATION(id)
);


create table UNIVERSITY.SECTION (
    id int NOT NULL auto_increment,
    course int,
    instructor int,
    offered int,
    location int,
    startHour time,

    PRIMARY KEY (id),
    FOREIGN KEY (course) REFERENCES COURSE(id),
    FOREIGN KEY (instructor) REFERENCES FACULTY(id),
    FOREIGN KEY (offered) REFERENCES SEMESTER(id),
    FOREIGN KEY (location) REFERENCES LOCATION(id)
);


create table UNIVERSITY.STUDENT (
    id int NOT NULL auto_increment,
    name text,
    graduationDate int,
    major int NULL,
    adviser int,

    PRIMARY KEY (id),
    FOREIGN KEY (graduationDate) REFERENCES SEMESTER(id),
    FOREIGN KEY (major) REFERENCES MAJOR(id),
    FOREIGN KEY (adviser) REFERENCES FACULTY(id)
);

create table UNIVERSITY.ENROLLMENT (
    id int NOT NULL auto_increment,
    student int,
    section int,
    grade text,

    PRIMARY KEY (id),
    FOREIGN KEY (student) REFERENCES STUDENT(id),
    FOREIGN KEY (section) REFERENCES SECTION(id)
);

