## Explanations

* To get the names of the students, I found a list of popular first names and popular last names, copied them into respective files. Then, I used Linux's `paste` command to merge all lines on the files, and put that into a new file. 
```
paste -d" " first_names.txt last_names.txt > names.txt
```

* used Roman's command to get the abbreviations. Used find and replace/reg ex to build up the dictionary


* Deleted Library and Information Studies

* for SECTION, times are in 24-hour time
    * 800 = 8:00am
    * 1500 = 3:00pm

* Deleted IS courses, no faculty teach in that dept.
* Deleted WGST courses, no faculty teach in that dept. 

 
## Assumptions:
* Science: Chemistry
* FCUL: Modern Languages
* Museum Studies: History
* General Studies: Paideia
* Removing the Nursing major

