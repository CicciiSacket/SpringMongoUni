import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { StudentRes } from '../interface/student';

@Injectable({
  providedIn: 'root'
})

export class StudentService {
  private uriStudent = "http://localhost:8093/students"

  constructor(private client: HttpClient, private router: Router) { }

  getAllStudents = async (): Promise<StudentRes> => {

    return await this.client.get(this.uriStudent).toPromise() as Promise<StudentRes>

  }
}
