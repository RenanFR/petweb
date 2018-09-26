package com.scrum.challenge.codec;

import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.scrum.challenge.model.Hero;

public class HeroCodec implements CollectibleCodec<Hero>	{
	
	private Codec<Document> codec;
	
	public HeroCodec(Codec<Document> codec) {
		this.codec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Hero hero, EncoderContext encoderContext) {
		Document heroDocument = new Document();
		ObjectId objectId = hero.getObjectId();
		heroDocument.append("_id", objectId);
		codec.encode(writer, heroDocument, encoderContext);
	}

	@Override
	public Class<Hero> getEncoderClass() {
		return Hero.class;
	}

	@Override
	public Hero decode(BsonReader reader, DecoderContext decoderContext) {
		return null;
	}

	@Override
	public Hero generateIdIfAbsentFromDocument(Hero document) {
		return null;
	}

	@Override
	public boolean documentHasId(Hero hero) {
		return hero.getObjectId() == null;
	}

	@Override
	public BsonValue getDocumentId(Hero document) {
		return null;
	}	

}
