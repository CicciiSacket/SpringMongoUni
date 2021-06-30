import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  public email: string = "";
  public password: String = "";
  public role : String = "";

  constructor() { }

  ngOnInit(): void {
  }

  formLogin(): void {
  }

}
