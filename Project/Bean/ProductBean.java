package project.bean;

public class ProductBean {
	private int PRO_IDX;
	private String PRO_NAME;
	private int CATEGORY_ID;
	private String CATEGORY_NAME;
	private int PRICE;
	private String IMG_ADDRESS;
	private int INVENTORY;
	
	public int getPRO_IDX() {
		return PRO_IDX;
	}
	public void setPRO_IDX(int pRO_IDX) {
		PRO_IDX = pRO_IDX;
	}
	public String getPRO_NAME() {
		return PRO_NAME;
	}
	public void setPRO_NAME(String pRO_NAME) {
		PRO_NAME = pRO_NAME;
	}
	public int getCATEGORY_ID() {
		return CATEGORY_ID;
	}
	public void setCATEGORY_ID(int cATEGORY_ID) {
		CATEGORY_ID = cATEGORY_ID;
	}
	public String getCATEGORY_NAME() {
		return CATEGORY_NAME;
	}
	public void setCATEGORY_NAME(String cATEGORY_NAME) {
		CATEGORY_NAME = cATEGORY_NAME;
	}
	public int getPRICE() {
		return PRICE;
	}
	public void setPRICE(int pRICE) {
		PRICE = pRICE;
	}
	public String getIMG_ADDRESS() {
		return IMG_ADDRESS;
	}
	public void setIMG_ADDRESS(String iMG_ADDRESS) {
		IMG_ADDRESS = iMG_ADDRESS;
	}
	public int getINVENTORY() {
		return INVENTORY;
	}
	public void setINVENTORY(int iNVENTORY) {
		INVENTORY = iNVENTORY;
	}
}
