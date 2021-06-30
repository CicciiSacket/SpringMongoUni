import { Component, OnInit } from '@angular/core';
import { Valutation } from 'src/app/interface/valutations';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-student-page',
  templateUrl: './student-page.component.html',
  styleUrls: ['./student-page.component.css']
})
export class StudentPageComponent implements OnInit {
  public valutations : Valutation[] = []
  constructor(private studentService: StudentService) { }

  ngOnInit(): void {
    this.studentService.getStudentValutations().then(valutations =>{
      console.log(valutations)
      if (valutations.length > 0 ){
        this.valutations = valutations
        console.log(valutations)
      }
    })
  }

}
