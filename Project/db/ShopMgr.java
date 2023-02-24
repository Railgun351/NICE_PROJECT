package project.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import project.bean.CartBean;
import project.bean.MainProductBean;
import project.bean.MemberBean;
import project.bean.ProductBean;
import project.bean.StatisBean;
import project.bean.starDto;

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

	// 송명준 재고추가, 통계, 메인페이지, 장바구니 로그인회원정보 시작
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
				pb.setProIdx(rs.getInt(1));
				pb.setImgAddress(rs.getString(6));
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

	public Vector<MainProductBean> selectPro(String Cate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<MainProductBean> mpbv = new Vector<>();
		try {
			con = pool.getConnection();
			if (Cate != "전체") {
				sql = "SELECT p.pro_idx as 상품번호, p.name as 상품명, c.cat_idx as 카테고리번호,c.cat_name as 카테고리, p.price as 가격, p.IMG_ADDRESS\n"
						+ "FROM product p, category c " + "WHERE p.category_id = c.cat_idx AND c.cat_name = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, Cate);
			} else {
				sql = "SELECT p.pro_idx as 상품번호, p.name as 상품명, c.cat_idx as 카테고리번호,c.cat_name as 카테고리, p.price as 가격, p.IMG_ADDRESS\n"
						+ "FROM product p, category c " + "WHERE p.category_id = c.cat_idx";
				pstmt = con.prepareStatement(sql);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MainProductBean mpb = new MainProductBean();
				mpb.setProIdx(rs.getInt(1));
				mpb.setProName(rs.getString(2));
				mpb.setCategoryIdx(rs.getInt(3));
				mpb.setCategoryName(rs.getString(4));
				mpb.setPrice(rs.getInt(5));
				mpb.setImgAddress(rs.getString(6));
				mpbv.add(mpb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return mpbv;
	}

	public Vector<MainProductBean> selectProBySearch(String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		Vector<MainProductBean> mpbv = new Vector<>();
		try {
			con = pool.getConnection();
			sql = "SELECT p.pro_idx as 상품번호, p.name as 상품명, c.cat_idx as 카테고리번호,c.cat_name as 카테고리, p.price as 가격, p.IMG_ADDRESS\r\n"
					+ "from product p, category c\r\n"
					+ "where replace(trim(p.name),' ','') like ? AND p.category_id = c.cat_idx";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + word + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MainProductBean mpb = new MainProductBean();
				mpb.setProIdx(rs.getInt(1));
				mpb.setProName(rs.getString(2));
				mpb.setCategoryIdx(rs.getInt(3));
				mpb.setCategoryName(rs.getString(4));
				mpb.setPrice(rs.getInt(5));
				mpb.setImgAddress(rs.getString(6));
				mpbv.add(mpb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return mpbv;
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
		return updateAmount > 0 ? true : false;
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
		return updateAmount > 0 ? true : false;
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
			sql = "select c.cat_name, sum(o.quantity)\r\n" + " from order_log o, product p, category c\r\n"
					+ " WHERE o.pro_idx = p.pro_idx AND p.category_id = c.cat_idx AND o.status = 1\r\n"
					+ " Group by c.cat_name\r\n" + " order by sum(o.quantity) DESC";
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
				returnbean.get(i).setPurCountRatio((int) Math.round((temp.get(i) / sum) * 100));
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
			sql = "select c.cat_name, sum(o.quantity*p.price)\r\n" + " from order_log o, product p, category c\r\n"
					+ " WHERE o.pro_idx = p.pro_idx AND p.category_id = c.cat_idx AND o.status = 1 AND o.pro_idx = p.pro_idx\r\n"
					+ " Group by c.cat_name\r\n" + " order by sum(o.quantity*p.price) DESC";
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
				returnbean.get(i).setPurCountRatio((int) Math.round((temp.get(i) / sum) * 100));
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
			sql = "SELECT Extract(Year from final_date), SUM(quantity)\r\n" + "FROM order_log\r\n"
					+ "where status = 1\r\n" + "group by Extract(Year from final_date)\r\n"
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
				returnbean.get(i).setPurCountRatio((int) Math.round((temp.get(i) / sum) * 100));
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
					+ "FROM order_log o, product p\r\n" + "where o.status = 1 AND o.pro_idx = p.pro_idx\r\n"
					+ "group by Extract(Year from o.final_date)\r\n" + "order by Extract(Year from o.final_date)";
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
				returnbean.get(i).setPurCountRatio((int) Math.round((temp.get(i) / sum) * 100));
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
			st.setcName((i + 1) + "분기");
			returnBean.add(st);
		}
		Vector<StatisBean> temp = new Vector<StatisBean>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "select DISTINCT(EXTRACT(MONTH FROM final_date)), sum(Quantity)\r\n" + "from order_log\r\n"
					+ "where status = 1 AND EXTRACT(YEAR FROM final_date) = ?\r\n"
					+ "group by EXTRACT(MONTH FROM final_date)\r\n" + "order by EXTRACT(MONTH FROM final_date)";
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
				if ((temp.get(i).getMonth() - 1) / 3 == 0) {
					returnBean.get(0).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth() - 1) / 3 == 1) {
					returnBean.get(1).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth() - 1) / 3 == 2) {
					returnBean.get(2).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth() - 1) / 3 == 3) {
					returnBean.get(3).purCountPlus(temp.get(i).getPurCount());
				}
			}
			for (int i = 0; i < 4; i++) {
				returnBean.get(i).setPurCountRatio((int) Math.round((returnBean.get(i).getPurCount() / sum) * 100));
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
			st.setcName((i + 1) + "분기");
			returnBean.add(st);
		}
		Vector<StatisBean> temp = new Vector<StatisBean>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "select DISTINCT(EXTRACT(MONTH FROM o.final_date)), sum(o.Quantity*p.price)\r\n"
					+ "from order_log o, product p\r\n"
					+ "where o.status = 1 AND o.pro_idx = p.pro_idx AND EXTRACT(YEAR FROM o.final_date) = ?\r\n"
					+ "group by EXTRACT(MONTH FROM o.final_date)\r\n" + "order by EXTRACT(MONTH FROM o.final_date)";
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
				if ((temp.get(i).getMonth() - 1) / 3 == 0) {
					returnBean.get(0).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth() - 1) / 3 == 1) {
					returnBean.get(1).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth() - 1) / 3 == 2) {
					returnBean.get(2).purCountPlus(temp.get(i).getPurCount());
				} else if ((temp.get(i).getMonth() - 1) / 3 == 3) {
					returnBean.get(3).purCountPlus(temp.get(i).getPurCount());
				}
			}
			for (int i = 0; i < 4; i++) {
				returnBean.get(i).setPurCountRatio((int) Math.round((returnBean.get(i).getPurCount() / sum) * 100));
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
			sql = "select DISTINCT(EXTRACT(YEAR FROM final_date))\r\n" + "from order_log\r\n" + "where status = 1\r\n"
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

	public HashMap<Integer, CartBean> selectCart(int memIdx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		HashMap<Integer, CartBean> cartHm = new HashMap<>();
		double sum = 0;
		try {
			con = pool.getConnection();
			sql = "select p.pro_idx as proIdx, p.name as name, o.quantity as quantity, p.price as price\r\n"
					+ "from order_log o, product p\r\n"
					+ "where p.pro_idx = o.pro_idx and o.mem_idx = ? and status = 0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memIdx);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CartBean cb = new CartBean();
				cb.setProIdx(rs.getInt(1));
				cb.setProName(rs.getString(2));
				cb.setQuantity(rs.getInt(3));
				cb.setProPrice(rs.getInt(4));
				cb.setMemIdx(memIdx);
				if (!cartHm.containsKey(cb.getProIdx())) {
					cartHm.put(cb.getProIdx(), cb);
				} else {
					int temp = cartHm.get(cb.getProIdx()).getQuantity();
					temp += cb.getQuantity();
					cartHm.get(cb.getProIdx()).setQuantity(temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return cartHm;
	}

	public boolean updateCartFromPro(CartBean cb, int status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int updateAmount = 0;
		try {
			con = pool.getConnection();
			if (status == 1) {
				con.setAutoCommit(false);
				sql = "update order_log set status = ?, Final_date = SYSDATE where PRO_IDX = ? AND MEM_IDX = ? AND STATUS = 0";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, status);
				pstmt.setInt(2, cb.getProIdx());
				pstmt.setInt(3, cb.getMemIdx());
				updateAmount = pstmt.executeUpdate();
				sql = "update product set inventory = inventory - ? where PRO_IDX = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cb.getQuantity());
				pstmt.setInt(2, cb.getProIdx());
				updateAmount = pstmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			} else {
				sql = "update order_log set status = ?, Final_date = SYSDATE where PRO_IDX = ? AND STATUS = 0";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, status);
				pstmt.setInt(2, cb.getProIdx());
				updateAmount = pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return updateAmount > 0 ? true : false;
	}
	
	public MemberBean selectMember(String id, String pw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		MemberBean mb = new MemberBean();
		try {
			con = pool.getConnection();
			sql = "select *\r\n"
					+ "from member\r\n"
					+ "where id = ? AND pw = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				mb.setIdx(rs.getInt(1));
				mb.setId(rs.getString(2));
				mb.setPassword(rs.getString(3));
				mb.setName(rs.getString(4));
				mb.setGrade(rs.getString(5));
				mb.setPoint(rs.getInt(6));
				System.out.println(rs.getString(7));
				mb.setJoinDate(rs.getString(7));
				mb.setType(rs.getString(8));
				System.out.println(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return mb;
	}
	// 송명준 재고추가, 통계, 메인페이지, 장바구니 로그인회원정보 끝

	// 강성웅-리뷰

	public List<starDto> getStarDtoLists(int proIdx) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM shop.REVIEW WHERE PRO_IDX = ?";
		List<starDto> starDtoList = new ArrayList<>();

		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, proIdx);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				starDto tempStarDto = new starDto(rs.getInt("PRO_IDX"), rs.getInt("MEM_IDX"),
						rs.getTimestamp("COM_DATE"), rs.getString("COMMENTS"), rs.getInt("STAR_RATING"));
				starDtoList.add(tempStarDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}

		return starDtoList;
	}

	public boolean deleteStarCustomer(int proIdx, int memIdx, Timestamp comDate, String comments, int starRating) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int updateAmount = 0;
		try {
			con = pool.getConnection();
			sql = "DELETE FROM shop.REVIEW WHERE PRO_IDX = ? AND MEM_IDX = ? AND COM_DATE = ? AND COMMENTS = ? AND STAR_RATING = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, proIdx);
			pstmt.setInt(2, memIdx);
			pstmt.setTimestamp(3, comDate);
			pstmt.setString(4, comments);
			pstmt.setInt(5, starRating);
			updateAmount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return updateAmount > 0 ? true : false;
	}

//	       Insert into SHOP.MEMBER (MEM_IDX,ID,PW,NAME,GRADE,POINT,JOIN_DATE,TYPE) 
//	     values (2,'aaaa','1234','홍길동','Bronze',0,to_date('23/02/14','RR/MM/DD'),'일반');

	public boolean updateCustomer(String id, String pw, String nam) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int updateAmount = 0;
		try {
			con = pool.getConnection();
			// 중복되지 않는 가장 작은 MEM_IDX 값 찾기
			sql = "SELECT MEM_IDX FROM MEMBER";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Integer> memIdxList = new ArrayList<>(); // MEM_IDX 값을 저장할 리스트
			while (rs.next()) {
				memIdxList.add(rs.getInt("MEM_IDX"));

			}

			System.out.println(memIdxList);
			int memIdx = 1;
			while (memIdxList.contains(memIdx)) { // MEM_IDX 값 중복 처리
				memIdx++;
			}

			// INSERT 쿼리 실행
			sql = "INSERT INTO shop.MEMBER(MEM_IDX, ID, PW, NAME, GRADE, POINT, JOIN_DATE, TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memIdx);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, nam);
			pstmt.setString(5, "Bronze");
			pstmt.setInt(6, 0);
			pstmt.setDate(7, new Date(System.currentTimeMillis()));
			pstmt.setString(8, "일반");
			updateAmount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return updateAmount > 0;
	}

	// 강성웅-리뷰

	// 박수근 - 회원가입 시작

	public boolean signUp(String id, String pw, String name) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int updateAmount = 0;
		try {
			con = pool.getConnection();
			sql = "INSERT INTO member VALUES (seq_mem.nextval, ?, ?, ?, 'Bronze', 0, SYSDATE, '일반')";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			updateAmount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return updateAmount > 0 ? true : false;
	}

	// 박수근 - 회원가입 끝
	
	// 서수정 시작
	// 로그인
		public boolean login(String id, String password) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "select pw\r\n" + "from member\r\n" + "where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					if (rs.getString(1).equals(password))
						return true; // 로그인 성공
					else {
						return false; // 로그인 실패
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}

		// 새상품 추가

		public ProductBean addProduct(ProductBean pb) {

			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;

			try {

				con = pool.getConnection();
				con.setAutoCommit(false);
				sql = "insert into product values(seq_pro.nextval,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, pb.getProName());
				pstmt.setInt(2, pb.getCateIdx());
				pstmt.setInt(3, pb.getPrice());
				pstmt.setString(4, pb.getImgAddress());
				pstmt.setInt(5, pb.getInventory());

				int cnt = pstmt.executeUpdate();

				sql = "select LAST_NUMBER-1 from USER_SEQUENCES where SEQUENCE_NAME = 'SEQ_PRO' ";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {

					pb.setProIdx(rs.getInt(1));

				}
				sql = "update product set img_address = ? where pro_idx = ? ";
				pstmt = con.prepareStatement(sql);

				String imgAddress = "./img\\\\product" + pb.getProIdx() + ".png";
				System.out.println(imgAddress);
				pstmt.setString(1, imgAddress);
				pstmt.setInt(2, pb.getProIdx());

				cnt = pstmt.executeUpdate();

				con.commit();

			} catch (Exception e) {
				e.printStackTrace();
//				JOptionPane.showMessageDialog(null, (String)e.getMessage(), "11",JOptionPane.PLAIN_MESSAGE);
				pb.setProIdx(-1);
			} finally {
				pool.freeConnection(con, pstmt); // 리소스 반환
			}

			return pb;
		}

		// 카테고리 추가
		public boolean addCate(String addCate) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				
				sql = "insert into category values(seq_cat.nextval,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, addCate);
				int cnt = pstmt.executeUpdate();
				if (cnt == 1) {
					flag = true; // 카테고리 추가 성공
				} else {
					flag = false; // 카테고리 추가 실패
				}
				System.out.println(flag);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;

		}
		
		// 카테고리 콤보박스 리스트
		public void comboList( DefaultComboBoxModel<String> cateList ) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;
			
			
			try {
				
				con = pool.getConnection();
				sql = "select cat_name from category";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				cateList.removeAllElements();
				while (rs.next()) {
					
					
					cateList.addElement(rs.getString(1));
					

				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				pool.freeConnection(con, pstmt);
			}
			
			
			
		}
	// 서수정 끝
}
