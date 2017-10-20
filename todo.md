
## Data entry
- [ ] Populate table STUDENT with fake records.
- [ ] Populate table FACULTY with Luther faculty.
- [ x ] Populate table DEPARTMENT with Luther departments.
- [ x ] Populate table COURSE with Luther curriculum, fake the credits. 
    - [ x ] get department
    - [ x ] format the course's name correctly
- [ ] Populate table SECTION with fictional classes (course sections). Assume all 1xx courses have 4 sections, 2xx and 3xx courses have 2 section, 4xx courses have 1 section per year.
- [ ] Populate table ENROLLMENT with fictional enrollment data.
- [ ] Populate table LOCATION with a mix of real buildings and fictional rooms.
- [ x ] Populate table SEMESTER with the academic years of 2015-2016, 2016-2017, 2017-2018, and 2018-2019.
- [ x ] Populate table MAJOR with Luther majors.


To do:
make another map for Courses that then saves the dept_id so that we don't have to query all the time.



## Triggers (5)
Just create triggers in SQL files if it doesn't work on the real database. 

- [ ] New faculty have endDate set to Null
- [ ] New student has major set to Null
- [ ] If Faculty startDate is not provided in the insert statement, set it to current semester
- [ ] Any grade for a future course is set to NULL
- [ ] If abbreviation is null when adding a course, set it to the first three characters of the title
##################
PROBABLY NOT THESE
##################
- [ ] If a major is deleted, update students and set their new major to NULL
- [ ] Upon deletion of location entry, set the associated faculty's office to NULL


## Views (5)

- [ ] All current seniors
- [ ] Roster for each section
- [ ] Current courses view: show all courses offered this semester
- [ ] TRANSCRIPT: All courses taken by a student
- [ ] A view to see GPA for a student
##################
PROBABLY NOT THESE
##################
- [ ] RED_ALERT: See all low-enrollment sections (fewer than 8 students enrolled).
- [ ] A view of everyone in a section
- [ ] grades and the names of other students in the same section of the class
- [ ] Current student enrollment view: see all courses enrolled in this semester
- [ ] Current courses view: show all courses offered this semester
- [ ] Transcript view: use student Id to see grades and titles for all classes taken
- [ ] Faculty view: show faculty and appropriate departments and locations
- [ ] that you can use the student Id to find relevant information for one student