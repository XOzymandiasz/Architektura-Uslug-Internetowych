import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ExerciseResultService} from '../../service/exerciseResult.service';
import {ExerciseResult} from '../../model/exercise-result';
import {Exercise} from '../../../exercise/model/exercise';
import {ExerciseService} from '../../../exercise/service/exercise.service';

@Component({
  selector: 'app-element-details',
  templateUrl: './exercise-result-details.component.html',
  styleUrls: ['./exercise-result-details.component.css'],
  standalone: false
})
export class ExerciseResultDetailsComponent implements OnInit {
  exerciseId: string | undefined;
  resultId: string | undefined;
  result: ExerciseResult | undefined;
  exercise: Exercise | undefined;
  loaded = false;

  constructor(
    private route: ActivatedRoute,
    private service: ExerciseResultService,
    private exerciseService: ExerciseService,
    private cdr: ChangeDetectorRef,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.exerciseId = params.get('uuid')!;
      this.resultId = params.get('resultId')!;

      this.load();
    });
  }

  private load(): void {
    this.service
      .getOne(this.exerciseId, this.resultId)
      .subscribe(el => {
        if (!this.loaded) {
          this.loaded = true
          this.result = el
          this.cdr.detectChanges()
        }
      });
    this.exerciseService
      .getOne(this.exerciseId)
      .subscribe(exercise => {
        this.exercise = exercise;
        this.cdr.detectChanges()
      })
  }

  onDelete(): void {
    this.service.delete(this.exerciseId, this.resultId)
      .subscribe({
        next: () => {
          this.router.navigate(['/exercise', this.exerciseId, 'results']);

        },
        error: err => {
          console.error('Delete error', err);
        }
      });
  }
}
