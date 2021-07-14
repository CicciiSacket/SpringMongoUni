
import { Component, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';
import { CourseService } from 'src/app/services/course.service';
import { DataShareService } from 'src/app/services/data-share.service';
import { TeacherService } from 'src/app/services/teacher.service';
import { ValutationService } from 'src/app/services/valutation.service';
import { Valutation, MappedValutation } from 'src/app/interface/valutations';
import { MappedTeacher } from 'src/app/interface/teacher';
import { Course } from 'src/app/interface/course';


@Component({
  selector: 'app-student-page',
  templateUrl: './student-page.component.html',
  styleUrls: ['./student-page.component.css']
})
export class StudentPageComponent implements OnInit {
  public valutations : Valutation[] = [];
  public courses : Course[] = [];
  public teachers : MappedTeacher = {};

  constructor(
      private studentService: StudentService, 
      private courseService: CourseService, 
      private teacherService: TeacherService,
      private valutationService: ValutationService,
      private dataShareService: DataShareService
    ){}

  ngOnInit(): void {
    this.getValutations();
  }

  private getValutations = () => {
    this.valutationService.getStudentValutations().then(valutations =>{
      this.dataShareService.isUserLoggedIn.next(true);
      if (valutations.length > 0 ){
        this.valutations = valutations;
        this.getCoursesById();
        this.getTeachersById(valutations);
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

  private getTeachersById = (valutations: Valutation[]) =>{
    const teachersId = valutations.reduce((acc: string[], current) => {
      if(acc.indexOf(current.id_teacher) < 0){
        acc.push(current.id_teacher);
      }
      return acc
    },[])
    this.teacherService.getTeachersById(teachersId).then(teachers =>{
      if(Object.keys(teachers).length > 0){
        this.teachers = teachers;
      }
    })
  }


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
