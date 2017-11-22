package com.book.repository

import com.book.db.Messages
import com.book.domain.Message
import com.book.utils.getMessage
import com.book.utils.insertQuery
import com.book.utils.within
import org.jetbrains.exposed.sql.*
import org.postgis.PGbox2d
import org.postgis.Point
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * @inheritDoc
 */
interface MessageRepository : CrudRepository<Message, Int>

/**
 * @inheritDoc
 */
@Repository
@Transactional
class DefaultMessageRepository : MessageRepository {

    /**
     * @inheritDoc
     */
    override fun createTable() = SchemaUtils.create(Messages)

    /**
     * @inheritDoc
     */
    override fun insert(t: Message): Message {
        t.id = Messages.insert(insertQuery(t))[Messages.id]
        return t
    }

    /**
     * @inheritDoc
     */
    override fun findAll() = Messages.selectAll().map { it.getMessage() }

    /**
     * @inheritDoc
     */
    override fun findByBoundingBox(box: PGbox2d) = Messages.select { Messages.location within box }.map { it.getMessage() }

    /**
     * @inheritDoc
     */
    override fun updateLocation(id: Int, location: Point) {
        location.srid = 4326
        Messages.update({ Messages.id eq id }) { it[Messages.location] = location }
    }

    /**
     * @inheritDoc
     */
    override fun deleteAll() = Messages.deleteAll()

}