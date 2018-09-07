package prjframework.common.dataaccess.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibatis.common.jdbc.logging.LoggableStatement;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**-----------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 소속 : 고려대학교
 * 수정일 : 2018.03.09
 * 수정자 : 엄재덕
 * 수정내용 : 최초생성
 *------------------------------------------------------------------------
 */
@Component
public class MybatisDataAccessDAO {

	/**
	 * 
	 */
	@Autowired
	private SqlSession sqlSession ;

	
	public Object select(String queryId, Object parameterObject) {
		return sqlSession.selectOne(queryId, parameterObject) ;
	}
	
	@SuppressWarnings("rawtypes")
	public List list(String queryId, Object parameterObject) {
		return sqlSession.selectList(queryId, parameterObject) ;
	} 
	
	public Object insert(String queryId, Object parameterObject) {
		return sqlSession.insert(queryId, parameterObject) ;
	}
	
	public int delete(String queryId, Object parameterObject) {
		return sqlSession.delete(queryId, parameterObject) ;
	}
	
	public int update(String queryId, Object parameterObject) {
		return sqlSession.update(queryId, parameterObject) ;
	}
	
	public Object selectByPk(String queryId, Object parameterObject) {	
		return sqlSession.selectOne(queryId, parameterObject);
	}
}
