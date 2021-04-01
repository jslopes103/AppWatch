package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import model.Card;
import model.Content;
import model.Watch;

//contentdata
public class ContentData {
	
	//get all the content (movie and serie)
	public boolean selectAllContent(){

		//connection
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;

		//list of content
		Content.list.clear();
		
		boolean ret = false;
		
		try {
			//get connection
			connection = Database.getConnection();
			String query = "SELECT idContent, name, description, image, idCategory, type, price FROM content;";
			//create statement
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				//fill the list
				Content content = new Content();
				content.setIdContent(resultSet.getInt(1));
				content.setName(resultSet.getString(2));
				content.setDescription(resultSet.getString(3));
				content.setImage(resultSet.getString(4));
				content.setIdCategory(resultSet.getInt(5));
				content.setType(resultSet.getInt(6));
				content.setPrice(resultSet.getDouble(7));
				Content.list.add(content);
				ret = true;
			}

		} catch (SQLException exception) {
			//exception
			exception.printStackTrace();

		} finally {
			//close if all connections
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
	
	//select card and pay
	public boolean selectCardAndPay(int cardnumber, int credit){
		//connection
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultSet;
		
		boolean ret = false;
		
		//create new card
		Card card = new Card();
		
		try {
			//get connection
			connection = Database.getConnection();
			connection.setAutoCommit(false);
			String query = "SELECT id, credit FROM card WHERE card = " + cardnumber;
			statement = connection.prepareStatement(query);
			//execute query
			resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				//if find the card, check if there is credit and update
				card.setId(resultSet.getInt(1));
				card.setCredit(resultSet.getInt(2));
				
				if(card.getCredit() >= credit)
				{
					//update credit
					String query2 = "UPDATE card SET credit = credit - ? WHERE id = ?";
					statement2 = connection.prepareStatement(query2);
					statement2.setInt(1, credit);
					statement2.setInt(2, card.getId());
					statement2.executeUpdate();
					connection.commit();
					//insert into paid
					String query3 = "INSERT INTO paid(idcontent,idcard,date) VALUES(?,?,?)";
					statement3 = connection.prepareStatement(query3);
					statement3.setInt(1, Content.idContentBought);
					statement3.setInt(2, card.getId());
					statement3.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
					statement3.executeUpdate();
					connection.commit();
					
					//valid payment
					ret = true;
				}
				else
				{
					//error
					ret = false;
				}
				
			}

		} catch (SQLException exception) {
			//exception
			exception.printStackTrace();

		} finally {
			//close all connections
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
	
	//select all watch according to the card
	public boolean selectAllWatch(int card){

		//connection
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		
		Watch.listWatch.clear();
		
		boolean ret = false;
		
		try {
			//get connection
			connection = Database.getConnection();
			String query = "SELECT card.card, content.name, paid.date FROM ((paid INNER JOIN content ON paid.idcontent = content.idContent) INNER JOIN card ON paid.idcard = card.id) WHERE card = "+ card +" ORDER BY paid.date ASC;";
			statement = connection.createStatement();
			//execute query
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				//fill the list
				Watch watch = new Watch();
				watch.setCardNumber(resultSet.getInt(1));
				watch.setName(resultSet.getString(2));
				watch.setDate(resultSet.getDate(3));
				Watch.listWatch.add(watch);
				ret = true;
			}

		} catch (SQLException exception) {
			//exceptions
			exception.printStackTrace();

		} finally {
			//close all connection
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
