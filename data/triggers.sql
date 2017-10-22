--new faculty has startDate to next semester where it is inserted as null
create trigger new_faculty
after INSERT on UNIVERSITY.FACULTY
for each row
begin
    update UNIVERSITY.FACULTY set startDate = 8
    where startDate = null;
end;

--new student doesn't have a major, have to declare later
create trigger new_student
after INSERT on UNIVERSITY.STUDENT
for each row
begin
    update UNIVERSITY.STUDENT set major = null
end;


-- new enrollment, ensure null grade
create trigger new_enrollment
after INSERT on UNIVERSITY.ENROLLMENT
for each row
begin
    update UNIVERSITY.ENROLLMENT set grade = null
end;


--If a major is deleted, 
--update students and set their new major to NULL
create trigger deleted_major
after DELETE on UNIVERSITY.MAJOR
for each row
begin  
    update UNIVERSITY.STUDENT set major = null
    where student.major = major.id
end;

--Upon deletion of location entry, 
--set the associated faculty's office to NULL
create trigger deleted_office
after DELETE on UNIVERSITY.LOCATION
for each row
begin
    update UNIVERSITY.FACULTY set office = null
    where location.id = faculty.office
end;