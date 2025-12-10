import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import { Exercise } from '../model/exercise';
import {ExerciseMapperService} from './exercise-mapper.service';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {
  private readonly baseUrl: string = '/api/exercise';

  constructor(private http: HttpClient, private mapper: ExerciseMapperService) {

  }

  getAll(): Observable<Exercise[]> {
    return this.http.get<any[]>(
      `${this.baseUrl}/list`
    ).pipe(
      map(response => {
        return this.mapper.collectionRepresentationToModel(response);
      })
    );
  }

  getOne(id: string): Observable<Exercise> {
    return this.http.get<Exercise>(
      `${this.baseUrl}/${id}`
    ).pipe(
      map(response => this.mapper.representationToModel(response))
    );
  }

  create(exercise: Exercise): Observable<any> {
    return this.http.post<Exercise>(
      `${this.baseUrl}`, exercise
    );
  }

  update(uuid: string, exercise: Exercise): Observable<Exercise> {
    return this.http.put<Exercise>(
      `${this.baseUrl}/${uuid}`,
      exercise
    );
  }

  delete(exercise: Exercise): Observable<any> {
    return this.http.delete<Exercise>(
      `${this.baseUrl}/${exercise.id}`
    );
  }

}
