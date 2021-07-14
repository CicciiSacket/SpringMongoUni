import { ViewEncapsulation } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Course } from 'src/app/interface/course';
import { Student } from 'src/app/interface/student';
import { CourseService } from 'src/app/services/course.service';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-teacher-page',
  templateUrl: './teacher-page.component.html',
  styleUrls: ['./teacher-page.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class TeacherPageComponent implements OnInit {
  public selectedCourse: number = -1;
  public selectedStudent: string = "";
  public indexOfCourse: number = 0;
  public vote: number = 0;
  public courses: Course[] = [];

voteValidation = new FormControl('', [Validators.required, Validators.min(0), Validators.max(30)]);
  getErrorMessage() {
    if (this.voteValidation.hasError('required')) {
      return 'Inserisci un voto';
    }
    return this.voteValidation.hasError('min') ? 'Il valore minimo è di 0' : 'Il valore massimo è di 30';
  }

  constructor(private courseService: CourseService, private studentService: StudentService) { }

  ngOnInit(): void {
    this.courseService.getCoursesByTeacherEmail(localStorage.getItem('email')!).then(coursesResponse => {
      this.courses = coursesResponse;
    })
  }

  postValutation(): void{
    // if(this.courses[this.selectedCourse] && 
    //   (this.courses[this.selectedCourse].CFU > this.vote && this.courses[this.selectedCourse].CFU >= 0) &&
    //   this.courses[this.selectedCourse].students.find(student => student.id == this.selectedStudent)
    //   )

  }
}
