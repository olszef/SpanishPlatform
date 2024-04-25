function submitFormOnElementChange(element) {
	element.closest("form").submit();
}

function showNextCard(direction) {
	var activeCardDiv = $("#cards-container div").not(".removed-element");
	activeCardDiv.addClass("removed-element");
	
	var nextCard;
	
	if (direction == 'left') {
		nextCard = activeCardDiv.prev();
	} else {
		nextCard = activeCardDiv.next();
	}
	
	if (nextCard != null) {
		nextCard.removeClass("removed-element");
		
		if (nextCard.is(':first-child')) {
			$("#cards-left-arrow").addClass("removed-element");
		} else {
			$("#cards-left-arrow").removeClass("removed-element");
		}
		
		if (nextCard.is(':last-child')) {
			$("#cards-right-arrow").addClass("removed-element");
		} else {
			$("#cards-right-arrow").removeClass("removed-element");
		}
	}
	
}

function flipCard(element) {
	element.children.each(function() {
		if ($(this).is(":hidden")) {
			$(this).attr("hidden", true);
		} else {
			$(this).removeAttr("hidden");
		}
	});
}

function toggleHidden(clickedElement) {
    // Get all child nodes of the clicked element
    let childNodes = clickedElement.querySelectorAll('p, li:not(.card-box-subscript)');
    
    // Iterate through each child node
    childNodes.forEach(node => {
        // Check if the node is an Element (to ignore text nodes and others)
        if (node.nodeType === Node.ELEMENT_NODE) {
            // Toggle the 'hidden' attribute based on its current state
            if (node.classList.contains('removed-element')) {
                node.classList.remove('removed-element');
            } else {
                node.classList.add('removed-element');
            }
        }
    });
}