import {Injectable} from '@angular/core';
import {ExerciseResultMapperService} from '../../exercise_result/service/exercise-result-mapper.service';
import {ExerciseRepresentation} from '../representation/exercise-representation';
import {Exercise} from '../model/exercise';

@Injectable({
  providedIn: 'root'
})
export class ExerciseMapperService {
  constructor(private resultMapper: ExerciseResultMapperService) {}

  representationToModel(representation: ExerciseRepresentation): Exercise {
    return {
      id: representation.id,
      name: representation.name,
      muscleGroup: representation.muscleGroup,
      equipment: representation.equipment,
      duration: representation.duration,
      results: this.resultMapper.representationsToModels(representation.results)
    }
  }

  modelToRepresentation(model: Exercise) {
    return {
      id: model.id,
      name: model.name,
      muscleGroup: model.muscleGroup,
      equipment: model.equipment,
      duration: model.duration,
      results: this.resultMapper.modelsToRepresentations(model.results)
    }
  }

  collectionRepresentationToModel(representation: Exercise[]): Exercise[] {
    return representation.map(item => this.representationToModel(item));
  }
}
