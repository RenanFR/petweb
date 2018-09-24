var Quest = Backbone.Model.extend({

	idAttribute: "objectId",

	urlRoot: "/scrum-challenge/quest",

	validate: function(attrs){
	}
	
});
var QuestListView = Backbone.View.extend({
	
    el: $('#questList'),

	events: {
		"click .delete": "onDelete"
	},

    initialize: function() {
        this.render();
    },	

	render: function() {
		return this;
	},

	onDelete: function(e){
		e.preventDefault();
		this.model.set({
			objectId: $(e.target.parentElement).data('id')
		});
		$("a[data-id='" + $(e.target.parentElement)
				.data('id') +"']")
				.prop('disabled', true)
				.css("color","red");
		this.model.destroy();
	}
});
var questListView = new QuestListView({
	  model: new Quest()
});