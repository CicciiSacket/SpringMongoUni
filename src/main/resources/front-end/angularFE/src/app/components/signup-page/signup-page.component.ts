import { Component, OnInit } from '@angular/core';
import { Role } from 'src/app/interface/role';
import { Student } from 'src/app/interface/student';

import { AuthServiceService } from '../../services/auth-service.service';

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.css']
})
export class SignupPageComponent implements OnInit {

  public name : string = "";
  public surname : string = "";
  public email : string = "";
  public password : string = "";
  public role : string = "";

  constructor(private authServiceService: AuthServiceService) { }

  ngOnInit(): void {
  }

  formSingup(): void {
    if(this.role == "student"){
      this.authServiceService.signupStudent({
        name:  this.name,
        surname:  this.surname,
        email:  this.email,
        password:  this.password,
        role:  this.role,
        token: ""
      })
    }else if(this.role == "teacher"){
      this.authServiceService.signupTeacher({
        name:  this.name,
        surname:  this.surname,
        email:  this.email,
        password:  this.password,
        role:  this.role,
        token: ""
      })
    }
  }

}
