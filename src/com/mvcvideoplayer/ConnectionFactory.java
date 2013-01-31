package com.mvcvideoplayer;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {

	public Connection getConnection() throws SQLException, Exception {

		System.out.println("conectando no banco de dados (Pool de conexoes) ...");

        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc//MVCVideoPlayer");

        return ds.getConnection();

	}	
	
}
