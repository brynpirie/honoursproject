package Connector ;

import org.neo4j.driver.v1.*;
import java.sql.*;

import org.neo4j.driver.v1.Driver;
import org.neo4j.jdbc.*;
import org.neo4j.*;
import java.util.*;

//import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    Driver driver = GraphDatabase.driver(
            "bolt://localhost", AuthTokens.basic( "neo4j", "neo4j")
    );
    Session session = driver.session();

    session.run( "CREATE (a:Person {name:'Arthur', title:'King;})");

    StatementResult result = session.run(
            "MATCH (a:Person)" +
                    "WHERE a.name = 'Arthur'" +
                    "RETURN a.name AS name, a.title AS title"
    );
    while (result.hasNext() ){
        Record record = result.next();
        System.out.println( record.get( "title").asString() + " " + record.get("name").asString() );
    }

    session.close();
    driver.close();

    /**private String password;

    {
        password = "neo4j";
    }

    public static void initConn() {

        try {
            // Connecting
            try (Connection con = DriverManager.getConnection("jdbc:neo4j:bolt://localhost:7687", "neo4j", "neo4j")) {
                System.out.println("Attempting to initiate Neo4J connection...");
                // * Query
                String query = "MATCH (n) RETURN (n)";

                try (PreparedStatement stmt = con.prepareStatement(query)) {

                    try (ResultSet rs = stmt.execute()) {
                        while (rs.next()) {
                            System.out.println(rs.getString("f.name") + " is " + rs.getInt("f.age"));
                        }
                    }
                }
            } catch (Exception e) {
                SQLException.getMessage();

            }
        }
    } //END initConn() **/

} //END Connector