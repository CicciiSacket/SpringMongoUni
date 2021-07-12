import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student, StudentRes } from '../interface/student';
import { LoginRes } from '../interface/auth';
import { Teacher } from '../interface/teacher';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private uriStudent = "http://localhost:8093/student"
  private uriTeacher = "http://localhost:8093/teacher"
  private uriLogin = "http://localhost:8093/login"

  constructor(private client: HttpClient, private router: Router) { }

  signupStudent = async (student: Student): Promise<Student> => 
    await this.client.post(this.uriStudent, student).toPromise() as Promise<Student>

  signupTeacher = async (teacher: Teacher): Promise<Teacher> => 
    await this.client.post(this.uriTeacher, teacher).toPromise() as Promise<Teacher>
    
  login = async (email: string, password: string, role: string): Promise<LoginRes> =>
    await this.client.post(this.uriLogin + "/" + role.toLowerCase(), {email, password, role}).toPromise() as Promise<LoginRes>

}
