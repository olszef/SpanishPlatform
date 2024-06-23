package com.home.spanishplatform.POJO;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingNumbersForm {
	private List<TrainingNumber> trainingNumbers = new ArrayList<TrainingNumber>();

	public void addNumber(TrainingNumber trainingNumber) {
        this.trainingNumbers.add(trainingNumber);
    }
	
	public void setTrainingNumbersWithoutNames(List<Integer> trainingNumbers) {
		for (int trainingNumber : trainingNumbers) {
			this.trainingNumbers.add(new TrainingNumber(trainingNumber));
		}
	}
}
