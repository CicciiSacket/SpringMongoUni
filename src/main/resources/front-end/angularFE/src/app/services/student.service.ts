import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Student, StudentRes } from '../interface/student';
import { Valutation } from '../interface/valutations';

@Injectable({
  providedIn: 'root'
})

export class StudentService {
  private uriStudent = "http://localhost:8093/student"
  private uriStudentValutations =  "http://localhost:8093/valutations/student"
  constructor(private client: HttpClient, private router: Router) { }

  getAllStudents = async (): Promise<Student[]> => await this.client.get(this.uriStudent).toPromise() as Promise<Student[]>

  getStudentValutations = async (): Promise<Valutation[]> => {
    const token = localStorage.getItem('token')
    const email = localStorage.getItem('email')
    const role = localStorage.getItem('role')
    if(email && token && role) {
      return await this.client.get(this.uriStudentValutations, {
        headers: {'email':email, 'token':token, 'role':role}
     } ).toPromise() as Promise<Valutation[]>
    }
    return []
  }

  getStudentsFromIdList = async (id: string[]): Promise<Student[]> => {
    const token = (localStorage.getItem("token") ? localStorage.getItem("token")! : "");
    const email = (localStorage.getItem("email") ? localStorage.getItem("email")! : "");
    const role = (localStorage.getItem("role") ? localStorage.getItem("role")! : "");
    return await this.client.get(this.uriStudent + "/search", {headers: { id, email, token, role }} ).toPromise() as Promise<Student[]>
  }


  
}
