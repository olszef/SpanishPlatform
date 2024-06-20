$(document).ready(function() {
	
	var menuTabsAll = $('#menu li span');
	var menuItems = $("#menu > ul > li > ul > li > a");
	
	menuItems.on("click", function() {
		
		menuTab = $(this).parent().parent().prev();
		
		menuTabsAll.each(function() {
			localStorage.removeItem(menuTab.attr("id"));
		})
		
		if (menuTab.hasClass("active")) {
			localStorage.setItem(menuTab.attr("id"), "active");
		}
    });
	
	menuTabsAll.each(function() {
		if (localStorage.getItem(this.id) != null) {
			$(this).addClass("active");
			localStorage.removeItem(this.id);
		}
	});
});