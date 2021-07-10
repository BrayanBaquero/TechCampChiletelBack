package com.chiletel.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chiletel.exceptionHandler.BadRequestException;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;



@Repository
@Transactional
public class AgendaSpDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public int agendarOrdenes() {
		SimpleJdbcCall simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("PA_AGENDAR_ORDENES");
		SqlParameterSource in = new MapSqlParameterSource();
		try {
			Map<String, Object> out=simpleJdbcCall.execute(in);
			if(out!=null) {
				return ((BigDecimal)out.get("ESTADO")).intValueExact();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new BadRequestException(e.getMessage());
		}
		
		return 0;
	}
	
	

}
