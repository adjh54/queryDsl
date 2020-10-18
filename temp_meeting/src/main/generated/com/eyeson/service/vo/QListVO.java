package com.eyeson.service.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QListVO is a Querydsl query type for ListVO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QListVO extends EntityPathBase<ListVO> {

    private static final long serialVersionUID = 372628915L;

    public static final QListVO listVO = new QListVO("listVO");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final ListPath<Object, SimplePath<Object>> list = this.<Object, SimplePath<Object>>createList("list", Object.class, SimplePath.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QListVO(String variable) {
        super(ListVO.class, forVariable(variable));
    }

    public QListVO(Path<? extends ListVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QListVO(PathMetadata metadata) {
        super(ListVO.class, metadata);
    }

}

