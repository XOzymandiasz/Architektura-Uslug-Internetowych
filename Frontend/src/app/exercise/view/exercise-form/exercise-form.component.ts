import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {ExerciseService} from '../../service/exercise.service';
import {Exercise} from '../../model/exercise';


@Component({
  selector: 'app-exercise-list',
  templateUrl: './exercise-form.component.html',
  styleUrls: ['./exercise-form.component.css'],
  standalone: false
})
export class ExerciseFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;

  uuid: string | undefined;
  exercise: Exercise | undefined;
  original: Exercise | undefined;

  constructor(
    private fb: FormBuilder,
    private service: ExerciseService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.buildForm();

    this.route.paramMap.subscribe(params => {
      const uuid = params.get('uuid');

      if (uuid) {
        this.isEdit = true;
        this.uuid = uuid;
        this.load(uuid);
      } else {
        this.isEdit = false;
      }
    });
  }

  private buildForm(): void {
    this.form = this.fb.group({
      id: ['', Validators.required],
      name: ['', Validators.required],
      muscleGroup: ['', Validators.required],
      equipment: ['', Validators.required],
      duration: ['', Validators.required],
    })
  }

  private load(uuid: string): void {
    this.loading = true;

    this.service.getOne(uuid).subscribe({
      next: (exercise: Exercise) => {
        this.exercise = exercise;
        this.original = { ...exercise };

        this.form.patchValue({
          id: exercise.id,
          name: exercise.name,
          muscleGroup: exercise.muscleGroup,
          equipment: exercise.equipment,
          duration: exercise.duration,
        });

        this.loading = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = this.form.value;

    if (this.isEdit && this.uuid) {
      this.update(this.uuid, payload);
    } else {
      this.create(payload);
    }
  }

  private create(payload: any): void {
    this.service.create(payload).subscribe({
      next: () => {
        this.router.navigate(['/exercises']);
      }
    });
  }

  private update(uuid: string, payload: any): void {
    this.service.update(uuid, payload).subscribe({
      next: () => {
        this.router.navigate(['/exercises']);
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/exercises']);
  }
}
