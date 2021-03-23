package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Content;

public class ContentData {
	
	public boolean selectAllContent(){

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;

		Content.list.clear();
		
		boolean ret = false;
		
		try {
			connection = Database.getConnection();
			String query = "SELECT idContent, name, image, idCategory, type, price FROM content;";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Content content = new Content();
				content.setIdContent(resultSet.getInt(1));
				content.setName(resultSet.getString(2));
				content.setImage(resultSet.getString(3));
				content.setIdCategory(resultSet.getInt(4));
				content.setType(resultSet.getInt(5));
				content.setPrice(resultSet.getDouble(6));
				Content.list.add(content);
				ret = true;
			}

		} catch (SQLException exception) {
			exception.printStackTrace();

		} finally {
			if (null != statement) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}

}
