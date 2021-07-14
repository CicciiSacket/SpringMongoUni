import { Injectable } from '@angular/core';
import { Course } from '../interface/course';
import { Accept }  from '../constants/headers'
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private uriCourses =  "http://localhost:8093/course"
  constructor(private client: HttpClient) { }

  getCourses = async (): Promise<Course[]> => await this.client.get(this.uriCourses).toPromise() as Promise<Course[]>

  getCoursesById = async (coursesID: string[]): Promise<Course[]> => await this.client.get(this.uriCourses+"/byids", { headers:{coursesID} })
    .toPromise() as Promise<Course[]>

    getCoursesByTeacherEmail = async (teacherEmail: string): Promise<Course[]> => {
      const token = (localStorage.getItem("token") ? localStorage.getItem("token")! : "");
      const email = (localStorage.getItem("email") ? localStorage.getItem("email")! : "");
      const role = (localStorage.getItem("role") ? localStorage.getItem("role")! : "");
      const queryEmail = teacherEmail
      return await this.client.get(this.uriCourses+"/teacher/bymail", { headers:{queryEmail, token, email, role, Accept} }).toPromise() as Promise<Course[]>
    }
}
