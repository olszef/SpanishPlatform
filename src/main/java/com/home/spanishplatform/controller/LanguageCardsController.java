package com.home.spanishplatform.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.home.spanishplatform.entity.LanguageCard;
import com.home.spanishplatform.security.MyUserPrincipal;
import com.home.spanishplatform.service.LanguageCardService;

@Controller
@RequestMapping("/custom/cards")
public class LanguageCardsController {
		
    @Autowired
    private LanguageCardService languageCardService;
	
	@GetMapping("")
	public String translationPage(Model theModel) {
		return "custom/cards_menu";
	}

    @GetMapping("/manage")
    public String showPrimaryManageForm(LanguageCard languageCard, Model model) {
    	model.addAttribute("cardsGroups", languageCardService.findAllGroups(getLoggedUserId()));
        return "custom/cards_manage";
    }
	
    @GetMapping("/manage/show")
    public String showManageCards(@ModelAttribute("groupId") Integer groupId, Model model) {
    	model.addAttribute("cardsGroups", languageCardService.findAllGroups(getLoggedUserId()));
        model.addAttribute("existingLanguageCards", languageCardService.findLanguageCardsByGroupId(groupId));
        
        if (!model.containsAttribute("languageCard")) 
        	model.addAttribute("languageCard", new LanguageCard());
        
        if (!model.containsAttribute("modifiedLanguageCard")) 
        	model.addAttribute("modifiedLanguageCard", new LanguageCard());
        
        return "custom/cards_manage";
    }

    @PostMapping("/manage/changegroup")
    public String changeGroup(@Valid LanguageCard languageCard, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam(value = "action", required = true) String formAction, Model model) {
    	
    	if (formAction == null || languageCard.getCardsGroup() == null || ("remove group".toLowerCase().equals(formAction) && languageCard.getCardsGroup().getGroupId() == 0)) {
    		model.addAttribute("returnTitle", "Error when removing the group");
    		model.addAttribute("returnMessage", "Please try again");
    		return "shared/return_message";
    	} 
    	
    	if ("add card".toLowerCase().equals(formAction) && result.hasErrors()) {
    		addRedirectAttributesWhenError(redirectAttributes, result, languageCard, "languageCard");
    		return "redirect:/custom/cards/manage/show";
    	}   	
    	
    	try {
	    	if ("add card".toLowerCase().equals(formAction)) {
	    		languageCard = languageCardService.saveNewCardsGroupAndLanguageCard(languageCard, getLoggedUserId());;
	    	} else if ("remove group".toLowerCase().equals(formAction)) {
	    		languageCardService.removeCardsGroup(languageCard.getCardsGroup());
	    	}
    	} catch (Exception e) {
    		model.addAttribute("returnTitle", "Error when trying to change group or add language card.");
    		model.addAttribute("returnMessage", "Please try again");
    		return "shared/return_message";
    	}
    	
    	redirectAttributes.addFlashAttribute("groupId", languageCard.getCardsGroup().getGroupId());
		return "redirect:/custom/cards/manage/show";
    }
    
    @PostMapping("/manage/changecard")
    public String changeCard(@Valid LanguageCard modifiedLanguageCard, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam(value = "action", required = true) String formAction, Model model) {
    	if (formAction == null || modifiedLanguageCard.getCardsGroup() == null || modifiedLanguageCard.getCardsGroup().getGroupId() == 0 || modifiedLanguageCard.getCardId() == 0) {
    		model.addAttribute("returnTitle", "Error when modifying the card");
    		model.addAttribute("returnMessage", "Please try again");
    		return "shared/return_message";
    	} 
    	
    	if ("edit".toLowerCase().equals(formAction) && result.hasErrors()) {
			addRedirectAttributesWhenError(redirectAttributes, result, modifiedLanguageCard, "modifiedLanguageCard");
			return "redirect:/custom/cards/manage/show";
		}
		
		try {
	    	if ("edit".toLowerCase().equals(formAction)) {
	        	languageCardService.saveLanguageCard(modifiedLanguageCard);
	        } else if ("remove".toLowerCase().equals(formAction)) {
	        	languageCardService.removeLanguageCard(modifiedLanguageCard);
	        }
		} catch (Exception e) {
			model.addAttribute("returnTitle", "Error when trying to edit or remove language card.");
    		return "shared/return_message";
		}
        
		redirectAttributes.addFlashAttribute("groupId", modifiedLanguageCard.getCardsGroup().getGroupId());
		return "redirect:/custom/cards/manage/show";
    }
    
    @GetMapping("/train")
    public String showPrimaryTrainForm(LanguageCard languageCard, Model model) {
    	model.addAttribute("cardsGroups", languageCardService.findAllGroups(getLoggedUserId()));
    	model.addAttribute("cardsTrainingOption", "spanish");
        return "custom/cards_train";
    }
    
    @GetMapping("/train/show")
    public String showTrainCards(@ModelAttribute("groupId") Integer groupId, @ModelAttribute("cardsTrainingOption") String cardsTrainingOption, Model model) {
    	model.addAttribute("cardsGroups", languageCardService.findAllGroups(getLoggedUserId()));
    	model.addAttribute("cardsTrainingOption", cardsTrainingOption);
        model.addAttribute("existingLanguageCards", languageCardService.findLanguageCardsByGroupId(groupId));
        
        return "custom/cards_train";
    }    
    
	private int getLoggedUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ((MyUserPrincipal)authentication.getPrincipal()).getUserId();		
	}
	
	private void addRedirectAttributesWhenError(RedirectAttributes redirectAttributes, BindingResult result, LanguageCard languageCard, String cardAttributeName) {
		redirectAttributes.addFlashAttribute(cardAttributeName, languageCard);
		redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + cardAttributeName, result);
		redirectAttributes.addFlashAttribute("groupId", languageCard.getCardsGroup().getGroupId());
		redirectAttributes.addFlashAttribute("errorsCardId", languageCard.getCardId());
	}
}
