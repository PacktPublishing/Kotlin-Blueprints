package com.book.db

import org.jetbrains.exposed.sql.ColumnType
import org.postgis.PGgeometry
import org.postgis.Point

/**
 * It represents the point column type for Exposed
 */
class PointColumnType(private val srid: Int = 4326) : ColumnType() {
    override fun sqlType() = "GEOMETRY(Point, $srid)"
    override fun valueFromDB(value: Any) = if (value is PGgeometry) value.geometry else value
    override fun notNullValueToDB(value: Any): Any {
        if (value is Point) {
            if (value.srid == Point.UNKNOWN_SRID) value.srid = srid
            return PGgeometry(value)
        }
        return value
    }
}