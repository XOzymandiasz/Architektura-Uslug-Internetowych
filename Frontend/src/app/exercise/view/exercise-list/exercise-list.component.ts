import { Component, OnInit } from '@angular/core';
import {ExerciseService} from '../../service/exercise.service';
import {Exercises} from '../../model/exercises';
import {Exercise} from '../../model/exercise';

@Component({
  selector: 'app-exercise-list',
  templateUrl: './exercise-list.component.html',
  styleUrls: ['./exercise-list.component.css'],
  standalone: false
})
export class ExerciseListComponent implements OnInit {

  constructor(private service: ExerciseService) {}

  exercises: Exercises | undefined;

  ngOnInit(): void {
    this.service.getAll()
      .subscribe(exercises => this.exercises = exercises)
  }

  onDelete(exercise: Exercise): void {
    this.service.delete(exercise)
      .subscribe(() => this.ngOnInit())
  }

}
