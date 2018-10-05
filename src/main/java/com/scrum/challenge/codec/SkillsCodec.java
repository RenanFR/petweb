package com.scrum.challenge.codec;

import org.bson.BsonReader;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import com.scrum.challenge.model.Skills;

public class SkillsCodec implements CollectibleCodec<Skills>{

	private Codec<Document> codec;
	
	public SkillsCodec(Codec<Document> codec) {
		this.codec = codec;
	}	
	
	@Override
	public void encode(BsonWriter writer, Skills skills, EncoderContext encoderContext) {
		Document skillsDocument = new Document();
		String description = skills.getDescription();
		String color = skills.getColor();
		String name = skills.name();
		skillsDocument.append("description", description);
		skillsDocument.append("color", color);
		skillsDocument.append("name", name);
		codec.encode(writer, skillsDocument, encoderContext);
	}

	@Override
	public Class<Skills> getEncoderClass() {
		return Skills.class;
	}

	@Override
	public Skills decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = codec.decode(reader, decoderContext);
		String name = document.getString("name");
		return Skills.valueOf(name);
	}

	@Override
	public Skills generateIdIfAbsentFromDocument(Skills document) {
		return null;
	}

	@Override
	public boolean documentHasId(Skills document) {
		return document.getDescription() == null;
	}

	@Override
	public BsonValue getDocumentId(Skills document) {
		return null;
	}

}
