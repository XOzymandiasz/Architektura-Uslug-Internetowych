import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import {ExerciseService} from '../../service/exercise.service';
import {Exercise} from '../../model/exercise';

@Component({
  selector: 'app-exercise-list',
  templateUrl: './exercise-list.component.html',
  styleUrls: ['./exercise-list.component.css'],
  standalone: false
})
export class ExerciseListComponent implements OnInit {

  constructor(private service: ExerciseService,
              private cdr: ChangeDetectorRef) {}


  exs: Exercise[] = [];

  ngOnInit(): void {
    this.service.getAll()
      .subscribe(exercises => {
        this.exs = exercises;
        this.cdr.detectChanges();
      });
  }

  onDelete(exercise: Exercise): void {
    this.service.delete(exercise)
      .subscribe(() => this.ngOnInit())
  }

}
