package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.AccountDao;
import poly.store.entity.Account;
import poly.store.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDao dao;
	
	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}

}
