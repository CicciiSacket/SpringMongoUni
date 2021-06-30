import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../interface/student';
import { Teacher } from '../interface/teacher';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private uriStudent = "http://localhost:8093/students"
  private uriTeacher = "http://localhost:8093/teachers"

  constructor(private client: HttpClient, private router: Router) { }

  signupStudent = async (student: Student): Promise<Student> => 
    await this.client.post(this.uriStudent, student).toPromise() as Promise<Student>

  signupTeacher = async (teacher: Teacher): Promise<Teacher> => 
    await this.client.post(this.uriTeacher, teacher).toPromise() as Promise<Teacher>
}
