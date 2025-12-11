import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import {ExerciseResult} from '../../model/exercise-result';
import {ExerciseResultService} from '../../service/exerciseResult.service';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-exercise-result-list',
  templateUrl: './exercise-result-list.component.html',
  styleUrls: ['./exercise-result-list.component.css'],
  standalone: false,
})
export class ExerciseResultListComponent implements OnInit {

  constructor(private service: ExerciseResultService,
              private cdr: ChangeDetectorRef,
              private route: ActivatedRoute,
              private router: Router,
  ) {}

  results: ExerciseResult[] = [];
  exerciseId: string | undefined;

  ngOnInit(): void {
    this.exerciseId = this.route.snapshot.paramMap.get('uuid')!;
    this.service.getAll(this.exerciseId)
      .subscribe(results => {
        this.results = results
        this.cdr.detectChanges()
      });
  }

  onDelete(resultId: string): void {
    this.service.delete(this.exerciseId, resultId)
      .subscribe(() => this.ngOnInit());
  }
}

