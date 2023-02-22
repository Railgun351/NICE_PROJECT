package project.bean;

import javax.swing.JButton;
import javax.swing.JSpinner;

public class CartBean {
	private int proIdx;
	private String proName;
	private int memIdx;
	private int quantity;
	private int proPrice;
	private JSpinner jsp;
	private JButton btn;
	
	public CartBean() {
		proIdx = -1;
		memIdx = -1;
		quantity = -1;
	}
	
	public int getProIdx() {
		return proIdx;
	}

	public void setProIdx(int proIdx) {
		this.proIdx = proIdx;
	}

	public int getMemIdx() {
		return memIdx;
	}

	public void setMemIdx(int memIdx) {
		this.memIdx = memIdx;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProPrice() {
		return proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	public JSpinner getJsp() {
		return jsp;
	}

	public void setJsp(JSpinner jsp) {
		this.jsp = jsp;
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}
	
}
