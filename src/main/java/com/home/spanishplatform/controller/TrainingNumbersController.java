package com.home.spanishplatform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.spanishplatform.POJO.TrainingNumber;
import com.home.spanishplatform.POJO.TrainingNumbersForm;
import com.home.spanishplatform.entity.NumberName;
import com.home.spanishplatform.service.NumberNameService;
import com.home.spanishplatform.utils.NumberUtils;

@Controller
@RequestMapping("/training/numbers")
public class TrainingNumbersController {

	@Autowired
	private NumberNameService numberNameService;
	
	private String trainingStatus;
	private String errorMessage;
	
	@GetMapping("")
	public String trainNumbers(Model theModel) {
		trainingStatus = "ready";
		theModel.addAttribute("trainingStatus", trainingStatus);
		return "training/training_numbers";
	}

	@GetMapping(value="/getList", params="action=draw")
	public String drawNumbers(@RequestParam(defaultValue = "0") String rangeFrom, @RequestParam(defaultValue = "9999") String rangeTo, Model theModel) {
		TrainingNumbersForm trainingNumbersForm = new TrainingNumbersForm();
		
		if (NumberUtils.isRangeNumericAndPositive(rangeFrom, rangeTo)) {
			trainingStatus = "OK";
			trainingNumbersForm.setTrainingNumbersWithoutNames(NumberUtils.drawDistinctRandomNumbersInRange(Integer.parseInt(rangeFrom), Integer.parseInt(rangeTo)));
			theModel.addAttribute("trainingNumbersForm", trainingNumbersForm);
		} else {
			trainingStatus = "error";
			errorMessage = "The given range is incorrect...";
			theModel.addAttribute("errorMessage", errorMessage);
		}
		
		theModel.addAttribute("rangeFrom", rangeFrom);
		theModel.addAttribute("rangeTo", rangeTo);
		theModel.addAttribute("trainingStatus", trainingStatus);		
		return "training/training_numbers";
	}
	
	@GetMapping(value="/getList", params="action=range")
	public String rangeNumbers(@RequestParam(defaultValue = "1") String rangeFrom, @RequestParam(defaultValue = "10") String rangeTo, Model theModel) {
		TrainingNumbersForm trainingNumbersForm = new TrainingNumbersForm();
		
		if (NumberUtils.isRangeNumericAndPositive(rangeFrom, rangeTo)) {
			trainingStatus = "OK";
			trainingNumbersForm.setTrainingNumbersWithoutNames(NumberUtils.drawDistinctSequentialNumbersInRange(Integer.parseInt(rangeFrom), Integer.parseInt(rangeTo)));
			theModel.addAttribute("trainingNumbersForm", trainingNumbersForm);
		} else {
			trainingStatus = "error";
			errorMessage = "The given range is incorrect...";
			theModel.addAttribute("errorMessage", errorMessage);
		}
		
		theModel.addAttribute("rangeFrom", rangeFrom);
		theModel.addAttribute("rangeTo", rangeTo);
		theModel.addAttribute("trainingStatus", trainingStatus);
		return "training/training_numbers";
	}
	
	@GetMapping("/checkAnswers")
	public String checkAnswers(@ModelAttribute TrainingNumbersForm trainingNumbersForm, Model theModel) {
		trainingStatus = "OK";
		
		List<Integer> numbersIds = new ArrayList<Integer>();
		for (TrainingNumber trainingNumber : trainingNumbersForm.getTrainingNumbers()) {
			trainingNumber.setNumberCorrectlyNamed(false);
			
			if (trainingNumber.getNumberText().isBlank()) {
				trainingNumber.setNumberChecked(false);
			} else {
				trainingNumber.setNumberChecked(true);
				numbersIds.add(trainingNumber.getNumberId());
			}
		}
		
		try {
			List<NumberName> givenNumbersNames = numberNameService.findAllGivenNumbersById(numbersIds);
			
			for (TrainingNumber trainingNumber : trainingNumbersForm.getTrainingNumbers()) {
				if (trainingNumber.isNumberChecked()) {
					if (givenNumbersNames.contains(trainingNumber.getNumberName())) {
						trainingNumber.setNumberCorrectlyNamed(true);
					}					
				} 
			}
		} catch(Exception e){
			trainingStatus = "error";
			errorMessage = "Error.. Numbers couldn't be checked...";
			theModel.addAttribute("errorMessage", errorMessage);
		}		
		
		theModel.addAttribute("trainingNumbersForm", trainingNumbersForm);
		theModel.addAttribute("trainingStatus", trainingStatus);
		return "training/training_numbers";
	}



}
