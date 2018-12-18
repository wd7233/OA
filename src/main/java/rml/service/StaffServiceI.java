package rml.service;

import java.util.List;

import rml.model.MUser;
import rml.model.Shop;
import rml.model.Staff;

public interface StaffServiceI
{
    
    List<Staff> selectByUser();
    
    List<Staff> selectAll();
    
    List<Staff> selectByRole(Integer userId,Integer role,Integer companyId);
    
    Staff selectByAccountAndPwd(String account, String pwd);
    
    int insert(Staff user);
    
}
