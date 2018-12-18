package rml.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rml.dao.StaffMapper;
import rml.model.Staff;

@Service("staffService")
public class StaffServiceImpl implements StaffServiceI{

	@Autowired
	private StaffMapper staffMapper;

    @Override
    public List<Staff> selectByUser()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Staff> selectAll()
    {
        // TODO Auto-generated method stub
        return staffMapper.selectAll();
    }

    @Override
    public Staff selectByAccountAndPwd(String account, String pwd)
    {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("account", account);
        paramMap.put("pwd", pwd);
        return staffMapper.selectByAccountAndPwd(paramMap);
    }

    @Override
    public List<Staff> selectByRole(Integer userId,Integer role,Integer companyId)
    {
        Map<String,Object> paramMap = new HashMap<String,Object> ();
        paramMap.put("userId", userId);
        paramMap.put("role", role);
        paramMap.put("companyId", companyId);
        return staffMapper.selectByRole(paramMap);
    }

    @Override
    public int insert(Staff user)
    {
        return staffMapper.insert(user);
    }


}
