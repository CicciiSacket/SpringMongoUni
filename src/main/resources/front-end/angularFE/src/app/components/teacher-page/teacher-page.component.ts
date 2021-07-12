import { ViewEncapsulation } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

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
  public courses = [
    {
      id: "id1",
      name:"name1",
      CFU: 10,
      students:[
        {
          id:"id1student",
          name: "namestudent1",
          surname: "surnamestudent1"
        }
      ]
    },
    {
      id: "id2",
      name:"name2",
      CFU: 20,
      students:[
        {
          id:"id2student",
          name: "namestudent2",
          surname: "surnamestudent2"
        }
      ]
    }
];

voteValidation = new FormControl('', [Validators.required, Validators.min(0), Validators.max(30)]);
  getErrorMessage() {
    if (this.voteValidation.hasError('required')) {
      return 'Inserisci un voto';
    }
    return this.voteValidation.hasError('min') ? 'Il valore minimo è di 0' : 'Il valore massimo è di 30';
  }

  constructor() { }

  ngOnInit(): void {
  }

  postValutation(): void{
    // if(this.courses[this.selectedCourse] && 
    //   (this.courses[this.selectedCourse].CFU > this.vote && this.courses[this.selectedCourse].CFU >= 0) &&
    //   this.courses[this.selectedCourse].students.find(student => student.id == this.selectedStudent)
    //   )

  }
}
