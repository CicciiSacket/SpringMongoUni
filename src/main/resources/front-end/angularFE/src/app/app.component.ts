import { Component } from '@angular/core';
import { StudentRes } from './interface/student';
import { StudentService } from './services/student.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angularFE';
  studentsList: StudentRes | undefined;

  constructor(private studentService: StudentService){

  }

  ngOnInit(): void{
    this.getStudents();
  }

  async getStudents(): Promise<void> {
    this.studentsList = await this.studentService.getAllStudents();
  }



}
