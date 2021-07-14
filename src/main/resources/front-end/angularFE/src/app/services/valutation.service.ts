import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Valutation } from '../interface/valutations';

@Injectable({
  providedIn: 'root'
})
export class ValutationService {
  private uriValutations =  "http://localhost:8093/valutations"

  constructor(private client: HttpClient) { }

  getStudentValutations = async (): Promise<Valutation[]> => {
    const token = localStorage.getItem('token')
    const email = localStorage.getItem('email')
    const role = localStorage.getItem('role')
    if(email && token && role) {
      return await this.client.get(this.uriValutations + "/student", {
        headers: {'email':email, 'token':token, 'role':role}
     } ).toPromise() as Promise<Valutation[]>
    }
    return []
  }
}
