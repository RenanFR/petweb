package com.scrum.challenge.codec;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import com.scrum.challenge.model.Classes;

public class ClassesCodec implements CollectibleCodec<Classes> {
	
	private Codec<Document> codec;
	
	public ClassesCodec(Codec<Document> codec) {
		this.codec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Classes classValue, EncoderContext encoderContext) {
		Document classDocument = new Document();
		String description = classValue.getDescription();
		classDocument.append("description", description);
		codec.encode(writer, classDocument, encoderContext);
	}

	@Override
	public Class<Classes> getEncoderClass() {
		return Classes.class;
	}

	@Override
	public Classes decode(BsonReader reader, DecoderContext decoderContext) {
		Document document = codec.decode(reader, decoderContext);
		Classes classes = null;
		classes = new Classes(document.getString("description"));
		return classes;
	}

	@Override
	public Classes generateIdIfAbsentFromDocument(Classes classes) {
		return documentHasId(classes)? 
				classes : 
					classes.generateId();		
	}

	@Override
	public boolean documentHasId(Classes classes) {
		return classes.getObjectId() == null;
	}

	@Override
	public BsonValue getDocumentId(Classes document) {
		 if (documentHasId(document)) {
			return new BsonString(document.getObjectId().toHexString()); 
		 } else {
			 throw new IllegalStateException();
		 }
	}	

}
