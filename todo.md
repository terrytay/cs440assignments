
## Data entry
- [ ] Populate table STUDENT with fake records.
- [ ] Populate table FACULTY with Luther faculty.
- [ x ] Populate table DEPARTMENT with Luther departments.
- [ ] Populate table COURSE with Luther curriculum, fake the credits. 
- [ ] Populate table SECTION with fictional classes (course sections). Assume all 1xx courses have 4 sections, 2xx and 3xx courses have 2 section, 4xx courses have 1 section per year.
- [ ] Populate table ENROLLMENT with fictional enrollment data.
- [ ] Populate table LOCATION with a mix of real buildings and fictional rooms.
- [ ] Populate table SEMESTER with the academic years of 2015-2016, 2016-2017, 2017-2018, and 2018-2019.
- [ ] Populate table MAJOR with Luther majors.

## Constraints (5)
- [ ] Maximum student load is 18 credit hours
- [ ] Maximum section size is 24
- [ ] Maximum instructor load is 16 credit hours
- [ ] Maximum new enrollment (for any year) is 700
- [ ] Semester season must be 'summer', 'fall', 'spring', or 'winter'
- [ ] Two sections in the same location can't have the same startHour
- [ ] Course credits must be an integer between 0 and 4 (inclusive)
- [ ] A room used for the class has a purpose of ‘classroom’
- [ ] Grade can’t be anything other than A, B, C, D, F, or W (null is OK)
- [ ] Faculty endDate must be after startDate or null
- [ ] Student gradYear is not more than 5 years in the future
- [ ] No more than 2 faculties occupying same office location

## Triggers (5)
- [ ] Any grade for a future course is set to NULL
- [ ] New faculty have endDate set to Null
- [ ] New student has major set to Null
- [ ] If a major is deleted, update students and set their new major to NULL
- [ ] If abbreviation is null when adding a course, set it to the first three characters of the title
- [ ] Upon deletion of location entry, set the associated faculty's office to NULL
- [ ] If Faculty startDate is not provided in the insert statement, set it to current semester

## Views (5)
- [ ] RED_ALERT: See all low-enrollment sections (fewer than 8 students enrolled).
- [ ] TRANSCRIPT: All courses taken by a student
- [ ] Roster for each section
- [ ] All current seniors
- [ ] A view of everyone in a section
- [ ] A view to see GPA for a student
- [ ] Student view: use student Id and join enrollment join section join course join everything
- [ ] grades and the names of other students in the same section of the class
- [ ] Current student enrollment view: see all courses enrolled in this semester, along with
- [ ] Current courses view: show all courses offered this semester
- [ ] Transcript view: use student Id to see grades and titles for all classes taken
- [ ] Faculty view: show faculty and appropriate departments and locations
- [ ] that you can use the student Id to find relevant information for one student