
CREATE VIEW UNIVERSITY.GRAD_FALL AS
select st.name
from UNIVERSITY.STUDENT st, UNIVERSITY.SEMESTER se
where st.graduationDate = se.id and se.year = 2018 
and se.season = 'FALL';


CREATE VIEW UNIVERSITY.CURRENT_COURSES AS
SELECT c.abbreviation, c.number, c.title
FROM UNIVERSITY.SECTION sec, UNIVERSITY.SEMESTER sem, UNIVERSITY.COURSE c
WHERE sec.offered = sem.id 
	and c.id = sec.course
	and sem.year = 2017 
	and sem.season = 'FALL';

CREATE VIEW UNIVERSITY.FAILED_CURRENT_SPRING AS
select distinct stu.name
from UNIVERSITY.STUDENT stu, UNIVERSITY.ENROLLMENT en, UNIVERSITY.SEMESTER sem, UNIVERSITY.SECTION sec
WHERE stu.id = en.student
	and sec.id = en.section
	and en.grade = 'F'
    and sem.id = sec.offered
    and sem.year = 2017
    and sem.season = 'SPRING';


CREATE VIEW UNIVERSITY.CLASS_LOCATIONS AS
SELECT c.abbreviation, c.number, loc.building, loc.room, loc.purpose, sec.startHour
FROM UNIVERSITY.SECTION sec, UNIVERSITY.LOCATION loc, UNIVERSITY.COURSE c
where sec.location = loc.id
	and c.id = sec.course;

CREATE VIEW UNIVERSITY.SEMESTER_GRADES AS
SELECT stu.name, c.title, en.grade
FROM UNIVERSITY.SECTION sec, UNIVERSITY.SEMESTER sem, UNIVERSITY.COURSE c,
	 UNIVERSITY.STUDENT stu, UNIVERSITY.ENROLLMENT en
WHERE sec.offered = sem.id 
	and c.id = sec.course
    and stu.id = en.student
    and sec.id = en.section
	and sem.year = 2017 
	and sem.season = 'FALL'
ORDER BY sec.id;