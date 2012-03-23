package eu.musictraveler.general.bus;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *  
 * @author Pavel Gorohhovatski
 */
public class BaseDao {

	@Resource
	protected JdbcTemplate jdbcTemplate;


}