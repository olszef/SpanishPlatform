package com.home.spanishplatform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cards_group")
public class CardsGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "group_name")
    @Size(min=1, max=50, message="Name must be between 1 and 50 characters")
    @NotNull(message="Name not filled")
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "cardsGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LanguageCard> languageCards = new HashSet<>();

}
