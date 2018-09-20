var QuestListView = Backbone.View.extend({
	tagName: "li",

	className: "vehicle",

	events: {
		"click .delete": "onDelete",
	},

	render: function() {
		var source = $("#questList").html();
		var template = _.template(source);

		this.$el.html(template(this.model.toJSON()));
		this.$el.attr("data-color", this.model.get("color"));

		return this;
	},

	onDelete: function(){
		this.remove();
	}
});
