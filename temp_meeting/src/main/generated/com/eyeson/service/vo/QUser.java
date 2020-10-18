package com.eyeson.service.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -173636441L;

    public static final QUser user = new QUser("user");

    public final StringPath authority = createString("authority");

    public final StringPath country = createString("country");

    public final StringPath department = createString("department");

    public final StringPath email = createString("email");

    public final StringPath enabled = createString("enabled");

    public final StringPath lang = createString("lang");

    public final StringPath legacyId = createString("legacyId");

    public final DateTimePath<java.time.LocalDateTime> mod_date = createDateTime("mod_date", java.time.LocalDateTime.class);

    public final StringPath mod_id = createString("mod_id");

    public final DateTimePath<java.time.LocalDateTime> reg_date = createDateTime("reg_date", java.time.LocalDateTime.class);

    public final StringPath reg_id = createString("reg_id");

    public final StringPath user_name = createString("user_name");

    public final StringPath user_pw = createString("user_pw");

    public final NumberPath<Long> user_seq = createNumber("user_seq", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

