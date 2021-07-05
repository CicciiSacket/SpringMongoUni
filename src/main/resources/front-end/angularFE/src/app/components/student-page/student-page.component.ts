import { Component, OnInit } from '@angular/core';
import { Valutation, MappedValutation } from 'src/app/interface/valutations';
import { StudentService } from 'src/app/services/student.service';
import { CourseService } from 'src/app/services/course.service';
import { Course } from 'src/app/interface/course';
import { DataShareService } from 'src/app/services/data-share.service';
@Component({
  selector: 'app-student-page',
  templateUrl: './student-page.component.html',
  styleUrls: ['./student-page.component.css']
})
export class StudentPageComponent implements OnInit {
  public valutations : Valutation[] = []
  public courses : Course[] = []

  constructor(private studentService: StudentService, private courseService: CourseService, private dataShareService: DataShareService) { }

  ngOnInit(): void {
    this.studentService.getStudentValutations().then(valutations =>{
      this.dataShareService.isUserLoggedIn.next(true)
      console.log(valutations)
      if (valutations.length > 0 ){
        this.valutations = valutations
        this.courseService.getCoursesById(this.mappedValutationsKeys).then(courses =>{
          console.log(courses)
          if(courses.length > 0){
            this.courses = courses
          }
        })
      }
    })
    
  }

  fakeData = (): void =>{
    this.valutations = [{
      id: "id1",
      id_course: "id1",
      id_student: "id1",
      id_teacher: "id1",
      vote: 10
    },
    {
      id: "id2",
      id_course: "id1",
      id_student: "id1",
      id_teacher: "id1",
      vote: 8
    },
    {
      id: "id5",
      id_course: "id1",
      id_student: "id1",
      id_teacher: "id1",
      vote: 7
    },
    {
      id: "id3",
      id_course: "id2",
      id_student: "id2",
      id_teacher: "id2",
      vote: 20
    },
    {
      id: "id4",
      id_course: "id3",
      id_student: "id3",
      id_teacher: "id3",
      vote: 30
    }]; 
    this.courses = [{
      id: "id1",
      name: "fake1",
      CFU: 10
    },
    {
      id: "id2",
      name: "fake1",
      CFU: 10
    },
    {
      id: "id3",
      name: "fake1",
      CFU: 10
    },
    {
      id: "id4",
      name: "fake1",
      CFU: 10
    }
  ]; 
  }

  get mappedValutations(): MappedValutation {
    return this.valutations.reduce((acc:MappedValutation,value)=>{
      acc[value.id_course] = [...acc[value.id_course] || [], value];
      return acc
    }, {});
  }

  get mappedValutationsKeys(): string[] {
    return Object.keys(this.mappedValutations)
  }
}
