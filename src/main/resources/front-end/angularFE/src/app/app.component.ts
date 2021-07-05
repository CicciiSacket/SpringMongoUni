import { Component } from '@angular/core';
import { Student, StudentRes } from './interface/student';
import { StudentService } from './services/student.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angularFE';

  studentsList: Student[] | undefined;

  constructor(private studentService: StudentService){
   
  }

  ngOnInit(): void{
  }
}
