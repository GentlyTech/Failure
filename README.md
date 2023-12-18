# Failure

A grade tracking app, created by failures, for failures.

Ok, but seriously, the primary goal of the app is to calculate minimum grades needed to pass a given class.

## System Description

- Course
  - Each course will contain a name, subject, date of enrollment, expected end of enrollment, and minimum grade required to pass as a number (anything below the number will be considered a fail)
    - For now, things like prerequisites will not need to be implemented
  - Users shall be able to create courses
  - Users shall be able to input assignments/projects/exams/etc. into a given course
  - The minimum grade can be treated as the "desired grade", or perhaps it may be a better idea to have a separate number for the "desired grade" (the grade the user wants to achieve, which may be above the actual minimum for the course itself)
  - With the set of submittables defined by the user, calculate the current expected grade in the course as a number out of 100
    - The user may be able to choose whether to display it as a letter grade or as a number

- Submittable (assignment, project, exam, etc.)
  - Each submittable will contain a name, a description (optional), when the assignment was assigned, due date, a weight, max grade, and achieved grade
    - Additionally, things that help categorization such as what unit/chapter the submittable belongs to could be something to consider implementing, maybe later down the line
  - If a submittable has yet to have a grade input, calculate how much the user needs to achieve in order to not fail the course
  - 