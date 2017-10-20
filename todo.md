
## Data entry
- [ ] Populate table STUDENT with fake records.
- [ ] Populate table FACULTY with Luther faculty.
- [ x ] Populate table DEPARTMENT with Luther departments.
- [ ] Populate table COURSE with Luther curriculum, fake the credits. 
    - [ ] get department
    - [ x ] format the course's name correctly
- [ ] Populate table SECTION with fictional classes (course sections). Assume all 1xx courses have 4 sections, 2xx and 3xx courses have 2 section, 4xx courses have 1 section per year.
- [ ] Populate table ENROLLMENT with fictional enrollment data.
- [ ] Populate table LOCATION with a mix of real buildings and fictional rooms.
- [ x ] Populate table SEMESTER with the academic years of 2015-2016, 2016-2017, 2017-2018, and 2018-2019.
- [ ] Populate table MAJOR with Luther majors.


To do:
make another map for Courses that then saves the dept_id so that we don't have to query all the time.






## Constraints (5)
- [ ] Grade can’t be anything other than A, B, C, D, F, or W (null is OK)
- [ ] Faculty endDate must be after startDate or null
- [ ] Semester season must be 'summer', 'fall', 'spring', or 'winter'
- [ ] A room used for the class has a purpose of ‘classroom’
- [ ] Maximum new enrollment (for any year) is 700
##################
PROBABLY NOT THESE
##################
- [ ] Maximum student load is 18 credit hours
- [ ] Maximum section size is 24
- [ ] Maximum instructor load is 16 credit hours
- [ ] Two sections in the same location can't have the same startHour
- [ ] Course credits must be an integer between 0 and 4 (inclusive)
- [ ] Student gradYear is not more than 5 years in the future
- [ ] No more than 2 faculties occupying same office location

## Triggers (5)
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