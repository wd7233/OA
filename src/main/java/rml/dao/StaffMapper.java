package rml.dao;

import java.util.List;
import java.util.Map;

import rml.model.Staff;

public interface StaffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Staff record);

    Staff selectByPrimaryKey(Integer id);

    List<Staff> selectAll();

    int updateByPrimaryKey(Staff record);
    /**==========================================================**/
    List<Staff> selectByRole(Map<String,Object> paramMap);
    Staff selectByAccountAndPwd(Map<String,Object> paramMap);
}