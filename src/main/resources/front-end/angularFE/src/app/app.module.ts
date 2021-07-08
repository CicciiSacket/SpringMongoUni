//Angular
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//Services
import { StudentService } from './services/student.service';

//Components
import { LoginPageComponent } from './components/login-page/login-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SignupPageComponent } from './components/signup-page/signup-page.component';
import { StudentPageComponent } from './components/student-page/student-page.component';
import { TeacherPageComponent } from './components/teacher-page/teacher-page.component';

//Material
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatInputModule } from '@angular/material/input'; 
import { MatRadioModule } from '@angular/material/radio';
import { MatListModule } from '@angular/material/list'; 



@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    NavbarComponent,
    SignupPageComponent,
    StudentPageComponent,
    TeacherPageComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatListModule,
    MatInputModule,
    MatRadioModule
  ],
  providers: [
    StudentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
