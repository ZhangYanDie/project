package dao;

import java.util.List;

import beans.Page;
import beans.User;

public interface UserDao {
	
	public User login(String userName,String password);
	public boolean regist(String userName,String password);
	public List<User> queryAll(Page page);
	public List<User> queryByUserName(String userName);
	public boolean deleteById(int id);
	public boolean updateUserPassword(String userName,String password);
	public int rowCount();
}
