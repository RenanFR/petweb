package com.scrum.challenge.codec;

import java.time.LocalDateTime;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.scrum.challenge.model.Hero;
import com.scrum.challenge.model.Quest;
import com.scrum.challenge.model.Sprint;

public class QuestCodec implements CollectibleCodec<Quest> {
	
	private Codec<Document> codec;
	
	public QuestCodec(Codec<Document> codec) {
		this.codec = codec;
	}

	@Override
	public void encode(BsonWriter bsonWriter, Quest quest, EncoderContext context) {
		Document questDocument = new Document();
		String description = quest.getDescription();
		String title = quest.getTitle();
		LocalDateTime beginDate = quest.getBeginDate();
		LocalDateTime expectedEndDate = quest.getExpectedEndDate();
		LocalDateTime endDate = quest.getEndDate();
		Hero assignedHero = quest.getAssignedHero();
		if (assignedHero != null) {
			String nameHero = assignedHero.getName();
			ObjectId heroObjectId = assignedHero.getObjectId();
			Document heroDocument = new Document("name", nameHero).append("objectId", heroObjectId);
			questDocument.append("assignedHero", heroDocument);
		}
		ObjectId objectId = quest.getObjectId();
		if (quest.getSprint() != null) {
			Long idSprint = quest.getSprint().getId();
			questDocument.append("idSprint", idSprint);
		}
		questDocument.append("_id", objectId);
		questDocument.append("title", title);
		questDocument.append("description", description);
		questDocument.append("beginDate", beginDate.toString());
		questDocument.append("expectedEndDate", expectedEndDate.toString());
		questDocument.append("endDate", endDate.toString());
		codec.encode(bsonWriter, questDocument, context);
	}

	@Override
	public Class<Quest> getEncoderClass() {
		return Quest.class;
	}

	@Override
	public Quest decode(BsonReader bsonReader, DecoderContext decoderContext) {
		Document document = codec.decode(bsonReader, decoderContext);
		Sprint sprint = null;
		Hero assignedHero = null;
		if (document.getString("idSprint") != null) {
			sprint = new Sprint(new ObjectId(document.getString("idSprint")));
		}
		Document documentHero = document.get("assignedHero", Document.class);
		if (documentHero != null) {
			ObjectId objectId = documentHero.getObjectId("objectId");
			String nameHero = documentHero.getString("name");
			assignedHero = new Hero(objectId, nameHero);
		}
		Quest quest = new Quest(document.getObjectId("_id"), 
				document.getString("title"), 
				document.getString("description"),
				LocalDateTime.parse(document.getString("beginDate")),
				LocalDateTime.parse(document.getString("expectedEndDate")),
				LocalDateTime.parse(document.getString("endDate")),
				sprint,
				assignedHero);
		return quest;
	}

	@Override
	public boolean documentHasId(Quest quest) {
		return quest.getObjectId() == null;
	}

	@Override
	public Quest generateIdIfAbsentFromDocument(Quest quest) {
		return documentHasId(quest)? 
				quest : 
					quest.generateId();
	}

	@Override
	public BsonValue getDocumentId(Quest quest) {
		 if (documentHasId(quest)) {
			return new BsonString(quest.getObjectId().toHexString()); 
		 } else {
			 throw new IllegalStateException();
		 }
	}

}
