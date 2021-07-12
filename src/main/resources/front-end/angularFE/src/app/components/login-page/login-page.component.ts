import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { DataShareService } from 'src/app/services/data-share.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  public email: string = "";
  public password: string = "";
  public role : string = "";
  constructor(private authServiceService: AuthServiceService,private dataShareService: DataShareService, private router: Router) { }

  ngOnInit(): void {
  }

  formLogin(): void {
    this.authServiceService.login(this.email, this.password, this.role).then((response) => {
      if(response.token){
        localStorage.setItem("token", response.token);
        localStorage.setItem("role", this.role);
        localStorage.setItem("email", this.email);
        this.dataShareService.getUserRole.next(this.role)
        this.dataShareService.isUserLoggedIn.next(true)
        this.router.navigate(['/'+this.role.toLowerCase()])
      }else{
        alert("Hai sbagliato bro ripova :)")
      }
    })
  }

}
