package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Company;
import it.contrader.model.CompanySectorDistinct;
import it.contrader.model.CompanyName;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class CompanyDAO implements DAO<Company> {

	private final String QUERY_ALL = "SELECT * FROM company";
	private final String QUERY_SEARCH = "SELECT * FROM company WHERE name LIKE ? AND address LIKE ? AND city LIKE ? AND sector LIKE ?";
	private final String QUERY_CREATE = "INSERT INTO company (name, address, city, sector) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM company WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE company SET name=?, address=?, city=?, sector=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM company WHERE id=?";
	private final String QUERY_SECTOR_ALL_DISTINCT = "SELECT DISTINCT sector FROM company ORDER BY sector ASC";
	private final String QUERY_NAME_ALL = "SELECT id, name FROM company ORDER BY name ASC";
	
	
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
				String sector = resultSet.getString("sector");
				company = new Company(name, address, city, sector);
				company.setId(id);
				usersList.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	public List<Company> search (String name, String address, String city, String sector) {
		List<Company> companiesList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, city);
			preparedStatement.setString(4, sector);
			ResultSet resultSet = preparedStatement.executeQuery();
			Company company;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				name = resultSet.getString("name");
				address = resultSet.getString("address");
				city = resultSet.getString("city");
				sector = resultSet.getString("sector");
				company = new Company(name, address, city, sector);
				company.setId(id);
				companiesList.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companiesList;
	}

	public boolean insert(Company userToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, userToInsert.getName());
			preparedStatement.setString(2, userToInsert.getAddress());
			preparedStatement.setString(3, userToInsert.getCity());
			preparedStatement.setString(4, userToInsert.getSector());
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
			String name, address, city, sector;

			name = resultSet.getString("name");
			address = resultSet.getString("address");
			city = resultSet.getString("city");
			sector = resultSet.getString("sector");
			Company company = new Company(name, address, city, sector);
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
				if (companyToUpdate.getSector() == null || companyToUpdate.getSector().equals("")) {
					companyToUpdate.setSector(companyRead.getSector());
				}

				// Update the company
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, companyToUpdate.getName());
				preparedStatement.setString(2, companyToUpdate.getAddress());
				preparedStatement.setString(3, companyToUpdate.getCity());
				preparedStatement.setString(4, companyToUpdate.getSector());
				preparedStatement.setInt(5, companyToUpdate.getId());
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

	public List<CompanySectorDistinct> getSectorDistinctAll () {
		List<CompanySectorDistinct> sectorDistincAlltList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_SECTOR_ALL_DISTINCT);
			while (resultSet.next()) {
				CompanySectorDistinct sector = new CompanySectorDistinct();
				sector.setSector(resultSet.getString("sector"));
				sectorDistincAlltList.add(sector);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sectorDistincAlltList;
	}
	
	public List<CompanyName> getCompanyAll () {
		List<CompanyName> companyAllList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_NAME_ALL);
			while (resultSet.next()) {
				CompanyName company = new CompanyName();
				company.setId(Integer.parseInt(resultSet.getString("id")));
				company.setName(resultSet.getString("name"));
				companyAllList.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companyAllList;
	}
}
