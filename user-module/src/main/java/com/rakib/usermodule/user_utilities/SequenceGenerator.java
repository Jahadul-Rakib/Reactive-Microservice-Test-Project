package com.rakib.usermodule.user_utilities;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGenerator {
    private final ReactiveMongoTemplate mongoOperations;

    public SequenceGenerator(ReactiveMongoTemplate mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
    public long generateSequence(String sequenceName) {
        Mono<DatabaseSequence> counter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return Objects.requireNonNull(counter.blockOptional()).get().getSequence();

    }
}
