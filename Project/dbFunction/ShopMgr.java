package project.dbFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import project.bean.ProductBean;
import project.bean.StatisBean;

public class ShopMgr {
	private DBConnectionMgr pool;
	private static ShopMgr shopMgr = null;

	private ShopMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static ShopMgr getInstance() {
        if (shopMgr == null) {
        	shopMgr = new ShopMgr();
        }
        return shopMgr;
    }

	public Vector<ProductBean> selectPro(String Cate, DefaultTableModel dtm) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<ProductBean> pbv = new Vector<>();
		dtm.setNumRows(0);
		try {
			con = pool.getConnection();
			if (Cate != "전체") {
				sql = "SELECT p.pro_idx as 상품번호, p.name as 상품명, c.cat_name as 카테고리, p.price as 가격, p.inventory as 현재재고량, p.IMG_ADDRESS\n"
						+ "FROM product p, category c " + "WHERE p.category_id = c.cat_idx AND c.cat_name = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, Cate);
			} else {
				sql = "SELECT p.pro_idx as 상품번호, p.name as 상품명, c.cat_name as 카테고리, p.price as 가격, p.inventory as 현재재고량, p.IMG_ADDRESS\n"
						+ "FROM product p, category c " + "WHERE p.category_id = c.cat_idx";
				pstmt = con.prepareStatement(sql);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector<Object> data = new Vector<>();
				ProductBean pb = new ProductBean();
				data.add(rs.getInt(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getInt(4));
				data.add(rs.getInt(5));
				pb.setPRO_IDX(rs.getInt(1));
				pb.setIMG_ADDRESS(rs.getString(6));
				dtm.addRow(data);
				pbv.add(pb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return pbv;
	}

	public void selectCate(DefaultComboBoxModel<String> dcbm) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		dcbm.removeAllElements();
		dcbm.addElement("전체");
		try {
			con = pool.getConnection();
			sql = "select cat_name from category";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dcbm.addElement(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	public boolean updateInven(int idx, int inven) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int updateAmount = 0;
		try {
			con = pool.getConnection();
			sql = "update product set inventory = inventory + ? where PRO_IDX = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, inven);
			pstmt.setInt(2, idx);
			updateAmount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return updateAmount > 0 ? true:false;
	}
	
	public boolean deletePro(int idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int updateAmount = 0;
		try {
			con = pool.getConnection();
			sql = "delete from PRODUCT where PRO_IDX = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			updateAmount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return updateAmount > 0 ? true:false;
	}
	
	public Vector<StatisBean> selectCateSt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<Integer> temp = new Vector<Integer>();
		Vector<StatisBean> returnbean = new Vector<StatisBean>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "select c.cat_name, sum(o.quantity)\r\n"
					+ " from order_log o, product p, category c\r\n"
					+ " WHERE o.pro_idx = p.pro_idx AND p.category_id = c.cat_idx AND o.status = 1\r\n"
					+ " Group by c.cat_name\r\n"
					+ " order by sum(o.quantity) DESC";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StatisBean sb = new StatisBean();
				sb.setcName(rs.getString(1));
				sb.setPurCount(rs.getInt(2));
				temp.add(rs.getInt(2));
				sum += rs.getInt(2);
				returnbean.add(sb);
			}
//			for (int i = 0; i < temp.size(); i++) {
//				sum += temp.get(i);
//				System.out.println("temp : "+ temp.get(i));
//			}
			for (int i = 0; i < temp.size(); i++) {
				returnbean.get(i).setPurCountRatio((int)Math.round((temp.get(i)/sum)*100));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return returnbean;
	}
	
	public Vector<StatisBean> selectCateAmountSt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<Integer> temp = new Vector<Integer>();
		Vector<StatisBean> returnbean = new Vector<StatisBean>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "select c.cat_name, sum(o.quantity*p.price)\r\n"
					+ " from order_log o, product p, category c\r\n"
					+ " WHERE o.pro_idx = p.pro_idx AND p.category_id = c.cat_idx AND o.status = 1 AND o.pro_idx = p.pro_idx\r\n"
					+ " Group by c.cat_name\r\n"
					+ " order by sum(o.quantity*p.price) DESC";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StatisBean sb = new StatisBean();
				sb.setcName(rs.getString(1));
				sb.setPurCount(rs.getInt(2));
				temp.add(rs.getInt(2));
				sum += rs.getInt(2);
				returnbean.add(sb);
			}
//			for (int i = 0; i < temp.size(); i++) {
//				sum += temp.get(i);
//				System.out.println("temp : "+ temp.get(i));
//			}
			for (int i = 0; i < temp.size(); i++) {
				returnbean.get(i).setPurCountRatio((int)Math.round((temp.get(i)/sum)*100));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return returnbean;
	}
	
	public Vector<StatisBean> selectYearSt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<Integer> temp = new Vector<Integer>();
		Vector<StatisBean> returnbean = new Vector<StatisBean>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "SELECT Extract(Year from final_date), SUM(quantity)\r\n"
					+ "FROM order_log\r\n"
					+ "where status = 1\r\n"
					+ "group by Extract(Year from final_date)\r\n"
					+ "order by Extract(Year from final_date)";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StatisBean sb = new StatisBean();
				sb.setYear(rs.getInt(1));
				sb.setPurCount(rs.getInt(2));
				temp.add(rs.getInt(2));
				sum += rs.getInt(2);
				returnbean.add(sb);
			}
//			for (int i = 0; i < temp.size(); i++) {
//				sum += temp.get(i);
//				System.out.println("temp : "+ temp.get(i));
//			}
			for (int i = 0; i < temp.size(); i++) {
				returnbean.get(i).setPurCountRatio((int)Math.round((temp.get(i)/sum)*100));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return returnbean;
	}
	
	public Vector<StatisBean> selectYearAmountSt() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<Integer> temp = new Vector<Integer>();
		Vector<StatisBean> returnbean = new Vector<StatisBean>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "SELECT Extract(Year from o.final_date), SUM(o.quantity*p.price)\r\n"
					+ "FROM order_log o, product p\r\n"
					+ "where o.status = 1 AND o.pro_idx = p.pro_idx\r\n"
					+ "group by Extract(Year from o.final_date)\r\n"
					+ "order by Extract(Year from o.final_date)";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StatisBean sb = new StatisBean();
				sb.setYear(rs.getInt(1));
				sb.setPurCount(rs.getInt(2));
				temp.add(rs.getInt(2));
				sum += rs.getInt(2);
				returnbean.add(sb);
			}
//			for (int i = 0; i < temp.size(); i++) {
//				sum += temp.get(i);
//				System.out.println("temp : "+ temp.get(i));
//			}
			for (int i = 0; i < temp.size(); i++) {
				returnbean.get(i).setPurCountRatio((int)Math.round((temp.get(i)/sum)*100));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return returnbean;
	}
	
	public Vector<StatisBean> selectQuarterSt(int year) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<StatisBean> returnBean = new Vector<StatisBean>();
		for (int i = 0; i < 4; i++) {
			StatisBean st = new StatisBean();
			st.setcName((i+1)+"분기");
			returnBean.add(st);
		}
		Vector<StatisBean> temp = new Vector<StatisBean>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "select DISTINCT(EXTRACT(MONTH FROM final_date)), sum(Quantity)\r\n"
					+ "from order_log\r\n"
					+ "where status = 1 AND EXTRACT(YEAR FROM final_date) = ?\r\n"
					+ "group by EXTRACT(MONTH FROM final_date)\r\n"
					+ "order by EXTRACT(MONTH FROM final_date)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, year);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StatisBean sb = new StatisBean();
				sb.setMonth(rs.getInt(1));
				sb.setPurCount(rs.getInt(2));
				sum += rs.getInt(2);
				temp.add(sb);
			}
			for (int i = 0; i < temp.size(); i++) {
				if ((temp.get(i).getMonth()-1)/3 == 0) {
					returnBean.get(0).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth()-1)/3 == 1) {
					returnBean.get(1).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth()-1)/3 == 2) {
					returnBean.get(2).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth()-1)/3 == 3) {
					returnBean.get(3).purCountPlus(temp.get(i).getPurCount());
				}
			}
			for (int i = 0; i < 4; i++) {
				returnBean.get(i).setPurCountRatio((int)Math.round((returnBean.get(i).getPurCount()/sum)*100));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return returnBean;
	}
	
	public Vector<StatisBean> selectQuarterAmountSt(int year) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<StatisBean> returnBean = new Vector<StatisBean>();
		for (int i = 0; i < 4; i++) {
			StatisBean st = new StatisBean();
			st.setcName((i+1)+"분기");
			returnBean.add(st);
		}
		Vector<StatisBean> temp = new Vector<StatisBean>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "select DISTINCT(EXTRACT(MONTH FROM o.final_date)), sum(o.Quantity*p.price)\r\n"
					+ "from order_log o, product p\r\n"
					+ "where o.status = 1 AND o.pro_idx = p.pro_idx AND EXTRACT(YEAR FROM o.final_date) = ?\r\n"
					+ "group by EXTRACT(MONTH FROM o.final_date)\r\n"
					+ "order by EXTRACT(MONTH FROM o.final_date)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, year);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				StatisBean sb = new StatisBean();
				sb.setMonth(rs.getInt(1));
				sb.setPurCount(rs.getInt(2));
				sum += rs.getInt(2);
				temp.add(sb);
			}
			for (int i = 0; i < temp.size(); i++) {
				if ((temp.get(i).getMonth()-1)/3 == 0) {
					returnBean.get(0).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth()-1)/3 == 1) {
					returnBean.get(1).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth()-1)/3 == 2) {
					returnBean.get(2).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth()-1)/3 == 3) {
					returnBean.get(3).purCountPlus(temp.get(i).getPurCount());
				}
			}
			for (int i = 0; i < 4; i++) {
				returnBean.get(i).setPurCountRatio((int)Math.round((returnBean.get(i).getPurCount()/sum)*100));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return returnBean;
	}
	
	public Vector<String> selectYear() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<String> returnStr = new Vector<String>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "select DISTINCT(EXTRACT(YEAR FROM final_date))\r\n"
					+ "from order_log\r\n"
					+ "where status = 1\r\n"
					+ "order by EXTRACT(YEAR FROM final_date)";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				returnStr.add(Integer.toString(rs.getInt(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return returnStr;
	}
	
//	public void insertMsg(ProductBean bean) {
//		// insert into tblMessage values (null, ?, ?, ?, now())
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String sql = null;
//		int check = 0;
//		try {
//			con = pool.getConnection();
//			sql = "Update on ";
//			pstmt = con.prepareStatement(sql);
////			pstmt.setString(1, bean.getFid());
////			pstmt.setString(2, bean.getTid());
////			pstmt.setString(3, bean.getMsg());
//			check = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			pool.freeConnection(con, pstmt);
//		}
//		if (check > 0) {
//			System.out.println("���� �Ϸ�");
//		} else {
//			System.out.println("���� ����");
//		}
//	}
//
//	public Vector<ProductBean> getMsgList(String id) {
//		// select * from tblMessage where fid = ? or tid = ?
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String sql = null;
//		Vector<ProductBean> vlist = new Vector<>();
//		try {
//			con = pool.getConnection();
//			sql = "select * from tblMessage where fid = ? or tid = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.setString(2, id);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				ProductBean bean = new ProductBean();
////				bean.setNo(rs.getInt("no"));
////				bean.setFid(rs.getString("fid"));
////				bean.setTid(rs.getString("tid"));
////				bean.setMsg(rs.getString("msg"));
////				bean.setMdate(rs.getString("mdate"));
//				vlist.add(bean);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			pool.freeConnection(con, pstmt);
//		}
//		return vlist;
//	}
}
