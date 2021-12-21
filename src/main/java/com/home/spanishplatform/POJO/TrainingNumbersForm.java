package com.home.spanishplatform.POJO;

import java.util.ArrayList;
import java.util.List;

public class TrainingNumbersForm {
	private List<TrainingNumber> trainingNumbers = new ArrayList<TrainingNumber>();

	public void addNumber(TrainingNumber trainingNumber) {
        this.trainingNumbers.add(trainingNumber);
    }

	public List<TrainingNumber> getTrainingNumbers() {
		return trainingNumbers;
	}

	public void setTrainingNumbers(List<TrainingNumber> trainingNumbers) {
		this.trainingNumbers = trainingNumbers;
	}
	
	public void setTrainingNumbersWithoutNames(List<Integer> trainingNumbers) {
		for (int trainingNumber : trainingNumbers) {
			this.trainingNumbers.add(new TrainingNumber(trainingNumber));
		}
	}
}
