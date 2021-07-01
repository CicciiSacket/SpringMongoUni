import { Component, OnInit } from '@angular/core';
import { DataShareService } from 'src/app/services/data-share.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logged: boolean = false;
  role: string = "";

  constructor(private dataShareService: DataShareService) { }

  ngOnInit(): void {
    this.dataShareService.isUserLoggedIn.subscribe(value => {
      this.logged = value;
    });
    this.dataShareService.getUserRole.subscribe(value => {
      this.role = value;
      this.role = this.role.toLowerCase()
    });
  }

}
