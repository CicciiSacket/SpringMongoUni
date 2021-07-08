import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TeacherRes, MappedTeacher } from '../interface/teacher';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  private uriTeacher =  "http://localhost:8093/teacher"
  constructor(private client: HttpClient) { }

  getTeachersById = async (teachersID: string[]): Promise<MappedTeacher> => await this.client.get(this.uriTeacher+"test", { headers:{teachersID} })
  .toPromise() as Promise<MappedTeacher>

}
