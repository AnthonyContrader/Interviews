package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Question;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class QuestionDAO implements DAO<Question> {

	private final String QUERY_ALL = "SELECT * FROM question";
	private final String QUERY_CREATE = "INSERT INTO question (question, sector, companyid) VALUES (?,?,?)";
	private final String QUERY_READ = "SELECT * FROM question WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE question SET question=?, sector=?, companyid=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM question WHERE id=?";

	public QuestionDAO() {

	}

	public List<Question> getAll() {
		List<Question> questionsList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Question question;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String questionText = resultSet.getString("question");
				String sector = resultSet.getString("sector");
				int companyid = resultSet.getInt("companyid");
				question = new Question(questionText, sector, companyid);
				question.setId(id);
				questionsList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questionsList;
	}

	public boolean insert(Question questionToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, questionToInsert.getQuestion());
			preparedStatement.setString(2, questionToInsert.getSector());
			preparedStatement.setInt(3, questionToInsert.getCompanyid());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Question read(int questionId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, questionId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String questionText, sector;
			int companyid;

			questionText = resultSet.getString("question");
			sector = resultSet.getString("sector");
			companyid = resultSet.getInt("companyid");
			Question question = new Question(questionText, sector, companyid);
			question.setId(resultSet.getInt("id"));

			return question;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(Question questionToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (questionToUpdate.getId() == 0)
			return false;

		Question questionRead = read(questionToUpdate.getId());
		if (!questionRead.equals(questionToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (questionToUpdate.getQuestion() == null || questionToUpdate.getQuestion().equals("")) {
					questionToUpdate.setQuestion(questionRead.getQuestion());
				}

				if (questionToUpdate.getSector() == null || questionToUpdate.getSector().equals("")) {
					questionToUpdate.setSector(questionRead.getSector());
				}

				if (questionToUpdate.getCompanyid() == 0) {
					questionToUpdate.setCompanyid(questionRead.getCompanyid());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, questionToUpdate.getQuestion());
				preparedStatement.setString(2, questionToUpdate.getSector());
				preparedStatement.setInt(3, questionToUpdate.getCompanyid());
				preparedStatement.setInt(4, questionToUpdate.getId());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				return false;
			}
		}

		return false;

	}

	public boolean delete(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}


}
