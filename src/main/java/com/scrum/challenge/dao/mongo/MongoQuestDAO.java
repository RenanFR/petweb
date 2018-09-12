package com.scrum.challenge.dao.mongo;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.scrum.challenge.codec.QuestCodec;
import com.scrum.challenge.dao.QuestDAO;
import com.scrum.challenge.model.Quest;

@Repository("mongoQuestDAO")
public class MongoQuestDAO implements QuestDAO{
	
	public Quest save(Quest quest) {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		QuestCodec questCodec = new QuestCodec(codec);
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(questCodec));
		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
		MongoClient mongoClient = new MongoClient("localhost:27017", options);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		MongoCollection<Quest> mongoCollection = mongoDatabase.getCollection("quests", Quest.class);
		mongoCollection.insertOne(quest);
		mongoClient.close();
		return quest;
	}
}
