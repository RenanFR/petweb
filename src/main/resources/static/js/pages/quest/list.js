var Quest = Backbone.Model.extend({

	idAttribute: "objectId",

	urlRoot: "/scrum-challenge/quest",

	validate: function(attrs){
	},

	start: function(){
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
		console.log('QuestListView');
		return this;
	},

	onDelete: function(e){
		e.preventDefault();
		this.model.set({
			objectId: $(e.target.parentElement).data('id')
		});
		this.model.destroy();
	}
});
var questListView = new QuestListView({
	  model: new Quest()
});