import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ExerciseResult } from '../model/exercise-result';


@Injectable({
  providedIn: 'root',
})
export class ExerciseResultService {
  private readonly baseUrl = '/api/exercise';
  private readonly resultUrl = 'result';

  constructor(private http: HttpClient) {}

  getAll(categoryId: string): Observable<ExerciseResult[]> {
    return this.http.get<ExerciseResult[]>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}`
    );
  }

  getOne(categoryId:string, id:string): Observable<ExerciseResult> {
    return this.http.get<ExerciseResult>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}/${id}`
    );
  }

  create(categoryId: string, result: ExerciseResult) {
    return this.http.post<ExerciseResult>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}`,
      result
    );
  }

  update(categoryId:string, exerciseId:string, result:ExerciseResult) {
    return this.http.patch<ExerciseResult>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}/${result.id}`,
      result
    );
  }

  delete(categoryId: string | undefined, id: string | undefined): Observable<any> {
    return this.http.delete<ExerciseResult>(
      `${this.baseUrl}/${categoryId}/${this.resultUrl}/${id}`
    );
  }

}
