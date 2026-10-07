package kr.swdl.model.sales;

import java.sql.SQLException;
import java.util.List;

import kr.swdl.model.DBCP;

public class SaleService {
	public List<SaleVO>  findAllSalesByMember(String memberId) {
	
		try {
			return new SaleDAO(DBCP.getConnection()).findAllSalesByMember(memberId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return null;
	}
	
	public List<SaleVO>  findSalesPeriodMember(String memberId, String startDay, String endDay) {
		try {
			return new SaleDAO(DBCP.getConnection()).findSalesPeriodMember(memberId, startDay, endDay);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return null;
	}

	public List<SaleVO> findSalesPeriodAdmin() {
		
		try {
			return new SaleDAO(DBCP.getConnection()).findSalesPeriodAdmin();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return null;
	}

	public List<SaleVO> findSalesByPeriod(String startDay, String endDay) {
	 
		try {
			return new SaleDAO(DBCP.getConnection()).findSalesByPeriod(startDay, endDay);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return null;
	}

	public int getSalesTotalPrice() {
		try {
			return new SaleDAO(DBCP.getConnection()).getSalesTotalPrice();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return 0;
	}
	
	public int getSalesTotalPriceByPeriod(String startDay, String endDay) {
		
		try {
			return new SaleDAO(DBCP.getConnection()).getSalesTotalPriceByPeriod(startDay, endDay);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return 0;
	}
	
	public int getSalesTotalPriceByMemberId(String memberId) {
		try {
			return new SaleDAO(DBCP.getConnection()).getSalesTotalPriceByMemberId(memberId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return 0;
	}

	public int getSalesTotalPriceByMemberIdByPeriod(String memberId, String startDay, String endDay) {
	
		try {
			return new SaleDAO(DBCP.getConnection()).getSalesTotalPriceByMemberIdByPeriod(memberId, startDay, endDay);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		return 0;
	}
	
	
}

