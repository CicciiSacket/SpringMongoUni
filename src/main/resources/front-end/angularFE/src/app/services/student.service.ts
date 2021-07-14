import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../interface/student';

@Injectable({
  providedIn: 'root'
})

export class StudentService {
  private uriStudent = "http://localhost:8093/student"

  constructor(private client: HttpClient) { }

  getAllStudents = async (): Promise<Student[]> => await this.client.get(this.uriStudent).toPromise() as Promise<Student[]>

  getStudentsFromIdList = async (id: string[]): Promise<Student[]> => {
    const token = (localStorage.getItem("token") ? localStorage.getItem("token")! : "");
    const email = (localStorage.getItem("email") ? localStorage.getItem("email")! : "");
    const role = (localStorage.getItem("role") ? localStorage.getItem("role")! : "");
    return await this.client.get(this.uriStudent + "/search", {headers: { id, email, token, role }} ).toPromise() as Promise<Student[]>
  }


  
}
