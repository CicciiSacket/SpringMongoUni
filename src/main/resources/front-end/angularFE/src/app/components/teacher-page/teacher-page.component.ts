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
  public selectedStudent: string = "";
  public indexOfCourse: number = 0;
  public courses: Course[] = [];
  public students : Student[] = [];
  ngOnInit(): void {
    this.courseService.getCoursesByTeacherEmail(localStorage.getItem('email')!).then(coursesResponse => {
      this.courses = coursesResponse;
    })
  }

  constructor(private courseService: CourseService, private studentService: StudentService) { }

  voteValidation = new FormControl('', [Validators.required, Validators.min(0)]);
  getErrorMessage() {
    if (this.voteValidation.hasError('required')) {
      return 'Inserisci un voto';
    }
    return this.voteValidation.hasError('min') ? 'Il valore minimo è di 0' : 'Il valore massimo è di '+this.voteValidation.getError("max").max;
  }

  selectCourse(event: number){
    if(event >= 0){
      this.indexOfCourse = event;
      this.voteValidation.setValidators(Validators.max(this.courses[event].cfu))
      this.studentService.getStudentsFromIdList(this.courses[event].studentsId).then(studentsResponse => {
        this.students = studentsResponse;
      })
    }else this.students = []
  }

  postValutation(): void{
    // if(this.courses[this.indexOfCourse] && 
    //   (this.courses[this.indexOfCourse].cfu >= this.voteValidation.value && this.courses[this.indexOfCourse].cfu >= 0) &&
    //   this.courses[this.indexOfCourse].studentsId.find(student => student == this.selectedStudent)
    //   ){}

  }
}
