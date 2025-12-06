import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exercise } from '../model/exercise';
import {Exercises} from '../model/exercises';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {
  private readonly baseUrl: string = 'api/exercise';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Exercises> {
    return this.http.get<Exercises>(
      `${this.baseUrl}`
    );
  }

  getOne(id: string): Observable<Exercise> {
    return this.http.get<Exercise>(
      `${this.baseUrl}/${id}`
    );
  }

  create(exercise: Exercise): Observable<any> {
    return this.http.post<Exercise>(
      `${this.baseUrl}`, exercise
    );
  }

  update(exercise: Exercise): Observable<any> {
    return this.http.put<Exercise>(
      `${this.baseUrl}`, exercise
    );
  }

  delete(exercise: Exercise): Observable<any> {
    return this.http.delete<Exercise>(
      `${this.baseUrl}/${exercise.id}`
    );
  }

}
