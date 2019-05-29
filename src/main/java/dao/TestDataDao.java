package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.TestDataVo;

@Repository
public class TestDataDao {
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private final String sqlNameSpace = "testData.";
	
	public int insert(TestDataVo testDataVo) {
		return this.sqlSessionTemplate.insert(this.sqlNameSpace + "insert", testDataVo);
	}
	public TestDataVo selectOne(TestDataVo testDataVo) {
		return this.sqlSessionTemplate.selectOne(this.sqlNameSpace + "selectOne", testDataVo);
	}
	public List<TestDataVo> selectList(TestDataVo testDataVo) {
		return this.sqlSessionTemplate.selectList(this.sqlNameSpace + "selectList", testDataVo);
	}
	public int update(TestDataVo testDataVo) {
		return this.sqlSessionTemplate.update(this.sqlNameSpace + "update", testDataVo);
	}
	public int delete(TestDataVo testDataVo) {
		return this.sqlSessionTemplate.delete(this.sqlNameSpace + "delete", testDataVo);
	}
	public int count() {
		return this.sqlSessionTemplate.selectOne(this.sqlNameSpace + "count");
	}
}
