import {Injectable} from '@angular/core';
import {ExerciseResultRepresentation} from '../representation/exercise-result-representation';
import {ExerciseResult} from '../model/exercise-result';
import {ExerciseResultsRepresentation} from '../representation/exercise-results-representation';

@Injectable({
  providedIn: 'root',
})
export class ExerciseResultMapperService {

  representationToModel(representation: ExerciseResultRepresentation): ExerciseResult {
    return {
      id: representation.id,
      set: representation.set,
      reps: representation.reps,
      weight: representation.weight,
      personalBest: representation.personalBest,
    };
  }

  modelToRepresentation(model: ExerciseResult): ExerciseResultRepresentation {
    return {
      id: model.id,
      set: model.set,
      reps: model.reps,
      weight: model.weight,
      personalBest: model.personalBest,
    };
  }

  representationsToModels(rep: ExerciseResultRepresentation[] | null | undefined): ExerciseResult[] {
    if (!rep || !Array.isArray(rep)) {
      return [];
    }

    return rep.map((r: ExerciseResultRepresentation) =>
      this.representationToModel(r)
    );
  }

  modelsToRepresentations(models: ExerciseResult[]): ExerciseResultsRepresentation {
    return {
      results: models.map((model: ExerciseResult) =>
        this.modelToRepresentation(model)
      ),
    };
  }

}

