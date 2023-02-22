package project.gangdb;

public class StarDto {
    private Integer PRO_IDX;
    private Integer MEM_IDX;
    private java.sql.Timestamp COM_DATE;
    private String COMMENTS;
    private Integer STAR_RATING;

    public StarDto( int PRO_IDX, int MEM_IDX, java.sql.Timestamp   COM_DATE, String COMMENTS, int STAR_RATING) {
        this.PRO_IDX = PRO_IDX;
        this.MEM_IDX = MEM_IDX;
        this.COM_DATE = COM_DATE;
        this.COMMENTS = COMMENTS;
        this.STAR_RATING = STAR_RATING;
    }

 

	public Integer getPRO_IDX() {
        return PRO_IDX;
    }

    public Integer getMEM_IDX() {
        return MEM_IDX;
    }

    public java.sql.Timestamp getCOM_DATE() {
        return COM_DATE;
    }

    public String getCOMMENTS() {
        return COMMENTS;
    }

    public Integer getDepIdx() {
        return STAR_RATING;
    }
	public Integer getSTAR_RATING() {
		
		return STAR_RATING;
	}
    @Override
    public String toString() {
        return "EmpDto{" +
                "PRO_IDX=" + PRO_IDX +
                ", MEM_IDX=" + MEM_IDX +
                ", COM_DATE=" + COM_DATE +
                ", COMMENTS='" + COMMENTS + '\'' +
                ", STAR_RATING=" + STAR_RATING +
                '}';
    }




}




