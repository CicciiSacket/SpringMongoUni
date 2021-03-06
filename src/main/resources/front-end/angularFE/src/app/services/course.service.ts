import { Injectable } from '@angular/core';
import { Course } from '../interface/course';
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

}
