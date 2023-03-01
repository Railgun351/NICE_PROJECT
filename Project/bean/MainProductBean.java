package project.bean;

import javax.swing.JButton;
import javax.swing.JSpinner;

public class MainProductBean {
	
	private int proIdx;
	private String proName;
	private int price;
	private String imgAddress;
	private JButton btn;
	private int categoryIdx;
	private String categoryName;
	
	public MainProductBean(int proIdx, String proName, int price, String imgAddress, JButton btn, int categoryIdx, String categoryName) {
		this.proIdx = proIdx;
		this.proName = proName;
		this.price = price;
		this.imgAddress = imgAddress;
		this.btn = btn;
		this.categoryIdx = categoryIdx;
		this.categoryName = categoryName;
	}
	public MainProductBean() {
		this.proIdx = -1;
		this.proName = null;
		this.price = -1;
		this.imgAddress = null;
		this.btn = null;
		this.categoryIdx = -1;
		this.categoryName = null;
	}

	public int getProIdx() {
		return proIdx;
	}

	public void setProIdx(int proIdx) {
		this.proIdx = proIdx;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgAddress() {
		return imgAddress;
	}

	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	public int getCategoryIdx() {
		return categoryIdx;
	}

	public void setCategoryIdx(int categoryIdx) {
		this.categoryIdx = categoryIdx;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
