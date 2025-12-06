import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ExerciseResult } from '../model/exercise-result';
import {ExerciseResults} from '../model/exercise-results';


@Injectable({
  providedIn: 'root',
})
export class Service {
  private readonly baseUrl = '/api/exercise';
  private readonly resultUrl = 'result';

  constructor(private http: HttpClient) {}

  getAll(categoryId: string): Observable<ExerciseResults> {
    return this.http.get<ExerciseResults>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}`
    );
  }

  getOne(categoryId:string, id:string): Observable<ExerciseResult> {
    return this.http.get<ExerciseResult>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}/${id}`
    );
  }

  create(categoryId:number, result:ExerciseResult) {
    return this.http.post<ExerciseResult>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}`,
      result
    );
  }

  update(categoryId:number, result:ExerciseResult) {
    return this.http.put<ExerciseResult>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}/${result.id}`,
      result
    );
  }

  delete(categoryId: string, id: string): Observable<any> {
    return this.http.delete<ExerciseResult>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}/${id}`
    );
  }

}
