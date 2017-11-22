package com.book.db

import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.QueryBuilder
import org.postgis.PGbox2d

/**
 * Special type to represent the box and if location of message
 * is inside the specified box
 */
class WithinOp(private val expr1: Expression<*>, private val box: PGbox2d) : Op<Boolean>() {
    override fun toSQL(queryBuilder: QueryBuilder) =
            "${expr1.toSQL(queryBuilder)} && ST_MakeEnvelope(${box.llb.x}, ${box.llb.y}, ${box.urt.x}, ${box.urt.y}, 4326)"
}