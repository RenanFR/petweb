package com.scrum.challenge.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.scrum.challenge.codec.QuestCodec;
import com.scrum.challenge.dao.QuestDAO;
import com.scrum.challenge.model.Quest;

@Repository("mongoQuestDAO")
public class MongoQuestDAO implements QuestDAO{
	
	private MongoClient mongoClient;
	
	private MongoDatabase mongoDatabase;
	
	private MongoCollection<Quest> mongoCollection;

	private void connect() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		QuestCodec questCodec = new QuestCodec(codec);
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(questCodec));
		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
		mongoClient = new MongoClient("localhost:27017", options);
		mongoDatabase = mongoClient.getDatabase("test");
		mongoCollection = mongoDatabase.getCollection("quests", Quest.class);
	}
	
	public Quest save(Quest quest) {
		connect();
		if (quest.getObjectId() == null) {
			ObjectId objectId = new ObjectId();
			quest.setObjectId(objectId);			
			mongoCollection.insertOne(quest);
		} else {
			mongoCollection.updateOne(Filters.eq("_id", quest.getObjectId()), new Document("$set", quest));
		}
		mongoClient.close();
		return quest;
	}


	@Override
	public List<Quest> findAll() {
		connect();
		MongoCursor<Quest> iterator = mongoCollection.find().iterator();
		List<Quest> quests = new ArrayList<Quest>();
		while (iterator.hasNext()) {
			Quest nextQuest = iterator.next();
			quests.add(nextQuest);
		}
		return quests;
	}
	
	@Override
	public Quest findById(ObjectId id) {
		connect();
		Quest quest = mongoCollection.find(Filters.eq("_id", id)).first();
		mongoClient.close();
		return quest;
	}
	
	@Override
	public void delete(Quest quest) {
		connect();
		ObjectId _id = quest.getObjectId();
		DeleteResult delete = mongoCollection.deleteOne(Filters.eq("_id", _id));
		if (delete.getDeletedCount() == 0) {
			throw new RuntimeException("NÃO FOI POSSÍVEL ELIMINAR NENHUM DOCUMENTO COM O ID " + _id);
		}
		mongoClient.close();
	}	
	
}
