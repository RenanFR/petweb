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
import com.scrum.challenge.codec.ClassesCodec;
import com.scrum.challenge.dao.ClassesDAO;
import com.scrum.challenge.model.Classes;

@Repository("mongoClassesDAO")
public class MongoClassesDAO implements ClassesDAO	{
	
	private MongoClient mongoClient;
	
	private MongoDatabase mongoDatabase;
	
	private MongoCollection<Classes> mongoCollection;

	private void connect() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		ClassesCodec classesCodec = new ClassesCodec(codec);
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(classesCodec));
		MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
		mongoClient = new MongoClient("localhost:27017", options);
		mongoDatabase = mongoClient.getDatabase("test");
		mongoCollection = mongoDatabase.getCollection("classes", Classes.class);
	}
	
	public Classes save(Classes classes) {
		connect();
		if (classes.getObjectId() == null) {
			classes.generateId();
			mongoCollection.insertOne(classes);
		} else {
			mongoCollection.updateOne(Filters.eq("_id", classes.getObjectId()), new Document("$set", classes));
		}
		mongoClient.close();
		return classes;
	}


	@Override
	public List<Classes> findAll() {
		connect();
		MongoCursor<Classes> iterator = mongoCollection.find().iterator();
		List<Classes> classes = new ArrayList<Classes>();
		while (iterator.hasNext()) {
			Classes nextClasses = iterator.next();
			classes.add(nextClasses);
		}
		return classes;
	}
	
	@Override
	public Classes findById(ObjectId id) {
		connect();
		Classes classes = mongoCollection.find(Filters.eq("_id", id)).first();
		mongoClient.close();
		return classes;		
	}
	
	@Override
	public void delete(Classes classes) {
		connect();
		ObjectId _id = classes.getObjectId();
		DeleteResult delete = mongoCollection.deleteOne(Filters.eq("_id", _id));
		if (delete.getDeletedCount() == 0) {
			throw new RuntimeException("NÃO FOI POSSÍVEL ELIMINAR NENHUM DOCUMENTO COM O ID " + _id);
		}
		mongoClient.close();
	}	
	
}
