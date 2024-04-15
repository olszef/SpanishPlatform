package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "language_card")
public class LanguageCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;

    @Column(name = "spanish_word")
    @Size(min=1, max=50, message="Spanish text must be between 1 and 50 characters")
    @NotNull(message="Text not found")
    private String spanishWord;

    @Column(name = "translation")
    @Size(min=1, max=50, message="Translation text must be between 1 and 50 characters")
    @NotNull(message="Text not found")
    private String translation;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    @NotNull(message="Group not present")
    private CardsGroup cardsGroup;
    
}
