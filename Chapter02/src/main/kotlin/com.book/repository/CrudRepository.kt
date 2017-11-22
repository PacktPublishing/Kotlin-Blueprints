package com.book.repository

import org.postgis.PGbox2d
import org.postgis.Point

/**
 * Basic CRUD operations related to Geospatial
 */
interface CrudRepository<T, K> {
    /**
     * Creates the table
     */
    fun createTable()

    /**
     * Insert the item
     */
    fun insert(t: T): T

    /**
     * Get list of all the items
     */
    fun findAll(): Iterable<T>

    /**
     * Delete all the items
     */
    fun deleteAll(): Int

    /**
     * Get list of items in the specified box
     */
    fun findByBoundingBox(box: PGbox2d): Iterable<T>

    /**
     * Update the location of the user
     */
    fun updateLocation(id: K, location: Point)
}
