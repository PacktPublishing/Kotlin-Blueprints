package com.book.utils

import com.book.db.Messages
import com.book.db.PointColumnType
import com.book.db.WithinOp
import com.book.domain.Message
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.postgis.PGbox2d
import org.postgis.Point

/**
 * Builds the insert query for the specified message
 */
fun insertQuery(m: Message): Messages.(UpdateBuilder<*>) -> Unit = {
    if (m.id != null) it[id] = m.id
    it[content] = m.content
    it[location] = m.location
}

/**
 * Create the message object from Result row
 * @return message
 */
fun ResultRow.getMessage() =
        Message(this[Messages.content], this[Messages.location], this[Messages.id])

/**
 * Extension function to get point column type from the table
 */
fun Table.point(name: String, srid: Int = 4326): Column<Point>
        = registerColumn(name, PointColumnType())

/**
 * To check if the message location is within the specified box area.
 * Returns true if yes else false
 */
infix fun ExpressionWithColumnType<*>.within(box: PGbox2d): Op<Boolean>
        = WithinOp(this, box)