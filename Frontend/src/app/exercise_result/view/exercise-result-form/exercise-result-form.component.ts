import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ExerciseResult} from '../../model/exercise-result';
import {ExerciseResultService} from '../../service/exerciseResult.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-exercise-result-form',
  templateUrl: './exercise-result-form.component.html',
  styleUrls: ['./exercise-result-form.component.css'],
  standalone: false
})
export class ExerciseResultFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  found = false;

  result: ExerciseResult | undefined;
  original: ExerciseResult | undefined;

  exerciseId!: string;
  resultId: string | null = null;

  constructor(
    private fb: FormBuilder,
    private service: ExerciseResultService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.buildForm();

    this.route.paramMap.subscribe(params => {
      const exerciseId = params.get('uuid');
      const resultId = params.get('resultId');

      if (!exerciseId) {
        this.router.navigate(['/exercises']);
        return;
      }

      this.exerciseId = exerciseId;
      this.resultId = resultId;

      if (resultId) {
        this.isEdit = true;
        this.load(this.exerciseId, resultId);
      } else {
        this.isEdit = false;
        this.form.patchValue({ exerciseId: this.exerciseId });
        this.found = true;
      }
      console.log(this.result);
    });
  }

  private buildForm(): void {
    this.form = this.fb.group({
      id: ['', Validators.required],
      exerciseId: ['', Validators.required],
      set: [0, [Validators.required, Validators.min(1)]],
      reps: [0, [Validators.required, Validators.min(1)]],
      weight: [0, [Validators.required, Validators.min(1)]],
      personalBest: [false, Validators.required],
    });
  }

  private load(exerciseId: string, resultId: string): void {
    this.service.getOne(resultId, exerciseId).subscribe({
      next: (result: ExerciseResult) => {
        this.result = result;
        this.original = { ...result };
        this.found = true;

        this.form.patchValue({
          id: result.id,
          exerciseId: exerciseId,
          set: result.set,
          reps: result.reps,
          weight: result.weight,
          personalBest: result.personalBest,
        });
      },
      error: () => {
        this.found = false;
      }
    });
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload: ExerciseResult = {
      ...this.form.value,
      exerciseId: this.exerciseId,
    };

    if (this.isEdit && this.resultId) {
      this.update(this.resultId, payload);
    } else {
      this.create(payload);
    }
  }

  private create(payload: ExerciseResult): void {
    this.service.create(this.exerciseId, payload).subscribe({
      next: () => {
        this.router.navigate(['/exercise', this.exerciseId, 'results']);
      }
    });
  }

  private update(resultId: string, payload: ExerciseResult): void {
    this.service.update(resultId, this.exerciseId, payload).subscribe({
      next: () => {
        this.router.navigate(['/exercise', this.exerciseId, 'results']);
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/exercise', this.exerciseId, 'results']);
  }
}
