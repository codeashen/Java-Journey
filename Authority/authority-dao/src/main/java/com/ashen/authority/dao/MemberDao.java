package com.ashen.authority.dao;

import com.ashen.authority.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 会员持久层接口
 */
@Repository
public interface MemberDao {

    /**
     * 根据id查询会员
     * @param id
     * @return
     */
    @Select("select * from authority_member where id = #{id}")
    Member findById(String id);
}
