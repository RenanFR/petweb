var Quest = Backbone.Model.extend({
	
	idAttribute: "title",

});

var Quests = Backbone.Collection.extend({
	
	url: '/scrum-challenge/quest/list',
	
	model: Quest
	
});

var QuestView = Backbone.View.extend({
	
	  tagName: 'td',
	  
	  events: {
		"click .delete": "onDelete"
	  },	  
	  
	  render: function() {
		var source = $("#questTemplate").html();
		var template = _.template(source);
		this.$el.html(template(this.model.toJSON()));
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

var QuestListView = Backbone.View.extend({
	
	tagName: "tr",
	
	render: function(){
		this.collection.each(function(quest){
			var questView = new QuestView({ model: quest });
			this.$el.append(questView.render().$el);
		}, this);
		return this;
	}

});

var quests = new Quests([
	new Quest({ title: "t", description: "d", beginDate: "b", expectedEndDate: "e", endDate: "e"    })
]);
//var quests = new Quests();
//
//quests.fetch();

var questListView = new QuestListView({ collection: quests });
$("tbody").html(questListView.render().$el);