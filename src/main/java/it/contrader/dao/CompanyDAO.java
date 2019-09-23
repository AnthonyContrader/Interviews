package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Company;
import it.contrader.model.User;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class CompanyDAO implements DAO<Company> {

	private final String QUERY_ALL = "SELECT * FROM company";
	private final String QUERY_CREATE = "INSERT INTO company (name, address, city) VALUES (?,?,?)";
	private final String QUERY_READ = "SELECT * FROM company WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE company SET name=?, address=?, city=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM company WHERE id=?";

	public CompanyDAO() {

	}

	public List<Company> getAll() {
		List<Company> usersList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Company company;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				company = new Company(name, address, city);
				company.setId(id);
				usersList.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	public boolean insert(Company userToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, userToInsert.getName());
			preparedStatement.setString(2, userToInsert.getAddress());
			preparedStatement.setString(3, userToInsert.getCity());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Company read(int companyId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, companyId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String name, address, city;

			name = resultSet.getString("name");
			address = resultSet.getString("address");
			city = resultSet.getString("city");
			Company company = new Company(name, address, city);
			company.setId(resultSet.getInt("id"));

			return company;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(Company companyToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (companyToUpdate.getId() == 0)
			return false;

		Company companyRead = read(companyToUpdate.getId());
		if (!companyRead.equals(companyToUpdate)) {
			try {
				// Fill the companyToUpdate object
				if (companyToUpdate.getName() == null || companyToUpdate.getName().equals("")) {
					companyToUpdate.setName(companyRead.getName());
				}

				if (companyToUpdate.getAddress() == null || companyToUpdate.getAddress().equals("")) {
					companyToUpdate.setAddress(companyRead.getAddress());
				}

				if (companyToUpdate.getCity() == null || companyToUpdate.getCity().equals("")) {
					companyToUpdate.setCity(companyRead.getCity());
				}

				// Update the company
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, companyToUpdate.getName());
				preparedStatement.setString(2, companyToUpdate.getAddress());
				preparedStatement.setString(3, companyToUpdate.getCity());
				preparedStatement.setInt(4, companyToUpdate.getId());
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
