package sql

import java.sql.Connection

class Query {

    lateinit var conn: Connection

    fun Query(conn: Connection) {
        this.conn = conn
    }

    fun id_headers(): Int {
        var id = 0
        try {
            val sql = "SELECT max(id_headers) as id_headers from headers;"
            val st = conn.prepareStatement(sql)
            val rs = st.executeQuery()
            while (rs.next()) {
                id = rs.getString("id_headers").toInt()
            }
        } catch (e: Exception) {
            println("ID : $e")
        }
        return id
    }
}