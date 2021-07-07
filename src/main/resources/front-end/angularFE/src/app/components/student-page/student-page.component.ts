import { Component, OnInit } from '@angular/core';
import { Valutation, MappedValutation } from 'src/app/interface/valutations';
import { StudentService } from 'src/app/services/student.service';
import { CourseService } from 'src/app/services/course.service';
import { Course } from 'src/app/interface/course';
import { TeacherRes } from 'src/app/interface/teacher';
import { DataShareService } from 'src/app/services/data-share.service';
import { TeacherService } from 'src/app/services/teacher.service';

@Component({
  selector: 'app-student-page',
  templateUrl: './student-page.component.html',
  styleUrls: ['./student-page.component.css']
})
export class StudentPageComponent implements OnInit {
  public valutations : Valutation[] = [];
  public courses : Course[] = [];
  public teachers : TeacherRes[] = [];

  constructor(
      private studentService: StudentService, 
      private courseService: CourseService, 
      private teacherService: TeacherService,
      private dataShareService: DataShareService
    ){}

  ngOnInit(): void {
    this.getValutations();
  }

  private getValutations = () => {
    this.studentService.getStudentValutations().then(valutations =>{
      this.dataShareService.isUserLoggedIn.next(true);
      if (valutations.length > 0 ){
        this.valutations = valutations;
        this.getCoursesById();
        //this.getTeachersById(valutations);
      }
    })
  }


  private getCoursesById = () =>{
    this.courseService.getCoursesById(this.mappedValutationsKeys).then(courses =>{
      if(courses.length > 0){
        this.courses = courses;
      }
    })
  }

  // private getTeachersById = (valutations: Valutation[]) =>{
  //   const teachersId = valutations.reduce((acc: string[], current) => {
  //     if(acc.indexOf(current.id_teacher) < 0){
  //       acc.push(current.id_teacher);
  //     }
  //     return acc
  //   },[])

  //   this.teacherService.getTeachersById(teachersId).then(teachers =>{
  //     if(teachers.length > 0){
  //       this.teachers = teachers;
  //     }
  //   })
  // }


  get mappedValutations(): MappedValutation {
    return this.valutations.reduce((acc:MappedValutation,value)=>{
      acc[value.id_course] = [...acc[value.id_course] || [], value];
      return acc;
    }, {});
  }

  get mappedValutationsKeys(): string[] {
    return Object.keys(this.mappedValutations);
  }
}
