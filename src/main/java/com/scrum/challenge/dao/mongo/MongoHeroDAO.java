package com.scrum.challenge.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.scrum.challenge.codec.HeroCodec;
import com.scrum.challenge.codec.SkillsCodec;
import com.scrum.challenge.dao.HeroDAO;
import com.scrum.challenge.model.Hero;

@Repository("mongoHeroDAO")
public class MongoHeroDAO implements HeroDAO	{
	
	private MongoClient mongoClient;
	
	private MongoDatabase mongoDatabase;
	
	private MongoCollection<Hero> mongoCollection;

	private void connect() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		HeroCodec heroCodec = new HeroCodec(codec);
		SkillsCodec skillsCodec = new SkillsCodec(codec);
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(heroCodec, skillsCodec));
		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
		mongoClient = new MongoClient("localhost:27017", options);
		mongoDatabase = mongoClient.getDatabase("test");
		mongoCollection = mongoDatabase.getCollection("heroes", Hero.class);
	}
	
	public Hero save(Hero hero) {
		connect();
		if (hero.getObjectId() == null) {
			hero.generateId();
			mongoCollection.insertOne(hero);
		} else {
			mongoCollection.updateOne(Filters.eq("_id", hero.getObjectId()), new Document("$set", hero));
		}
		mongoClient.close();
		return hero;
	}


	@Override
	public List<Hero> findAll() {
		connect();
		MongoCursor<Hero> iterator = mongoCollection.find().iterator();
		List<Hero> heroes = new ArrayList<Hero>();
		while (iterator.hasNext()) {
			Hero nextHero = iterator.next();
			heroes.add(nextHero);
		}
		return heroes;
	}
	
	@Override
	public Hero findById(ObjectId id) {
		connect();
		Hero hero = mongoCollection.find(Filters.eq("_id", id)).first();
		mongoClient.close();
		return hero;
	}
	
	@Override
	public void delete(Hero hero) {
		connect();
		ObjectId _id = hero.getObjectId();
		DeleteResult delete = mongoCollection.deleteOne(Filters.eq("_id", _id));
		if (delete.getDeletedCount() == 0) {
			throw new RuntimeException("NÃO FOI POSSÍVEL ELIMINAR NENHUM DOCUMENTO COM O ID " + _id);
		}
		mongoClient.close();
	}

	@Override
	public UserDetails findByName(String username) {
		connect();
		Hero hero = mongoCollection.find(Filters.eq("name", username)).first();
		return hero;
	}	
	
}
