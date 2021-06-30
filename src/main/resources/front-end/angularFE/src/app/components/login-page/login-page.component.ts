import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  public email: string = "";
  public password: string = "";
  public role : string = "";
  constructor(private authServiceService: AuthServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  formLogin(): void {
    if(this.role == "Student"){
      this.authServiceService.loginStudent(this.email, this.password, this.role).then((response) => {
        if(response.token){
          localStorage.setItem("token", response.token);
          localStorage.setItem("role", this.role);
          localStorage.setItem("email", this.email);
          this.router.navigate(['/student'])
        }else{
          alert("Hai sbagliato bro ripova :)")
        }
      })
    }else if(this.role == "Teacher"){
    }




  }

}
