package josh.address.model;

import java.sql.SQLException;
import java.util.List;

public interface InterAdressDAO {

	List<AddressDTO> selectAddress(String userid) throws SQLException;

}
