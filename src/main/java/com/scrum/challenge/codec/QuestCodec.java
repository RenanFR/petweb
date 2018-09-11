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
		String description = quest.getDescription();
		String title = quest.getTitle();
		LocalDateTime beginDate = quest.getBeginDate();
		LocalDateTime expectedEndDate = quest.getExpectedEndDate();
		LocalDateTime endDate = quest.getEndDate();
		String nameHero = quest.getAssignedHero().getName();
		ObjectId objectId = quest.getObjectId();
		Long idSprint = quest.getSprint().getId();
		Document questDocument = new Document();
		questDocument.append("_id", objectId);
		questDocument.append("title", title);
		questDocument.append("description", description);
		questDocument.append("nameHero", nameHero);
		questDocument.append("beginDate", beginDate);
		questDocument.append("expectedEndDate", expectedEndDate);
		questDocument.append("endDate", endDate);
		questDocument.append("idSprint", idSprint);
		codec.encode(bsonWriter, questDocument, context);
	}

	@Override
	public Class<Quest> getEncoderClass() {
		return Quest.class;
	}

	@Override
	public Quest decode(BsonReader bsonReader, DecoderContext decoderContext) {
		Document document = codec.decode(bsonReader, decoderContext);
		Quest quest = new Quest(document.getObjectId("_id"), 
				document.getString("title"), 
				document.getString("description"),
				LocalDateTime.parse(document.getString("beginDate")),
				LocalDateTime.parse(document.getString("expectedEndDate")),
				LocalDateTime.parse(document.getString("endDate")),
				new Sprint(new ObjectId(document.getString("idSprint"))), 
				new Hero(document.getString("nameHero"))
		);
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
